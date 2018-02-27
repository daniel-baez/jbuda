package cl.daplay.jbuda.real;

import cl.daplay.jbuda.devel.Tuple;
import cl.daplay.jbuda.model.Market;

import java.io.Serializable;
import java.math.BigDecimal;

public class RealMarket implements Market, Serializable {

    private final String id;
    private final String name;
    private final String baseCurrency;
    private final String quoteCurrency;
    private final Tuple<BigDecimal, String> minimumOrderAmount;

    public RealMarket(String id,
                      String name,
                      String baseCurrency,
                      String quoteCurrency,
                      Tuple<BigDecimal, String> minimumOrderAmount) {
        this.id = id;
        this.name = name;
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.minimumOrderAmount = minimumOrderAmount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBaseCurrency() {
        return baseCurrency;
    }

    @Override
    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    @Override
    public BigDecimal getMinimumOrderAmount() {
        return minimumOrderAmount.left;
    }

    @Override
    public String getMinimumOrderAmountCurrency() {
        return minimumOrderAmount.right;
    }

}
