package cl.daplay.jbuda.jackson.dto;

import cl.daplay.jbuda.jackson.model.balance.JacksonBalance;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class BalanceDTO {

    @JsonProperty("balance")
    private final JacksonBalance balance;

    @JsonCreator
    public BalanceDTO(@JsonProperty("balance") JacksonBalance balance) {
        this.balance = balance;
    }

    public JacksonBalance getBalance() {
        return balance;
    }

}
