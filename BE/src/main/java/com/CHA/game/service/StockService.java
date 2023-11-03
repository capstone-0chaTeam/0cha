package com.CHA.game.service;

import com.CHA.game.Entity.Stock;
import com.CHA.game.Entity.StockList;
import com.CHA.game.repository.StockListRepository;
import com.CHA.game.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class StockService {


    private final StockListRepository stockListRepository;
    private final StockRepository stockRepository;


    public List showStock(String account){

//        Optional<Stock> byId = stockRepository.findById(Id);
        Optional<Stock> byAccount = stockRepository.findByAccount(account);

        List<StockList> stocklist = byAccount.get().getStocklist();
        return stocklist;

    }

}
