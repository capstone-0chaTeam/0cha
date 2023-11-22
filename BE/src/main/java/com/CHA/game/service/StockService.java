package com.CHA.game.service;

import com.CHA.game.Entity.Stock;
import com.CHA.game.Entity.StockList;
import com.CHA.game.dto.StartStockListDTO;
import com.CHA.game.dto.StockAssetDTO;
import com.CHA.game.repository.StockListRepository;
import com.CHA.game.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class StockService {


    private final StockListRepository stockListRepository;
    private final StockRepository stockRepository;


    public List showStock(String account){

        Optional<Stock> byAccount = stockRepository.findByAccount(account);

        List<StockList> stocklist = byAccount.get().getStocklist();
        return stocklist;

    }

    /*
    * 마이자산 전체저장
    * account 기반으로 stock 찾기 -> stock 값 업데이트
    * */
    public void saveStockAsset(StockAssetDTO stockAssetDTO) {
        Optional<Stock> byAccount = stockRepository.findByAccount(stockAssetDTO.getAccount());

        if (byAccount.isPresent()){
            byAccount.get().updateStock(stockAssetDTO.getBalance(),
                    stockAssetDTO.getCash(),
                    stockAssetDTO.getPurchaseamount_all());
        }
    }

    //account 기준으로 조회 -> Stock값 찾음
    //해당 고객 account의 기준으로 주식 리스트를 찾고 주식 리스트중 StockList_id 값을 제외하고
    //StartStockListDTO(종목코드 , 매수량 , 매입금액) 을 List 에 저장해서 StartStockListDTO 타입으로 반환
    public List<StartStockListDTO> startStockList(String account) {
        Optional<Stock> byAccount = stockRepository.findByAccount(account);

        List<StartStockListDTO> list = new ArrayList<>();

        for (StockList stockList : byAccount.get().getStocklist()){
            list.add(StartStockListDTO.builder()
                    .stockcode(stockList.getStockcode())
                    .quantityheld(stockList.getQuantityheld())
                    .purchaseprice(stockList.getPurchaseprice())
                    .build());
        }
        return list;
    }

}
