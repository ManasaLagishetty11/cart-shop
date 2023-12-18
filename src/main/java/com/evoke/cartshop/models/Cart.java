package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="number_of_items")
    private int numberOfItems;

    @Column(name="total_value_of_items")
    private double totalValueOfItems;

    @Column(name="is_visible")
    private boolean isVisible = true;
}
