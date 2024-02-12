package com.accenture.wishlist.web;

import com.accenture.wishlist.business.repository.DTO.WishlistDTO;
import com.accenture.wishlist.business.repository.DTO.WishlistResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @GetMapping()
    public ResponseEntity<WishlistResponse> getWishlist(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return new ResponseEntity<>(wishlistService.getAllWishlist(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<WishlistDTO> wishlistDetail(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getWishlistById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WishlistDTO> createWishlist(@RequestBody WishlistDTO wishlistDTO) {
        return new ResponseEntity<>(wishlistService.createWishlist(wishlistDTO), HttpStatus.CREATED);
    }

     @PutMapping("{id}")
    public ResponseEntity<WishlistDTO> updateWishlist(@RequestBody WishlistDTO wishlistDTO, @PathVariable("id") Long wishlistId) {
        WishlistDTO response = wishlistService.updateWishlist(wishlistDTO, wishlistId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") Long wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
        return new ResponseEntity<>("Wishlist delete", HttpStatus.OK);
    }
}
