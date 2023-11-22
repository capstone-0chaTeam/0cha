package com.CHA.game.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockAssetDTO {

    private String account;                 //계좌번호
    private Long cash;                       //주식을 살 수 있는 현금
    private Long balance;                   //통장잔고
    private Long purchaseamount_all;        //매입금액




}
