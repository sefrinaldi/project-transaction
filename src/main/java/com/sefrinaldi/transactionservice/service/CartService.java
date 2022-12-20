package com.sefrinaldi.transactionservice.service;

import com.sefrinaldi.transactionservice.client.dto.ProductDto;
import com.sefrinaldi.transactionservice.client.product.ProductRestClient;
import com.sefrinaldi.transactionservice.dto.ProductCartRequestDto;
import com.sefrinaldi.transactionservice.entity.Cart;
import com.sefrinaldi.transactionservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRestClient productRestClient;
    private final CartRepository cartRepository;

    public void insertToCart(List<ProductCartRequestDto> productCartRequestDtos) {
        productCartRequestDtos.forEach(data -> {
            try {
                ProductDto productDto = productRestClient.getProductByCode(data.getCode());

                Optional<Cart> cartOptional = cartRepository.findByCode(productDto.getCode());

                cartOptional.ifPresentOrElse(cart -> {
                    cart.setTotalProduct(cart.getTotalProduct() + data.getTotalProduct());
                    cartRepository.save(cart);
                }, () -> {
                    Cart cart = Cart.builder()
                            .code(data.getCode())
                            .productName(productDto.getLabel())
                            .totalProduct(data.getTotalProduct())
                            .price(productDto.getAmount())
                            .build();
                    cartRepository.save(cart);
                });

            } catch (RestClientException | NullPointerException e) {
                throw e;
            }
        });
    }

    public void removeItemCart(List<ProductCartRequestDto> productCartRequestDtos) {
        productCartRequestDtos.forEach(data -> {
            Optional<Cart> cartOptional = cartRepository.findByCodeAndStatus(data.getCode(), Cart.Status.DEFAULT);

            cartOptional.ifPresentOrElse(cartRepository::delete, cartOptional::orElseThrow);
        });
    }
}
