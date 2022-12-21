package com.sefrinaldi.transactionservice.controller;

import com.sefrinaldi.transactionservice.dto.ProductCartRequestDto;
import com.sefrinaldi.transactionservice.entity.Transaction;
import com.sefrinaldi.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction-service")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/checkout-transaction")
    public Transaction checkoutTransaction() {
        return transactionService.checkoutTransactionFromCart();
    }

    @PostMapping("/create-transaction")
    public Transaction createTransaction(@RequestBody List<ProductCartRequestDto> productCartRequestDtos) {
        return transactionService.createTransaction(productCartRequestDtos);
    }
}
