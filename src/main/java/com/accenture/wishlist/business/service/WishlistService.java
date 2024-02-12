package com.accenture.wishlist.business.service;

import com.accenture.wishlist.business.repository.DTO.WishlistDTO;
import com.accenture.wishlist.business.repository.DTO.WishlistResponse;

public interface WishlistService {

    WishlistDTO createWishlist(WishlistDTO wishlistDTO);
    WishlistResponse getAllWishlist(int pageNo, int pageSize);
    WishlistDTO getWishlistById(Long id);
    WishlistDTO updateWishlist(WishlistDTO wishlistDTO, Long id);
    void deleteWishlist(Long id);

}
