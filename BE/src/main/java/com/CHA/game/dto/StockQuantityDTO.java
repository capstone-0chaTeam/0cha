package com.CHA.game.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockQuantityDTO {


    private Long quantityheld;
    private String stockcode;



    public Long getQuantityheld() {
        return quantityheld;
    }

    public String getStockcode() {
        return stockcode;
    }



}
