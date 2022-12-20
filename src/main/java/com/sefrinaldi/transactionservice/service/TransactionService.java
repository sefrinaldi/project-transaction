package com.sefrinaldi.transactionservice.service;

import com.sefrinaldi.transactionservice.client.dto.ProductDto;
import com.sefrinaldi.transactionservice.client.product.ProductRestClient;
import com.sefrinaldi.transactionservice.dto.ProductCartRequestDto;
import com.sefrinaldi.transactionservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CartRepository cartRepository;
    private final ProductRestClient productRestClient;

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
}
