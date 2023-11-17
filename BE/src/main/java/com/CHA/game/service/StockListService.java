package com.CHA.game.service;


import com.CHA.game.Entity.Stock;
import com.CHA.game.Entity.StockList;
import com.CHA.game.dto.StockCashDTO;
import com.CHA.game.dto.StockListDTO;
import com.CHA.game.dto.StockQuantityDTO;
import com.CHA.game.repository.StockListRepository;
import com.CHA.game.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StockListService {

    private final StockListRepository stockListRepository;
    private final StockRepository stockRepository;


    //매수하면 quantityheld > 0으로 보내면 원래 있던 종목 삭제후 다시 새롭게 저장 -> 업데이트 형식이 아님
    /*
    * 메소드 추출하기
    * 1. 수량이 0이면 삭제
    * 2.이름이 같은게 있으면 값 변환
    * 3.처음 들어온 값이면 생성
    * */
    public void saveStockList(StockListDTO stockListDTO) {
        Optional<Stock> byId = stockRepository.findByAccount(stockListDTO.getAccount());

        if (stockListDTO.getQuantityheld()== 0){
            stockListRepository.deleteBystockcode(stockListDTO.getStockcode());
            return;
        }

        for (StockList stockList : byId.get().getStocklist()) {
            if(stockList.getStockcode().equals(stockListDTO.getStockcode())) {
                stockList.updateStockList(stockListDTO.getStockcode(), stockListDTO.getPurchaseprice(), stockListDTO.getQuantityheld());
                return;
            }
        }

        StockList list = StockList.builder()
                .stocklist_to_stock(byId.get())
                .stockcode(stockListDTO.getStockcode())
                .quantityheld(stockListDTO.getQuantityheld())
                .purchaseprice(stockListDTO.getPurchaseprice())
                .build();

        stockListRepository.save(list);

    }


    public StockQuantityDTO StockQuantity(String account, String stockcode){

        Optional<Stock> byId = stockRepository.findByAccount(account);

        for (StockList stockList : byId.get().getStocklist()) {
            if(stockList.getStockcode().equals(stockcode)) {
                return StockQuantityDTO.builder()
                        .stockcode(stockList.getStockcode())
                        .quantityheld(stockList.getQuantityheld())
                        .build();
            }
        }
        return null;
    }


    public StockCashDTO StockCash(String account){
        Optional<Stock> byId = stockRepository.findByAccount(account);

        return StockCashDTO.builder()
                .cash(byId.get().getCash())
                .build();


    }

}
