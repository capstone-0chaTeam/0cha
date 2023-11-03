package com.CHA.game.dto;


import com.CHA.game.Entity.Stock;
import com.CHA.game.Entity.StockList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockListDTO {

//    private Stock stocklist_to_stock;       //사용자 분류
//    private Long stock_id;
    private String account;                 //11.3 수정
    private String stockcode;               //종목코드
    private Long valuationgainandloss;      //평가손익
    private Long purchaseamount;            //매입금액
    private Long evaluationamount;          //평가금액
    private Long stockreturns;              //수익률
    private Long purchaseprice;             //매입단가
    private Long quantityheld;              //주식 보유수량

    public static StockListDTO toDto(StockList entity ,Stock stock){
        return StockListDTO.builder()
//                .stocklist_to_stock(entity.getStocklist_to_stock())
                .account(stock.getAccount())
                .stockcode(entity.getStockcode())
                .valuationgainandloss(entity.getValuationgainandloss())
                .purchaseamount(entity.getPurchaseamount())
                .evaluationamount(entity.getEvaluationamount())
                .stockreturns(entity.getStockreturns())
                .purchaseprice(entity.getPurchaseprice())
                .quantityheld(entity.getQuantityheld())
                .build();
    }

    @Override
    public String toString() {
        return
//                "stocklist_to_stock=" + stocklist_to_stock +
                ", stockcode='" + stockcode + '\'' +
                ", valuationgainandloss=" + valuationgainandloss +
                ", purchaseamount=" + purchaseamount +
                ", evaluationamount=" + evaluationamount +
                ", stockreturns=" + stockreturns +
                ", purchaseprice=" + purchaseprice +
                ", quantityheld=" + quantityheld +
                '}';
    }


}
