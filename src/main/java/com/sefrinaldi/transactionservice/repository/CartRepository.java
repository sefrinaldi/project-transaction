package com.sefrinaldi.transactionservice.repository;

import com.sefrinaldi.transactionservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCode(String code);
    Optional<Cart> findByCodeAndStatus(String code, Cart.Status status);
    List<Cart> findAllByStatus(Cart.Status status);
}