package com.evoke.cartshop.repositories;

import com.evoke.cartshop.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

    Discount findDiscountByItemId(Long id);
}
