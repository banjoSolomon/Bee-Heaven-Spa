package com.solo.Beespa.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePriceResponse {
    private String message;
    private BigDecimal newPrice;
}
