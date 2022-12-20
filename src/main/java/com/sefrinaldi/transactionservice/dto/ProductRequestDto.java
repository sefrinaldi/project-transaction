package com.sefrinaldi.transactionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequestDto {

    private List<ProductCartRequestDto> productCartDtoList;
}
