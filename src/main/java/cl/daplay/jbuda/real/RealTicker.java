package cl.daplay.jbuda.real;

import cl.daplay.jbuda.devel.Tuple;
import cl.daplay.jbuda.jackson.BigDecimalToStringSerializer;
import cl.daplay.jbuda.jackson.model.JacksonAmount;
import cl.daplay.jbuda.model.Ticker;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

public class RealTicker implements Ticker, Serializable {

    private static final long serialVersionUID = 2017_08_06;

    private final String marketId;
    private final Tuple<BigDecimal, String> lastPrice;
    private final Tuple<BigDecimal, String> minAsk;
    private final Tuple<BigDecimal, String> maxBid;
    private final Tuple<BigDecimal, String> volume;
    private final BigDecimal priceVariation24Hours;
    private final BigDecimal priceVariation7Days;

    @JsonCreator
    public RealTicker(final String marketId,
                      final Tuple<BigDecimal, String> lastPrice,
                      final Tuple<BigDecimal, String> minAsk,
                      final Tuple<BigDecimal, String> maxBid,
                      final Tuple<BigDecimal, String> volume,
                      final BigDecimal priceVariation24Hours,
                      final BigDecimal priceVariation7Days) {
        this.marketId = marketId;
        this.lastPrice = lastPrice;
        this.minAsk = minAsk;
        this.maxBid = maxBid;
        this.volume = volume;
        this.priceVariation24Hours = priceVariation24Hours;
        this.priceVariation7Days = priceVariation7Days;
    }

    @Override
    public String getMarketId() {
        return marketId;
    }

    @Override
    public BigDecimal getLastPrice() {
        return lastPrice.left;
    }

    @Override
    public String getLastPriceCurrency() {
        return lastPrice.right;
    }

    @Override
    public BigDecimal getMinAsk() {
        return minAsk.left;
    }

    @Override
    public String getMinAskCurrency() {
        return minAsk.right;
    }

    @Override
    public BigDecimal getMaxBid() {
        return maxBid.left;
    }

    @Override
    public String getMaxBidCurrency() {
        return maxBid.right;
    }

    @Override
    public BigDecimal getVolume() {
        return volume.left;
    }

    @Override
    public String getVolumeCurrency() {
        return volume.right;
    }

    public BigDecimal getPriceVariation24Hours() {
        return priceVariation24Hours;
    }

    public BigDecimal getPriceVariation7Days() {
        return priceVariation7Days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealTicker ticker = (RealTicker) o;

        if (lastPrice != null ? !lastPrice.equals(ticker.lastPrice) : ticker.lastPrice != null) return false;
        if (minAsk != null ? !minAsk.equals(ticker.minAsk) : ticker.minAsk != null) return false;
        if (maxBid != null ? !maxBid.equals(ticker.maxBid) : ticker.maxBid != null) return false;
        if (volume != null ? !volume.equals(ticker.volume) : ticker.volume != null) return false;
        if (priceVariation24Hours != null ? !priceVariation24Hours.equals(ticker.priceVariation24Hours) : ticker.priceVariation24Hours != null)
            return false;
        return priceVariation7Days != null ? priceVariation7Days.equals(ticker.priceVariation7Days) : ticker.priceVariation7Days == null;
    }

    @Override
    public int hashCode() {
        int result = lastPrice != null ? lastPrice.hashCode() : 0;
        result = 31 * result + (minAsk != null ? minAsk.hashCode() : 0);
        result = 31 * result + (maxBid != null ? maxBid.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (priceVariation24Hours != null ? priceVariation24Hours.hashCode() : 0);
        result = 31 * result + (priceVariation7Days != null ? priceVariation7Days.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "lastPrice=" + lastPrice +
                ", minAsk=" + minAsk +
                ", maxBid=" + maxBid +
                ", volume=" + volume +
                ", priceVariation24Hours=" + priceVariation24Hours +
                ", priceVariation7Days=" + priceVariation7Days +
                '}';
    }
}
