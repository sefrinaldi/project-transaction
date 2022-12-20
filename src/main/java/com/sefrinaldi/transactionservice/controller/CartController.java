package com.sefrinaldi.transactionservice.controller;

import com.sefrinaldi.transactionservice.dto.ProductCartRequestDto;
import com.sefrinaldi.transactionservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-service")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/insert-cart")
    public String insertCart(@RequestBody List<ProductCartRequestDto> productCartRequestDtos) {
        cartService.insertToCart(productCartRequestDtos);
        return "Product berhasil di tambahkan";
    }

    @DeleteMapping("/remove-item")
    public String removeItem(@RequestBody List<ProductCartRequestDto> productCartRequestDtos) {
        cartService.removeItemCart(productCartRequestDtos);
        return "Product berhasil dihapus";
    }
}
