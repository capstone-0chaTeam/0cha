package com.CHA.game.repository;

import com.CHA.game.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByAccount(String account);


    @Query("select s from Stock s join fetch s.user")
    List<Stock> findTop20ByOrderByBalanceDesc();

    Optional<Stock> findById(Long id);




}
