package com.CHA.game.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StartStockListDTO {

    private String stockcode;               //종목코드
    private Long quantityheld;              //주식 보유수량
    private Long purchaseprice;             //매입단가

    public String getStockcode() {
        return stockcode;
    }

    public Long getQuantityheld() {
        return quantityheld;
    }

    public Long getPurchaseprice() {
        return purchaseprice;
    }
}
