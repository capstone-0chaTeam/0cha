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
    @PostMapping("/save-stockList")
    public void saveStockList(@RequestBody StockListDTO stockListDTO) {
        stockListService.saveStockList(stockListDTO);
    }

    @PostMapping("/save-asset")
    public void saveStockAsset(@RequestBody StockAssetDTO stockAssetDTO) {
        stockService.saveStockAsset(stockAssetDTO);
    }


}
