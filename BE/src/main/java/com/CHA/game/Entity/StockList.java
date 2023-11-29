package com.CHA.game.Entity;

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


//    private Long balance;                   //잔고 가 필요한거 DB의 중복을 일으키는데 ? 생각해보자 -> 회의 후 삭제함 


    private String stockcode;               //종목코드
    private Long purchaseprice;             //매입단가
    private Long quantityheld;              //주식 보유수량

    public void updateStockList(String stockcode, Long purchaseprice , Long quantityheld){
        this.stockcode = stockcode;
        this.purchaseprice = purchaseprice;
        this.quantityheld = quantityheld;
    }


}
