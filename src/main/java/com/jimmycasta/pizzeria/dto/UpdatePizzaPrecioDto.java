package com.jimmycasta.pizzeria.dto;

import lombok.Data;

@Data
public class UpdatePizzaPrecioDto {

    private int idPizza;
    private double newPrice;
}
