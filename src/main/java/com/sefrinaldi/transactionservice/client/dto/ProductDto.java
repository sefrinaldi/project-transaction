package com.sefrinaldi.transactionservice.client.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Long id;
    private String code;
    private String label;
    private BigDecimal amount;
}
