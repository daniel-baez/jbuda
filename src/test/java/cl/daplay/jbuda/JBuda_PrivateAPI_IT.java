package cl.daplay.jbuda;

import cl.daplay.jbuda.devel.PartialDelegatingJSON;
import cl.daplay.jbuda.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JBuda_PrivateAPI_IT {


    JBuda buda;

    @Before
    public void before() {
        String key = "fb107cb2f1decebf20553e5a5c7332f4";
        String secret = "WE4YnqHUs6oKCL5nONBEnaOLFc7tzXL55Z3bBApc";
        buda = new JBuda(key, secret, Constants.newNonce(), new InetSocketAddress("localhost", 8888));
    }

    @Ignore
    @Test
    public void test_new_api_key() throws Exception {
        String name = UUID.randomUUID().toString();
        ApiKey newApiKey = buda.newAPIKey(name, Instant.now().plus(5, ChronoUnit.DAYS));
        System.out.println(newApiKey);
    }

    @Test
    public void test_new_order_and_cancel_order() throws Exception {
        Order order = buda.newOrder("btc-clp", "bid", "limit", new BigDecimal(5), BigDecimal.ONE);
        buda.cancelOrder(order.getId());
        System.out.println(order);
    }

}
