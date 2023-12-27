package com.evoke.cartshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="upload_item_image")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @OneToOne
    @JoinColumn(name="item_id",referencedColumnName = "id")
    private Item item;
}
