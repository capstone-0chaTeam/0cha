package com.CHA.game.dto;

import com.CHA.game.Entity.StockList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockListDTO {

    private String account;
    private String stockcode;               //종목코드
    private Long purchaseprice;             //매입단가
    private Long quantityheld;              //주식 보유수량

    public String getAccount() {
        return account;
    }

    public String getStockcode() {
        return stockcode;
    }

    public Long getPurchaseprice() {
        return purchaseprice;
    }

    public Long getQuantityheld() {
        return quantityheld;
    }
    //Entity -> DTO
    public static StockListDTO toDto(StockList stockList ){

        return  StockListDTO.builder()
                .stockcode(stockList.getStockcode())
                .purchaseprice(stockList.getPurchaseprice())
                .quantityheld(stockList.getQuantityheld())
                .build();
    }


}
