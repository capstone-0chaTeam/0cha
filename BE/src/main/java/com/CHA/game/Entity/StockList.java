package com.CHA.game.Entity;

import com.CHA.user.User;
import jakarta.persistence.*;
import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class StockList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockList_id")
    private Long id;


    @ManyToOne
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




}
