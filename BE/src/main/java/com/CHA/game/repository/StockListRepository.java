package com.CHA.game.repository;

import com.CHA.game.Entity.StockList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockListRepository  extends JpaRepository<StockList, Long> {



    void deleteBystockcode(String stockcode);


}
