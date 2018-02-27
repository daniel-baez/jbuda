package cl.daplay.jbuda.devel;

import cl.daplay.jbuda.Constants;
import cl.daplay.jbuda.JSON;
import cl.daplay.jbuda.model.*;
import cl.daplay.jbuda.real.RealMarket;
import cl.daplay.jbuda.real.RealTicker;
import cl.daplay.jfun.ThrowingFunction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class PartialDelegatingJSON extends DelegatingJSON {

    private final DecimalFormat decimalFormat;
    private final DateTimeFormatter dateTimeFormatter;

    public PartialDelegatingJSON(JSON delegate) {
        super(delegate);
        decimalFormat = Constants.newBigDecimalFormat();
        dateTimeFormatter = Constants.newDateTimeFormatter();
    }

    @Override
    public String newAPIKey(String name, Instant expiration) {
        final String template = "{\"api_key\":{\"name\":\"%s\",\"expiration_time\":\"%s\"}}";
        return format(template, name, dateTimeFormatter.format(expiration));
    }

    @Override
    public String newOrder(String marketId, String orderType, String orderPriceType, BigDecimal qty, BigDecimal price) {
        final String template = "{\"order\":{\"type\":\"%s\",\"price_type\":\"%s\",\"limit\":\"%s\",\"amount\":\"%s\"}}";
        return format(template, orderType, orderPriceType, decimalFormat.format(price), decimalFormat.format(qty));
    }

    @Override
    public String cancelOrder(long orderId) {
        return "{\"state\":\"CANCELING\"}";
    }

    @Override
    public List<Market> markets(String _json) throws Exception {
        return unwrapList(_json, "markets", (o) -> {
            final String id = o.optString("id");
            final String name = o.optString("name");
            final String baseCurrency = o.optString("base_currency");
            final String quoteCurrency = o.optString("quote_currency");
            final Tuple<BigDecimal, String> minimumOrderAmount = amount(o, "minimum_order_amount");

            return new RealMarket(id, name, baseCurrency, quoteCurrency, minimumOrderAmount);
        });
    }

    @Override
    public Ticker ticker(String _json) throws Exception {
        return unwrapObject(_json, "ticker", ticker -> {
            final String marketId = ticker.optString("market_id");

            final Tuple<BigDecimal, String> lastPrice = amount(ticker, "last_price");
            final Tuple<BigDecimal, String> minAsk = amount(ticker, "min_ask");
            final Tuple<BigDecimal, String> maxBid = amount(ticker, "max_bid");
            final Tuple<BigDecimal, String> volume = amount(ticker, "volume");

            final BigDecimal priceVariation24h = bigDecimal(ticker, "price_variation_24h");
            final BigDecimal priceVariation7d = bigDecimal(ticker, "price_variation_7d");

            return new RealTicker(marketId, lastPrice, minAsk, maxBid, volume, priceVariation24h, priceVariation7d);
        });
    }

    @Override
    public OrderBook orderBook(String json) throws Exception {
        return super.orderBook(json);
    }

    @Override
    public Trades trades(String json) throws Exception {
        return super.trades(json);
    }

    @Override
    public JBudaException exception(int statusCode, String json) throws Exception {
        return super.exception(statusCode, json);
    }

    private BigDecimal bigDecimal(JSONObject json, String propertyName) throws ParseException {
        return (BigDecimal) decimalFormat.parse(json.optString(propertyName));
    }

    private Tuple<BigDecimal, String> amount(JSONObject json, String propertyName) throws ParseException {
        final JSONArray property = json.optJSONArray(propertyName);

        final BigDecimal amount = (BigDecimal) decimalFormat.parse(property.optString(0));
        final String currency = property.optString(1);

        return new Tuple<>(amount, currency);
    }

    private <K> K unwrapObject(String _json, String propertyName, ThrowingFunction<JSONObject, K> mapper) throws Exception {
        final JSONObject json = new JSONObject(_json);
        return mapper.apply(json.optJSONObject(propertyName));
    }

    private <K> List<K> unwrapList(String _json, String propertyName, ThrowingFunction<JSONObject, K> mapper) throws Exception {
        final JSONObject json = new JSONObject(_json);
        final JSONArray array = json.optJSONArray(propertyName);

        final int length = array.length();
        final List<K> result = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            result.add(mapper.apply(array.optJSONObject(i)));
        }

        return result;
    }


}
