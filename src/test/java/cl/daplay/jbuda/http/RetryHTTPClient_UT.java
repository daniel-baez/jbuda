package cl.daplay.jbuda.http;

import cl.daplay.jbuda.HTTPClient;
import cl.daplay.jbuda.Signer;
import cl.daplay.jbuda.model.JBudaException;
import cl.daplay.jfun.ThrowingSupplier;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class RetryHTTPClient_UT {

    /**
     * shared counter to track invocations of "failing clients"
     */
    Map<String, AtomicInteger> counters = new HashMap<>();

    int newRandomLimit() {
        return new Random().nextInt(10) + 1;
    }

    /**
     * access/creates any counter by name
     * @param name
     * @return
     */
    AtomicInteger getCounter(String name) {
        return counters.computeIfAbsent(name, __ -> new AtomicInteger());
    }

    /**
     * creates a "failing client"
     *
     * a failing client is a HTTPClient implementation that always fails
     * with the exception returned by the supplier
     *
     * it keeps a counter of each invocation using `getCounter(name)`
     * that can later be checked.
     *
     * @param supplier
     * @return
     */
    private HTTPClient failingClient(Supplier<Exception> supplier) {
        return new HTTPClient() {
            @Override
            public <T> T get(String path, Signer signer, HTTPResponseHandler<T> responseMapper) throws Exception {
                getCounter("get").incrementAndGet();
                throw supplier.get();
            }

            @Override
            public <T> T put(String path, Signer signer, String jsonBody, HTTPResponseHandler<T> responseHandler) throws Exception {
                getCounter("put").incrementAndGet();
                throw supplier.get();
            }

            @Override
            public <T> T post(String path, Signer signer, String jsonBody, HTTPResponseHandler<T> responseHandler) throws Exception {
                getCounter("post").incrementAndGet();
                throw supplier.get();
            }
        };
    }

    @Before
    public void before() {
        // for every test, clear the counters
        counters.clear();
    }

    /**
     * given any Exception retry up to limit times
     */
    @Test
    public void test_get() {
        int limit = newRandomLimit();
        HTTPClient client = new RetryHTTPClient(failingClient(Exception::new), limit);

        testCounter("get", limit, () -> {
            return client.get("path", null, null);
        });
    }

    /**
     * given a JBudaException (app logic exception) don't retry
     */
    @Test
    public void test_get_jbuda_exception() {
        int limit = newRandomLimit();
        HTTPClient client = new RetryHTTPClient(failingClient(JBudaException::new), limit);

        testCounter("get", 1, () -> {
            return client.get("path", null, null);
        });
    }

    /**
     * given a JBudaException for 401 retry up to limit times
     */
    @Test
    public void test_get_jbuda_exception_401() {
        int limit = newRandomLimit();
        HTTPClient client = new RetryHTTPClient(failingClient(() -> new JBudaException(401, "", "", new JBudaException.Detail[0])), limit);

        testCounter("get", limit, () -> {
            return client.get("path", null, null);
        });
    }

    /**
     * given any Exception retry up to limit times
     */
    @Test
    public void test_put() {
        int limit = newRandomLimit();
        HTTPClient client = new RetryHTTPClient(failingClient(Exception::new), limit);

        testCounter("put", limit, () -> {
            return client.put("path", null, null, null);
        });
    }


    /**
     * given any Exception retry up to limit times
     */
    @Test
    public void test_post() {
        int limit = newRandomLimit();
        HTTPClient client = new RetryHTTPClient(failingClient(Exception::new), limit);

        testCounter("post", limit, () -> {
            return client.post("path", null, null, null);

        });
    }

    private void testCounter(String counterName, int expected, ThrowingSupplier supplier) {
        assertEquals(0, getCounter(counterName).get());

        try {
            supplier.get();
        } catch (Exception ex) {
            assertEquals(expected, getCounter(counterName).get());
        }
    }

}
