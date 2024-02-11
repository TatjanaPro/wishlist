package com.accenture.wishlist.business.repository;

import com.accenture.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}
