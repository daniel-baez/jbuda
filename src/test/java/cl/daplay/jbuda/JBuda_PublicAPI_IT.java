package cl.daplay.jbuda;

import cl.daplay.jbuda.model.Market;
import cl.daplay.jbuda.model.OrderBook;
import cl.daplay.jbuda.model.Ticker;
import cl.daplay.jbuda.model.Trades;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JBuda_PublicAPI_IT {

    JBuda buda;

    @Before
    public void before() {
        buda = new JBuda();
    }

    @Test
    public void test_get_markets() throws Exception {
        List<Market> markets = buda.getMarkets();
        Assert.assertFalse(markets.isEmpty());

        for (Market market : markets) {
            System.out.println(market);
        }
    }

    @Test
    public void test_get_tickers() throws Exception {
        List<Market> markets = buda.getMarkets();

        for (Market market : markets) {
            Ticker ticker = buda.getTicker(market.getId());
            System.out.println(market);
            System.out.println(ticker);
        }
    }

    @Test
    public void test_get_order_book() throws Exception {
        List<Market> markets = buda.getMarkets();

        for (Market market : markets) {
            OrderBook orderBook = buda.getOrderBook(market.getId());
            System.out.println(market);
            System.out.println(orderBook);
        }
    }

    @Test
    public void test_get_trades() throws Exception {
        List<Market> markets = buda.getMarkets();

        for (Market market : markets) {
            Trades trades = buda.getTrades(market.getId());
            System.out.println(market);
            System.out.println(trades);
        }
    }


}
