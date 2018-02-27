package cl.daplay.jbuda.real;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Balance extends Serializable {

    long getAccountId();

    String getId();

    BigDecimal getAmount();

    String getAmountCurrency();

    BigDecimal getAvailableAmount();

    String getAvailableAmountCurrency();

    BigDecimal getFrozenAmount();

    String getFrozenAmountCurrency();

    BigDecimal getPendingWithdrawAmount();

    String getPendingWithdrawAmountCurrency();
}

