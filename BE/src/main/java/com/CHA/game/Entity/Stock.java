package com.CHA.game.Entity;

import com.CHA.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @OneToOne(mappedBy = "user_to_stock")
    private User user;

    @OneToMany(mappedBy = "stocklist_to_stock")
    private List<StockList> stocklist = new ArrayList<>();

    private String account;                 //계좌번호
    private Long balance;                   //통장잔고
    private Long valuationgainandloss_all;  //평가손익
    private Long purchaseamount_all;        //매입금액
    private Long evaluationamount_all;      //평가금액



}
