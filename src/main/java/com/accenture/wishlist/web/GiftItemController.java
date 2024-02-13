package com.accenture.wishlist.web;

import com.accenture.wishlist.business.DTO.GiftItemDTO;
import com.accenture.wishlist.business.service.GiftItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GiftItemController {

    private GiftItemService giftItemService;

    @Autowired
    public GiftItemController(GiftItemService giftItemService) {
        this.giftItemService = giftItemService;
    }

    @PostMapping("/wishlist/{wishlistId}/gift/items")
    public ResponseEntity<GiftItemDTO> createGiftItem(@PathVariable(value="wishlistId") Long wishlist_id,
                                                      @RequestBody GiftItemDTO giftItemDTO) {
        return new ResponseEntity<>(giftItemService.createGiftItem(wishlist_id, giftItemDTO), HttpStatus.CREATED);
    }

    @GetMapping("/wishlist/{wishlistId}/gift/items")
    public List<GiftItemDTO> getGiftsByWishlistId(@PathVariable(value = "wishlistId") Long wishlistId) {
        return giftItemService.getGiftItemsByWishlistId(wishlistId);
    }

    @GetMapping("/wishlist/{wishlistId}/gift/items/{id}") //check via postmas cause last time wishlistid and id switched places
    public ResponseEntity<GiftItemDTO> getGiftItemById(@PathVariable(value = "wishlistId") Long wishlistId,
                                                       @PathVariable(value = "id") Long giftItemId) {
        GiftItemDTO giftItemDTO = giftItemService.getGiftItemById(wishlistId, giftItemId);
        return new ResponseEntity<>(giftItemDTO, HttpStatus.OK);
    }

    @PutMapping("/wishlist/{wishlistId}/gift/items/{id}")
    public ResponseEntity<GiftItemDTO> updateGiftItem(@PathVariable(value = "wishlistId") Long wishlistId,
                                                      @PathVariable(value = "id") Long giftItemId,
                                                      @RequestBody GiftItemDTO giftItemDTO) {
        GiftItemDTO updatedGiftItem = giftItemService.updateGiftItem(wishlistId, giftItemId, giftItemDTO);
        return new ResponseEntity<>(updatedGiftItem, HttpStatus.OK);
    }

    @DeleteMapping("/wishlist/{wishlistId}/gift/items/{id}")
    public ResponseEntity<String> deleteGiftItem(@PathVariable(value = "wishlistId") Long wishlistId,
                                                 @PathVariable(value = "id") Long giftItemId) {
        giftItemService.deleteGiftItem(wishlistId, giftItemId);
        return new ResponseEntity<>("Gift item deleted successfully", HttpStatus.OK);
    }
}
