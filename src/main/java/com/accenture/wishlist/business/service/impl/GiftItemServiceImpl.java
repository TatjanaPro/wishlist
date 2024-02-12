package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.repository.DTO.GiftItemDTO;
import com.accenture.wishlist.business.repository.GiftItemRepository;
import com.accenture.wishlist.business.repository.WishlistRepository;
import com.accenture.wishlist.business.service.GiftItemService;
import com.accenture.wishlist.exceptions.GiftItemNotFoundException;
import com.accenture.wishlist.exceptions.WishlistNotFoundException;
import com.accenture.wishlist.model.GiftItem;
import com.accenture.wishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftItemServiceImpl implements GiftItemService {

    private GiftItemRepository giftItemRepository;

    private WishlistRepository wishlistRepository;

    @Autowired
    public GiftItemServiceImpl(GiftItemRepository giftItemRepository, WishlistRepository wishlistRepository) {
        this.giftItemRepository = giftItemRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public GiftItemDTO createGiftItem(Long wishlistId, GiftItemDTO giftItemDTO) {
        GiftItem giftItem = mapToEntity(giftItemDTO);

        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException("Wishlist with associated gift item not found"));
        giftItem.setWishlist(wishlist);
        GiftItem newGiftItem = giftItemRepository.save(giftItem);
        return mapToDto(newGiftItem);
    }

    @Override
    public List<GiftItemDTO> getGiftItemsByWishlistId(Long id) {
       List<GiftItem> giftItems = giftItemRepository.findByWishlistId(id);

       return giftItems.stream().map(giftItem -> mapToDto(giftItem)).collect(Collectors.toList());
    }

    @Override
    public GiftItemDTO getGigtItemById(Long giftItemId, Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException("Wishlist with associated gift item not found"));

        GiftItem giftItem = giftItemRepository.findById(giftItemId).orElseThrow(() -> new GiftItemNotFoundException("Gift Item with associated wishlist not found"));

        if(giftItem.getWishlist().getId() != wishlist.getId()) { //not the smartest solution...use equals instead???
            throw new GiftItemNotFoundException("This gift item does not belong to a wishlist");
        }
        return mapToDto(giftItem);
    }

    @Override
    public GiftItemDTO updateGiftItem(Long wishlistId, Long giftItemId, GiftItemDTO giftItemDTO) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException("Wishlist with associated gift item not found"));

        GiftItem giftItem = giftItemRepository.findById(giftItemId).orElseThrow(() -> new GiftItemNotFoundException("Gift Item with associated wishlist not found"));

        if (giftItem.getWishlist().getId() != wishlist.getId()) { //not the smartest solution...use equals instead???
            throw new GiftItemNotFoundException("This gift item does not belong to a wishlist");
        }
        giftItem.setName(giftItemDTO.getName());
        giftItem.setImage_url(giftItemDTO.getImage_url());
        giftItem.setPurchase_url(giftItemDTO.getPurchase_url());
        giftItem.setPrice(giftItemDTO.getPrice());
        giftItem.setGift_status(giftItemDTO.getGift_status());
        giftItem.setPriority(giftItemDTO.getPriority());

        GiftItem updateGiftItem = giftItemRepository.save(giftItem);

        return mapToDto(updateGiftItem);
    }

    @Override
    public void deleteGiftItem(Long wishlistId, Long giftItemId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException("Wishlist with associated gift item not found"));

        GiftItem giftItem = giftItemRepository.findById(giftItemId).orElseThrow(() -> new GiftItemNotFoundException("Gift Item with associated wishlist not found"));

        if (giftItem.getWishlist().getId() != wishlist.getId()) { //not the smartest solution...use equals instead???
            throw new GiftItemNotFoundException("This gift item does not belong to a wishlist");
        }

        giftItemRepository.delete(giftItem);
    }

    private GiftItemDTO mapToDto(GiftItem giftItem) {
        GiftItemDTO giftItemDTO = new GiftItemDTO();
        giftItemDTO.setGift_item_id(giftItem.getGift_item_id());
        giftItemDTO.setName(giftItem.getName());
        giftItemDTO.setImage_url(giftItem.getImage_url());
        giftItemDTO.setPurchase_url(giftItem.getPurchase_url());
        giftItemDTO.setPrice(giftItem.getPrice());
        giftItemDTO.setGift_status(giftItem.getGift_status());
        giftItemDTO.setPriority(giftItem.getPriority());

        return giftItemDTO;
    }

    private GiftItem mapToEntity(GiftItemDTO giftItemDTO) {
        GiftItem giftItem = new GiftItem();
        giftItem.setGift_item_id(giftItemDTO.getGift_item_id());
        giftItem.setName(giftItemDTO.getName());
        giftItem.setImage_url(giftItemDTO.getImage_url());
        giftItem.setPurchase_url(giftItemDTO.getPurchase_url());
        giftItem.setPrice(giftItemDTO.getPrice());
        giftItem.setGift_status(giftItemDTO.getGift_status());
        giftItem.setPriority(giftItemDTO.getPriority());

        return giftItem;

    }

}
