package com.CHA.game.controller;


import com.CHA.game.Entity.StockList;
import com.CHA.game.dto.StockAccountDTO;
import com.CHA.game.dto.StockListDTO;
import com.CHA.game.service.StockListService;
import com.CHA.game.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Game")
@Slf4j
public class GameController {
    //10-28일

    private final StockListService stockListService;
    private final StockService stockService;
    private final ModelMapper modelMapper;

    //@RequestHeader("StockList") String stocklist
    /*

{
    "stock_id": "1", // Stock 엔티티의  /실제 ID 값
    "stockcode": "!21233",
    "valuationgainandloss": 112323,
    "purchaseamount": 121233,
    "evaluationamount": 121233,
    "stockreturns": 121233,
    "purchaseprice": 121233,
    "quantityheld": 121233
    }
}

    * */
    @PostMapping("/save-stockList")
    public ResponseEntity<StockListDTO> saveStockList(@RequestBody StockListDTO stockListDTO) {
        StockListDTO list = stockListService.saveStockList(stockListDTO);
        System.out.println(list);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/who_buyList")
    public ResponseEntity<List<StockList>> showStockList(@RequestParam("account") String account)  {

        System.out.println(account);

        List<StockList> stockList = stockService.showStock(account);

        System.out.println(stockList.toString());
        return ResponseEntity.ok().body(stockList);
    }
}
