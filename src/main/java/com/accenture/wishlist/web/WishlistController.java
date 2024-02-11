package com.accenture.wishlist.web;

import com.accenture.wishlist.business.repository.model.WishlistDTO;
import com.accenture.wishlist.business.service.WishlistService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @GetMapping("wishlist")
    public ResponseEntity<List<WishlistDTO>> getWishlist() {
        return new ResponseEntity<>(wishlistService.getAllWishlist(), HttpStatus.OK);
    }

    @GetMapping("wishlist/{id}")
    public ResponseEntity<WishlistDTO> wishlistDetail(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getWishlistById(id));
    }

    @PostMapping("wishlist")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WishlistDTO> createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        return new ResponseEntity<>(wishlistService.createWishlist(wishlistDTO), HttpStatus.CREATED);
    }

     @PutMapping("wishlist/{id}")
    public ResponseEntity<WishlistDTO> updateWishlist(@RequestBody WishlistDTO wishlistDTO, @PathVariable("id") Long wishlistId) {
        WishlistDTO response = wishlistService.updateWishlist(wishlistDTO, wishlistId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("wishlist/{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return new ResponseEntity<>("Wishlist delete", HttpStatus.OK);
    }
}
