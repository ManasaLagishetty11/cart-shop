package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="item_id",referencedColumnName = "id")
    private Item item;

    @Column(name="discount_percentage")
    private Double discountPercentage;

    @Column(name="is_discount_available")
    private boolean isDiscountAvailable;

    @Transient
    private Double offerPrice;

}
