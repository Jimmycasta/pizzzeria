package com.jimmycasta.pizzeria.projections;

import java.time.LocalDate;

public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDate getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
