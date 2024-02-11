package com.accenture.wishlist.business.service;

import com.accenture.wishlist.business.repository.model.WishlistDTO;
import com.accenture.wishlist.model.Wishlist;

import java.util.List;

public interface WishlistService {

    WishlistDTO createWishlist(WishlistDTO wishlistDTO);
    List<WishlistDTO> getAllWishlist();
    WishlistDTO getWishlistById(Long id);
    WishlistDTO updateWishlist(WishlistDTO wishlistDTO, Long id);
    void deleteWishlist(Long id);

}
