package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="area")
    private String area;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="zipcode")
    private String zipcode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

}
