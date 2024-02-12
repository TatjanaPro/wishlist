package com.accenture.wishlist.business.service;

import com.accenture.wishlist.business.repository.DTO.GiftItemDTO;
import java.util.List;
public interface GiftItemService {

    GiftItemDTO createGiftItem(Long wishlistId, GiftItemDTO giftItemDTO);
    List<GiftItemDTO> getGiftItemsByWishlistId(Long wishlistId);
    GiftItemDTO getGiftItemById(Long wishlistId, Long giftItemId);
    GiftItemDTO updateGiftItem(Long wishlistId, Long giftItemId, GiftItemDTO giftItemDTO); //dto to specify the actual gift item to update
    void deleteGiftItem(Long wishlistId, Long giftItemId); //wishlistid for many to one relationship. To find relationships
}
