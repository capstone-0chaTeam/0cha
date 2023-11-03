package com.CHA.game.Entity;

import com.CHA.game.dto.StockListDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


//StockList에서는 Stock 조회 안해도 될거같음 양방향 해제 해도 될거같음 굳이 쿼리를 날릴 필요가 ,, 우선 더 짜보자
//Stock에서만 Stock를 조회하자 -> 10.31

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class StockList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockList_id")
    private Long id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id" )
    private Stock stocklist_to_stock;


//    private Long balance;                   //잔고 가 필요한거 DB의 중복을 일으키는데 ? 생각해보자


    private String stockcode;               //종목코드
    private Long valuationgainandloss;      //평가손익
    private Long purchaseamount;            //매입금액
    private Long evaluationamount;          //평가금액
    private Long stockreturns;              //수익률
    private Long purchaseprice;             //매입단가
    private Long quantityheld;              //주식 보유수량

    public static StockList toEntity(StockListDTO dto ){ // Stock stock 매개 변수
        return StockList.builder()
//                .stocklist_to_stock(stock)
                .stockcode(dto.getStockcode())
                .valuationgainandloss(dto.getValuationgainandloss())
                .purchaseamount(dto.getPurchaseamount())
                .evaluationamount(dto.getEvaluationamount())
                .stockreturns(dto.getStockreturns())
                .purchaseprice(dto.getPurchaseprice())
                .quantityheld(dto.getQuantityheld())
                .build();
    }




}
