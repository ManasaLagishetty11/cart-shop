package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="category")
    @Enumerated(EnumType.ORDINAL)
    private ItemCategory category;

    @Column(name="is_available")
    private boolean availability;

    @Column(name="number_of_items")
    private Long numberOfItems;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="status")
    private ItemStatus status;

    @OneToOne(mappedBy = "item",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Discount discount;
}
