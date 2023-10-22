package com.CHA.game.Entity;

import com.CHA.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long id;
    @OneToOne(mappedBy = "stock")
    private User user;

    private Long balance;

    private Long valuationgainandloss;
    private Long purchaseamount;
    private Long evaluationamount;



}
