package com.accenture.wishlist.business.repository;

import com.accenture.wishlist.business.repository.model.WishlistDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishlistDAO, Long> {
}
