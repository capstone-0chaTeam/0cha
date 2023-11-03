package com.CHA.game.repository;

import com.CHA.game.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByAccount(String account);


    List<Stock> findTop3ByOrderByBalanceDesc();

    Optional<Stock> findById(Long id);


}
