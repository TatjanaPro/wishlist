package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.repository.UserRepository;
import com.accenture.wishlist.business.repository.WishlistRepository;
import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.DTO.WishlistResponse;
import com.accenture.wishlist.business.service.WishlistService;
import com.accenture.wishlist.exceptions.EntityNotFoundException;
import com.accenture.wishlist.exceptions.WishlistNotFoundException;
import com.accenture.wishlist.model.UserEntity;
import com.accenture.wishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.accenture.wishlist.business.DTO.UserDTO.mapToUser;
import static com.accenture.wishlist.business.DTO.UserDTO.mapToUserDto;

@Service
public class WishListServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;
    private UserRepository userRepository;

    @Autowired //on constructor because it is easy to test
    public WishListServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WishlistDTO createWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistDTO.getId());
        UserEntity owner = mapToUser(wishlistDTO.getOwner());
        wishlist.setOwner(owner);
        wishlist.setTitle(wishlistDTO.getTitle());
        wishlist.setDescription(wishlistDTO.getDescription());
        wishlist.setEvent_category(wishlistDTO.getEvent_category());
        wishlist.setStart_date(wishlistDTO.getStart_date());
        wishlist.setEnd_date(wishlistDTO.getEnd_date());

        Wishlist newWishlist = wishlistRepository.save(wishlist);

        WishlistDTO wishlistResponse = new WishlistDTO();
        wishlistResponse.setId(newWishlist.getId());
        UserDTO ownerDTO = mapToUserDto(owner);
        wishlistResponse.setOwner(ownerDTO);
        wishlistResponse.setTitle(newWishlist.getTitle());
        wishlistResponse.setDescription(newWishlist.getDescription());
        wishlistResponse.setEvent_category(newWishlist.getEvent_category());
        wishlistResponse.setStart_date(newWishlist.getStart_date());
        wishlistResponse.setEnd_date(newWishlist.getEnd_date());

        return wishlistResponse;
    }

    @Override
    public WishlistResponse getAllWishlist(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Wishlist> wishlist = wishlistRepository.findAll(pageable);
        List<Wishlist> listOfWishlist = wishlist.getContent();
        List<WishlistDTO> content = listOfWishlist.stream().map(w -> mapToDto(w)).collect(Collectors.toList());

        WishlistResponse wishlistResponse = new WishlistResponse();
        wishlistResponse.setContent(content);
        wishlistResponse.setPageNo(wishlist.getNumber());
        wishlistResponse.setPageSize(wishlist.getSize());
        wishlistResponse.setTotalElements(wishlist.getTotalElements());
        wishlistResponse.setTotalPages(wishlist.getTotalPages());
        wishlistResponse.setLast(wishlist.isLast());

        return wishlistResponse;
    }

/*    @Override
    public WishlistDTO getWishlistById(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be found"));
        return mapToDto(wishlist);
    }*/

    @Override
    public WishlistDTO getWishlistById(Long id) {
        Wishlist wishlist = wishlistRepository.findByIdWithCollaborators(id)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be found"));
        return mapToDto(wishlist);
    }

    @Override
    public WishlistDTO updateWishlist(WishlistDTO wishlistDTO, Long id) {
        Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(() -> new WishlistNotFoundException("Wishlist could not be updated"));
        UserEntity owner = mapToUser(wishlistDTO.getOwner());
        wishlist.setOwner(owner);
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

    @Override
    public void addCollaboratorToWishlist(Long id, Long userId) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        wishlist.getCollaborators().add(user);
        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeCollaboratorFromWishlist(Long wishlistId, Long userId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new WishlistNotFoundException("Wishlist not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        wishlist.getCollaborators().remove(user);
        wishlistRepository.save(wishlist);
    }


    private WishlistDTO mapToDto(Wishlist wishlist) {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setId(wishlist.getId());
        UserDTO ownerDTO = mapToUserDto(wishlist.getOwner());
        wishlistDTO.setOwner(ownerDTO);
        List<UserDTO> collaboratorDTO = wishlist.getCollaborators().stream()
                        .map(collaborator -> mapToUserDto(collaborator))
                                .collect(Collectors.toList());
        wishlistDTO.setCollaborators(collaboratorDTO);
        wishlistDTO.setTitle(wishlist.getTitle());
        wishlistDTO.setDescription(wishlist.getDescription());
        wishlistDTO.setEvent_category(wishlist.getEvent_category());
        wishlistDTO.setStart_date(wishlist.getStart_date());
        wishlistDTO.setEnd_date(wishlist.getEnd_date());
        return wishlistDTO;
    }

    private Wishlist mapToEntity(WishlistDTO wishlistDTO) {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistDTO.getId());
        UserEntity owner = mapToUser(wishlistDTO.getOwner());
        wishlist.setOwner(owner);
        wishlist.setTitle(wishlistDTO.getTitle());
        wishlist.setDescription(wishlistDTO.getDescription());
        wishlist.setEvent_category(wishlistDTO.getEvent_category());
        wishlist.setStart_date(wishlistDTO.getStart_date());
        wishlist.setEnd_date(wishlistDTO.getEnd_date());
        return wishlist;
    }
}
