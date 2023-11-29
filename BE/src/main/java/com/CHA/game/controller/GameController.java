package com.CHA.game.controller;


import com.CHA.game.Entity.StockList;
import com.CHA.game.dto.StartStockListDTO;
import com.CHA.game.dto.StockCashDTO;
import com.CHA.game.dto.StockQuantityDTO;
import com.CHA.game.service.StockListService;
import com.CHA.game.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Game")
@CrossOrigin("*")
@Slf4j
public class GameController {
    //10-28일

    private final StockListService stockListService;
    private final StockService stockService;



    //매도하기 수량 불러오기
        // 경로 변수로 받은 account와 quantityheld 값을 사용하여 요청을 처리

    @GetMapping("/stockquantity")
    public ResponseEntity<StockQuantityDTO> StockQuantity(@RequestParam String account , @RequestParam String stockcode) {
        return ResponseEntity.ok(stockListService.StockQuantity(account , stockcode));
    }

    //현금 조회하기
    @GetMapping("/stockcash")
    public ResponseEntity<StockCashDTO> StockCash(@RequestParam String account){
        return ResponseEntity.ok().body(stockListService.StockCash(account));
    }
    //게임시작할때 전체 주식 리스트 조회하기
    @GetMapping("/start/stockList")
    public ResponseEntity<List<StartStockListDTO>> startStockList(@RequestParam String account){
        return ResponseEntity.ok().body(stockService.startStockList(account));
    }
    
    //전체 주식 리스트 다른 버전
    /*
    
    @GetMapping("/who_buyList")
    public ResponseEntity<List<StockList>> showStockList(@RequestParam("account") String account)  {

        List<StockList> stockList = stockService.showStock(account);
        return ResponseEntity.ok().body(stockList);
    }


    @GetMapping("/who_buyList2")
    public ResponseEntity<List<StockList>> showStockList2(@RequestParam("account") String account)  {
        List<StockList> stockList = stockService.showStock(account);
        
        return ResponseEntity.ok().body(stockList);
    }
    */
}
