package com.accenture.wishlist.business.repository;

import com.accenture.wishlist.business.repository.model.GiftItemDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftItemRepository extends JpaRepository<GiftItemDAO, Long> {
}
