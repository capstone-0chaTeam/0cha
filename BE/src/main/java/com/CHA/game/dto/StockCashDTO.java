package com.CHA.game.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockCashDTO {
    private Long cash;

    public Long getCash() {
        return cash;
    }
}
