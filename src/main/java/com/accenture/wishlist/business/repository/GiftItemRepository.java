package com.accenture.wishlist.business.repository;

import com.accenture.wishlist.model.GiftItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {

    List<GiftItem> findByWishlistId(Long wishlistId);
}
