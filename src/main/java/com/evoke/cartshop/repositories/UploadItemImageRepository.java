package com.evoke.cartshop.repositories;

import com.evoke.cartshop.models.UploadItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadItemImageRepository extends JpaRepository<UploadItemImage,Long> {
}
