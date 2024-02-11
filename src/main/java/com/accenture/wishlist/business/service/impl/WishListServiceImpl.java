package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.repository.WishlistRepository;
import com.accenture.wishlist.business.repository.model.WishlistDTO;
import com.accenture.wishlist.business.service.WishlistService;
import com.accenture.wishlist.exceptions.WishlistNotFoundException;
import com.accenture.wishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;

    @Autowired //on constructor because it is easy to test
    public WishListServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public WishlistDTO createWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setOwner_id(wishlistDTO.getOwner_id());
        wishlist.setTitle(wishlistDTO.getTitle());
        wishlist.setDescription(wishlistDTO.getDescription());
        wishlist.setEvent_category(wishlistDTO.getEvent_category());
        wishlist.setStart_date(wishlistDTO.getStart_date());
        wishlist.setEnd_date(wishlistDTO.getEnd_date());

        Wishlist newWishlist = wishlistRepository.save(wishlist);

        WishlistDTO wishlistResponse = new WishlistDTO();
        wishlistResponse.setOwner_id(newWishlist.getOwner_id());
        wishlistResponse.setTitle(newWishlist.getTitle());
        wishlistResponse.setDescription(newWishlist.getDescription());
        wishlistResponse.setEvent_category(newWishlist.getEvent_category());
        wishlistResponse.setStart_date(newWishlist.getStart_date());
        wishlistResponse.setEnd_date(newWishlist.getEnd_date());

        return wishlistResponse;
    }

    @Override
    public List<WishlistDTO> getAllWishlist() {
        List<Wishlist> wishlist = wishlistRepository.findAll();
        return wishlist.stream().map(w -> mapToDto(w)).collect(Collectors.toList());
    }

    @Override
    public WishlistDTO getWishlistById(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be found"));
        return mapToDto(wishlist);
    }

    @Override
    public WishlistDTO updateWishlist(WishlistDTO wishlistDTO, Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be updated"));
        wishlist.setOwner_id(wishlistDTO.getOwner_id());
        wishlist.setTitle(wishlistDTO.getTitle());
        wishlist.setDescription(wishlistDTO.getDescription());
        wishlist.setEvent_category(wishlistDTO.getEvent_category());
        wishlist.setStart_date(wishlistDTO.getStart_date());
        wishlist.setEnd_date(wishlistDTO.getEnd_date());

        Wishlist updatedWishlist = wishlistRepository.save(wishlist);

        return mapToDto(updatedWishlist);
    }

    @Override
    public void deleteWishlist(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be deleted"));
        wishlistRepository.delete(wishlist);
    }

    private WishlistDTO mapToDto(Wishlist wishlist) {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setWishlist_id(wishlist.getWishlist_id());
        wishlistDTO.setOwner_id(wishlist.getOwner_id());
        wishlistDTO.setTitle(wishlist.getTitle());
        wishlistDTO.setDescription(wishlist.getDescription());
        wishlistDTO.setEvent_category(wishlist.getEvent_category());
        wishlistDTO.setStart_date(wishlist.getStart_date());
        wishlistDTO.setEnd_date(wishlist.getEnd_date());
        return wishlistDTO;
    }

    private Wishlist mapToEntity(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlist_id(wishlistDTO.getWishlist_id());
        wishlist.setOwner_id(wishlistDTO.getOwner_id());
        wishlist.setTitle(wishlistDTO.getTitle());
        wishlist.setDescription(wishlistDTO.getDescription());
        wishlist.setEvent_category(wishlistDTO.getEvent_category());
        wishlist.setStart_date(wishlistDTO.getStart_date());
        wishlist.setEnd_date(wishlistDTO.getEnd_date());
        return wishlist;
    }
}
