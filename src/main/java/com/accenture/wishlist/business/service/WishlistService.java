package com.accenture.wishlist.business.service;

import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.DTO.WishlistResponse;

public interface WishlistService {

    WishlistDTO createWishlist(WishlistDTO wishlistDTO);
    WishlistResponse getAllWishlist(int pageNo, int pageSize);
    WishlistDTO getWishlistById(Long id);
    WishlistDTO updateWishlist(WishlistDTO wishlistDTO, Long id);
    void deleteWishlist(Long id);
    void addCollaboratorToWishlist(Long id, Long userId);
    void removeCollaboratorFromWishlist(Long id, Long userId);

}
