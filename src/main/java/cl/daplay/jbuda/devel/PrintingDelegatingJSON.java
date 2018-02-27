package cl.daplay.jbuda.devel;

import cl.daplay.jbuda.JSON;
import cl.daplay.jbuda.model.*;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class PrintingDelegatingJSON extends DelegatingJSON {

    public PrintingDelegatingJSON(JSON delegate) {
        super(delegate);
    }

    public void log(String identifier, String json) throws Exception {
        String fileName = String.format("___%s_%s.json", identifier, System.currentTimeMillis());
        File file = new File(fileName);

        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    @Override
    public String newAPIKey(String name, Instant expiration) throws Exception {
        String json = super.newAPIKey(name, expiration);
        log("newAPIKey", json);
        return json;
    }

    @Override
    public String newOrder(String marketId, String orderType, String orderPriceType, BigDecimal qty, BigDecimal price) throws Exception {
        String json = super.newOrder(marketId, orderType, orderPriceType, qty, price);
        log("newOrder", json);
        return json;
    }

    @Override
    public String cancelOrder(long orderId) throws Exception {
        String json = super.cancelOrder(orderId);
        log("cancelOrder", json);
        return json;
    }

    @Override
    public ApiKey apiKey(String json) throws Exception {
        log("apiKey", json);
        return super.apiKey(json);
    }

    @Override
    public Order order(String json) throws Exception {
        log("order", json);
        return super.order(json);
    }

    @Override
    public Ticker ticker(String json) throws Exception {
        log("ticker", json);
        return super.ticker(json);
    }

    @Override
    public OrderBook orderBook(String json) throws Exception {
        log("orderBook", json);
        return super.orderBook(json);
    }

    @Override
    public Balance balance(String json) throws Exception {
        log("balance", json);
        return super.balance(json);
    }

    @Override
    public Trades trades(String json) throws Exception {
        log("trades", json);
        return super.trades(json);
    }

    @Override
    public List<Market> markets(String json) throws Exception {
        log("markets", json);
        return super.markets(json);
    }

    @Override
    public List<Balance> balances(String json) throws Exception {
        log("balances", json);
        return super.balances(json);
    }

    @Override
    public List<Order> orders(String json) throws Exception {
        log("orders", json);
        return super.orders(json);
    }

    @Override
    public List<Deposit> deposits(String json) throws Exception {
        log("deposits", json);
        return super.deposits(json);
    }

    @Override
    public List<Withdrawal> withdrawls(String json) throws Exception {
        log("withdrawls", json);
        return super.withdrawls(json);
    }

    @Override
    public Page page(String json) throws Exception {
        log("page", json);
        return super.page(json);
    }

    @Override
    public JBudaException exception(int statusCode, String json) throws Exception {
        log("exception", json);
        return super.exception(statusCode, json);
    }
}
