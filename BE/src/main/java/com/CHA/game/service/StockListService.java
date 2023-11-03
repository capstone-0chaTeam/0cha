package com.CHA.game.service;


import com.CHA.game.Entity.Stock;
import com.CHA.game.Entity.StockList;
import com.CHA.game.dto.StockListDTO;
import com.CHA.game.repository.StockListRepository;
import com.CHA.game.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockListService {

    private final StockListRepository stockListRepository;
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    public StockListDTO saveStockList(StockListDTO stockListDTO){

        Optional<Stock> byId = stockRepository.findByAccount(stockListDTO.getAccount());

        StockList stockList = StockList.builder()
                .stocklist_to_stock(byId.get())
                .stockcode(stockListDTO.getStockcode())
                .valuationgainandloss(stockListDTO.getValuationgainandloss())
                .purchaseamount(stockListDTO.getPurchaseamount())
                .evaluationamount(stockListDTO.getEvaluationamount())
                .stockreturns(stockListDTO.getStockreturns())
                .purchaseprice(stockListDTO.getPurchaseprice())
                .quantityheld(stockListDTO.getQuantityheld())
                .build();

        stockListRepository.save(stockList);


        return stockListDTO;
    }

//    stock id 를 받고 스톡리스트에 넣고 나머지값넣기

//    public List<StockList> who_buyList(Long id){
//
//        List<StockList> stocklist = stockListRepository.findAllByStocklistToStockId(id);
//
//
//        return stocklist;
//    }

}
