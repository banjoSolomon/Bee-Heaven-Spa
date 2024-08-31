package com.solo.Beespa.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateServicePriceRequest {
    private Long serviceId;
    private BigDecimal newPrice;

}
