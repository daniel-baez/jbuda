package cl.daplay.jbuda.devel;

import cl.daplay.jbuda.JSON;
import cl.daplay.jbuda.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class DelegatingJSON implements JSON {

    private final JSON delegate;

    public DelegatingJSON(JSON delegate) {
        this.delegate = delegate;
    }

    @Override
    public String newAPIKey(String name, Instant expiration) throws Exception {
        return delegate.newAPIKey(name, expiration);
    }

    @Override
    public String newOrder(String marketId, String orderType, String orderPriceType, BigDecimal qty, BigDecimal price) throws Exception {
        return delegate.newOrder(marketId, orderType, orderPriceType, qty, price);
    }

    @Override
    public String cancelOrder(long orderId) throws Exception {
        return delegate.cancelOrder(orderId);
    }

    @Override
    public ApiKey apiKey(String json) throws Exception {
        return delegate.apiKey(json);
    }

    @Override
    public Order order(String json) throws Exception {
        return delegate.order(json);
    }

    @Override
    public Ticker ticker(String json) throws Exception {
        return delegate.ticker(json);
    }

    @Override
    public OrderBook orderBook(String json) throws Exception {
        return delegate.orderBook(json);
    }

    @Override
    public Balance balance(String json) throws Exception {
        return delegate.balance(json);
    }

    @Override
    public Trades trades(String json) throws Exception {
        return delegate.trades(json);
    }

    @Override
    public List<Market> markets(String json) throws Exception {
        return delegate.markets(json);
    }

    @Override
    public List<Balance> balances(String json) throws Exception {
        return delegate.balances(json);
    }

    @Override
    public List<Order> orders(String json) throws Exception {
        return delegate.orders(json);
    }

    @Override
    public List<Deposit> deposits(String json) throws Exception {
        return delegate.deposits(json);
    }

    @Override
    public List<Withdrawal> withdrawls(String json) throws Exception {
        return delegate.withdrawls(json);
    }

    @Override
    public Page page(String json) throws Exception {
        return delegate.page(json);
    }

    @Override
    public JBudaException exception(int statusCode, String json) throws Exception {
        return delegate.exception(statusCode, json);
    }
}
