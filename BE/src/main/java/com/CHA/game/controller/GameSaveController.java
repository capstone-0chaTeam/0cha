package com.CHA.game.controller;


import com.CHA.game.dto.StockAssetDTO;
import com.CHA.game.dto.StockListDTO;
import com.CHA.game.service.StockListService;
import com.CHA.game.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Game")
@CrossOrigin("*")
@Slf4j
public class GameSaveController {

    private final StockListService stockListService;
    private final StockService stockService;


    /*게임에서 매수,매도 한번에 처리함*/
    //EX 보유 0주 10주 구매 -> 10주 
    //   보유 10주 10주 구매 -> 20주
    //   보유 10주 0주 ->   0주
    @PostMapping("/save-stockList")
    public void saveStockList(@RequestBody StockListDTO stockListDTO) {
        stockListService.saveStockList(stockListDTO);
    }

    //게임에서 종료할때 자산 정보 저장하기 
    //주시리스트 X 전체 자산 O
    @PostMapping("/save-asset")
    public void saveStockAsset(@RequestBody StockAssetDTO stockAssetDTO) {
        stockService.saveStockAsset(stockAssetDTO);
    }


}
