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
@Slf4j
public class GameController {
    //10-28일

    private final StockListService stockListService;
    private final StockService stockService;



    //매도하기 수량 불러오기
    @GetMapping("/stockquantity")
    public ResponseEntity<StockQuantityDTO> StockQuantity(@RequestParam String account , @RequestParam String stockcode) {
        // 경로 변수로 받은 account와 quantityheld 값을 사용하여 요청을 처리

        StockQuantityDTO stockList = stockListService.StockQuantity(account , stockcode);

        return ResponseEntity.ok(stockList);
    }

    @GetMapping("/stockcash")
    public ResponseEntity<StockCashDTO> StockCash(@RequestParam String account){
        return ResponseEntity.ok().body(stockListService.StockCash(account));
    }

    @GetMapping("/start/stockList")
    public ResponseEntity<List<StartStockListDTO>> startStockList(@RequestParam String account){
        return ResponseEntity.ok().body(stockService.startStockList(account));
    }





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
//    @PostMapping("/save-stockList")
//    public ResponseEntity<StockListDTO> saveStockList(@RequestBody StockListDTO stockListDTO) {
//        StockListDTO list = stockListService.saveStockList(stockListDTO);
//        System.out.println(list);
//        return ResponseEntity.ok().body(list);
//    }
//
//    @PostMapping("/save-stockList")
//    public ResponseEntity<String>  newsaveStockList2(@RequestBody StockListDTO2 stockListDTO2) {
//        return ResponseEntity.ok().body("성공");
//
//    }



    //
//    아래의 형식
//    [
//    {
//        "id": 35,
//            "stockcode": "LG",
//            "valuationgainandloss": 112323,
//            "purchaseamount": 121233,
//            "evaluationamount": 121233,
//            "stockreturns": 121233,
//            "purchaseprice": 121233,
//            "quantityheld": 121233
//    },
//    {
//        "id": 36,
//            "stockcode": "LG",
//            "valuationgainandloss": 112323,
//            "purchaseamount": 121233,
//            "evaluationamount": 121233,
//            "stockreturns": 121233,
//            "purchaseprice": 121233,
//            "quantityheld": 121233
//    }
//]
    @GetMapping("/who_buyList")
    public ResponseEntity<List<StockList>> showStockList(@RequestParam("account") String account)  {

        System.out.println(account);

        List<StockList> stockList = stockService.showStock(account);

        System.out.println(stockList.toString());
        return ResponseEntity.ok().body(stockList);
    }


    @GetMapping("/who_buyList2")
    public ResponseEntity<List<StockList>> showStockList2(@RequestParam("account") String account)  {

        System.out.println(account);

        List<StockList> stockList = stockService.showStock(account);

        System.out.println(stockList.toString());
        return ResponseEntity.ok().body(stockList);
    }

//      아래의 형식
//    {
//        "account": "54457329",
//            "stockList": [
//        {
//            "id": 35,
//                "stockcode": "LG",
//                "valuationgainandloss": 112323,
//                "purchaseamount": 121233,
//                "evaluationamount": 121233,
//                "stockreturns": 121233,
//                "purchaseprice": 121233,
//                "quantityheld": 121233
//        },
//        {
//            "id": 36,
//                "stockcode": "LG",
//                "valuationgainandloss": 112323,
//                "purchaseamount": 121233,
//                "evaluationamount": 121233,
//                "stockreturns": 121233,
//                "purchaseprice": 121233,
//                "quantityheld": 121233
//        },
//    ]
//    }

//    @GetMapping("/who_buyList2")
//    public ResponseEntity<StockListDTO2> showStockList2(@RequestParam("account") String account){
//
//        System.out.println(account);
//
//        List<StockList> stockList = stockService.showStock(account);
//
//        StockListDTO2 stocklist2 = StockListDTO2.builder()
//                .account(account)
//                .stockList(stockList)
//                .build();
//
//        return ResponseEntity.ok().body(stocklist2);
//
//    }
}
