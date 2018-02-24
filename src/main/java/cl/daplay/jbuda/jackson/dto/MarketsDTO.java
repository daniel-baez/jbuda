package cl.daplay.jbuda.jackson.dto;

import cl.daplay.jbuda.jackson.model.market.JacksonMarket;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class MarketsDTO {

    @JsonProperty("markets")
    private final List<JacksonMarket> markets;

    @JsonCreator
    public MarketsDTO(@JsonProperty("markets") List<JacksonMarket> markets) {
        this.markets = markets;
    }

    public List<JacksonMarket> getMarkets() {
        return markets;
    }

}
