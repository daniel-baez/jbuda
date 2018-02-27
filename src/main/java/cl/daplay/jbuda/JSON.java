package cl.daplay.jbuda;

import cl.daplay.jbuda.model.ApiKey;
import cl.daplay.jbuda.model.JBudaException;
import cl.daplay.jbuda.model.Page;
import cl.daplay.jbuda.model.Ticker;
import cl.daplay.jbuda.model.Balance;
import cl.daplay.jbuda.model.Deposit;
import cl.daplay.jbuda.model.Market;
import cl.daplay.jbuda.model.Order;
import cl.daplay.jbuda.model.OrderBook;
import cl.daplay.jbuda.model.Trades;
import cl.daplay.jbuda.model.Withdrawal;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface JSON {

    String newAPIKey(String name, Instant expiration) throws Exception;

    String newOrder(String marketId,
                    String orderType,
                    String orderPriceType,
                    BigDecimal qty,
                    BigDecimal price) throws Exception;

    String cancelOrder(long orderId) throws Exception;


    ApiKey apiKey(String json) throws Exception;

    Order order(String json) throws Exception;

    Ticker ticker(String json) throws Exception;

    OrderBook orderBook(String json) throws Exception;

    Balance balance(String json) throws Exception;

    Trades trades(String json) throws Exception;

    List<Market> markets(String json) throws Exception;

    List<Balance> balances(String json) throws Exception;

    List<Order> orders(String json) throws Exception;

    List<Deposit> deposits(String json) throws Exception;

    List<Withdrawal> withdrawls(String json) throws Exception;

    Page page(String json) throws Exception;

    JBudaException exception(int statusCode, String json) throws Exception;
}
