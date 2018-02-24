package cl.daplay.jbuda.jackson.dto;

import cl.daplay.jbuda.jackson.model.trades.JacksonTrades;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TradesDTO {

    @JsonProperty("trades")
    private final JacksonTrades trades;

    @JsonCreator
    public TradesDTO(@JsonProperty("trades") JacksonTrades trades) {
        this.trades = trades;
    }

    public JacksonTrades getTrades() {
        return trades;
    }

}
