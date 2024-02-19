package com.accenture.wishlist.business.repository;

import com.accenture.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    @Query("SELECT DISTINCT w FROM Wishlist w LEFT JOIN FETCH w.collaborators WHERE w.id = :wishlistId")
    Optional<Wishlist> findByIdWithCollaborators(@Param("wishlistId") Long wishlistId);

}
