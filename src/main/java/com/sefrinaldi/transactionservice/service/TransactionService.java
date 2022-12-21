package com.sefrinaldi.transactionservice.service;

import com.sefrinaldi.transactionservice.client.dto.ProductDto;
import com.sefrinaldi.transactionservice.client.product.ProductRestClient;
import com.sefrinaldi.transactionservice.dto.ProductCartRequestDto;
import com.sefrinaldi.transactionservice.entity.Cart;
import com.sefrinaldi.transactionservice.entity.Transaction;
import com.sefrinaldi.transactionservice.repository.CartRepository;
import com.sefrinaldi.transactionservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CartRepository cartRepository;
    private final ProductRestClient productRestClient;
    private final CodeGeneratorService codeGeneratorService;
    private final TransactionRepository transactionRepository;

    private static final BigDecimal DEFAULT_SERVICE_CHARGE = BigDecimal.valueOf(1000);

    public void insertToCart(List<ProductCartRequestDto> productCartRequestDtos) {
        productCartRequestDtos.forEach(data -> {
            try {
//                ProductDto productDto = productRestClient.getProductById(data.getCode());

            } catch (RestClientException | NullPointerException e) {
                throw e;
            }
//            Optional<TransactionProduct> transactionProductOptional = transactionProductRepository.findById(data.getId());
//
//            TransactionProduct transactionProduct = new TransactionProduct();
//
//            transactionProductOptional.ifPresentOrElse(product -> {
//                transactionProduct.setProductName(product.getProductName());
//                transactionProduct.setTotalProduct(data.getTotalProduct() + product.getTotalProduct());
//                transactionProduct.setPrice(product.getPrice());
//            }, () -> {
//                transactionProduct.setProductName(data.getProductName());
//                transactionProduct.setTotalProduct(data.getTotalProduct());
//                transactionProduct.setPrice(data.getAmount());
//            });
//
//            transactionProductRepository.save(transactionProduct);
        });
    }

    public Transaction checkoutTransactionFromCart() {

        List<Cart> cartList = cartRepository.findAllByStatus(Cart.Status.DEFAULT);

        if (cartList.isEmpty()) {
            throw new NullPointerException("Item cart not found");
        }

        AtomicInteger totalPrice = new AtomicInteger();

        cartList.forEach(data -> {
            BigDecimal productPrice = data.getPrice().multiply(new BigDecimal(data.getTotalProduct()));
            totalPrice.addAndGet(productPrice.intValue());
        });

        BigDecimal basicPrice = new BigDecimal(String.valueOf(totalPrice.get()));

        Transaction transaction = Transaction.builder()
                .transactionNumber(codeGeneratorService.generateTransactionNumber("PRD/"))
                .basicPrice(basicPrice)
                .serviceCharge(DEFAULT_SERVICE_CHARGE)
                .totalPrice(basicPrice.add(DEFAULT_SERVICE_CHARGE))
                .build();

        transactionRepository.save(transaction);

        cartList.forEach(data -> {
            data.setStatus(Cart.Status.CHECKOUT);
            cartRepository.save(data);
        });

        return transaction;
    }

    public Transaction createTransaction(List<ProductCartRequestDto> productCartRequestDtos) {

        AtomicInteger totalPrice = new AtomicInteger();

        productCartRequestDtos.forEach(data -> {
            ProductDto productDto = productRestClient.getProductByCode(data.getCode());

            BigDecimal productPrice = productDto.getAmount().multiply(new BigDecimal(data.getTotalProduct()));
            totalPrice.addAndGet(productPrice.intValue());
        });

        BigDecimal basicPrice = new BigDecimal(String.valueOf(totalPrice.get()));

        Transaction transaction = Transaction.builder()
                .transactionNumber(codeGeneratorService.generateTransactionNumber("PRD/"))
                .basicPrice(basicPrice)
                .serviceCharge(DEFAULT_SERVICE_CHARGE)
                .totalPrice(basicPrice.add(DEFAULT_SERVICE_CHARGE))
                .build();

        transactionRepository.save(transaction);
        return transaction;
    }
}
