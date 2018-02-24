package cl.daplay.jbuda.jackson.dto;

import cl.daplay.jbuda.jackson.model.order.JacksonOrder;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class OrderDTO {

    @JsonProperty("order")
    private final JacksonOrder order;

    @JsonCreator
    public OrderDTO(@JsonProperty("order") JacksonOrder order) {
        this.order = order;
    }

    public JacksonOrder getOrder() {
        return order;
    }

}
