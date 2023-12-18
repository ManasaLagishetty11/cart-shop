package com.evoke.cartshop.repositories;

import com.evoke.cartshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findCartByUserId(Long userId);
}
