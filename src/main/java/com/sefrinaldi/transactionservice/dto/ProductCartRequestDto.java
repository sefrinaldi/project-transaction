package com.sefrinaldi.transactionservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCartRequestDto {

    private String code;
    private int totalProduct;
}
