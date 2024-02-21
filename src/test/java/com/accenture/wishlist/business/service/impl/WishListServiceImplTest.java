package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.DTO.WishlistResponse;
import com.accenture.wishlist.business.repository.WishlistRepository;
import com.accenture.wishlist.model.Role;
import com.accenture.wishlist.model.UserEntity;
import com.accenture.wishlist.model.Wishlist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListServiceImplTest {

    @InjectMocks
    private WishListServiceImpl wishListService;
    @Mock
    private WishlistRepository wishlistRepository;

    private Wishlist wishlist;
    private WishlistDTO wishlistDTO;
    private UserEntity entity;
    private UserDTO userDTO;
    private UserEntity collaborator;
    private UserEntity collaborator2;
    private List<UserEntity> collaborators;
    private UserDTO collaboratorDTO;
    private UserDTO collaboratorTwoDTO;
    private List<UserDTO> collaboratorsDTO;
    private Role role;
    private Role roleTwo;
    private List<Role> roles;
    private List<Wishlist> wishlists;
    private List<Wishlist> wishlistList;
    private Wishlist wishlistTwo;
    private  WishlistDTO wishlistTwoDTO;
    private List<WishlistDTO> wishlistDTOs;

    @BeforeEach
    public void setUp() {

        wishlists = new ArrayList<>();
        wishlistList = new ArrayList<>();

        role = Role.builder()
                .id(50L)
                .name("User")
                .build();

        roleTwo = Role.builder()
                .id(60L)
                .name("Admin")
                .build();

        roles = Arrays.asList(role, roleTwo);

        entity = UserEntity.builder() //UserEntity owner = UserEntity.builder()
                .id(10L)
                .username("Tatjana")
                .password("password")
                .roles(roles)
                .wishlists(wishlists)
                .wishlistList(wishlistList)
                .build();

        userDTO = UserDTO.builder() //UserDTO ownerDTO = UserDTO.builder()
                .id(10L)
                .username("Tatjana")
                .build();

        collaborator = UserEntity.builder() //UserEntity collaborator = UserEntity.builder()
                .id(20L)
                .username("TatjanaCollaborator")
                .password("password")
                .roles(roles)
                .wishlists(wishlists)
                .wishlistList(wishlistList)
                .build();

        collaborator2 = UserEntity.builder() //UserEntity collaborator2 = UserEntity.builder()
                .id(30L)
                .username("PolinaCollaborator")
                .password("password")
                .roles(roles)
                .wishlists(wishlists)
                .wishlistList(wishlistList)
                .build();

        collaborators = Arrays.asList(collaborator, collaborator2);

        collaboratorDTO = UserDTO.builder()
                .id(20L)
                .username("TatjanaCollaborator")
                .build();

        collaboratorTwoDTO = UserDTO.builder()
                .id(30L)
                .username("PolinaCollaborator")
                .build();

        collaboratorsDTO = Arrays.asList(collaboratorDTO, collaboratorTwoDTO);

        wishlist = Wishlist.builder(). //Wishlist wishlist = Wishlist.builder().
                id(1L)
                .owner(entity)
                .title("TestTitle")
                .description("TestDescription")
                .event_category("TestCategory")
                .start_date("2023-12-01")
                .end_date("2023-12-31")
                .collaborators(collaborators)
                .build();

        wishlistTwo = Wishlist.builder().
                id(2L)
                .owner(entity)
                .title("TestTitle2")
                .description("TestDescription2")
                .event_category("TestCategory2")
                .start_date("2023-12-01")
                .end_date("2023-12-31")
                .collaborators(collaborators)
                .build();

        wishlists = Arrays.asList(wishlist, wishlistTwo);

        wishlistDTO = WishlistDTO.builder(). //WishlistDTO wishlistDTO = WishlistDTO.builder().
                id(1L)
                .owner(userDTO)
                .title("TestTitle")
                .description("TestDescription")
                .event_category("TestCategory")
                .start_date("2023-12-01")
                .end_date("2023-12-31")
                .collaborators(collaboratorsDTO)
                .build();

        wishlistTwoDTO = WishlistDTO.builder().
                id(2L)
                .owner(userDTO)
                .title("TestTitle2")
                .description("TestDescription2")
                .event_category("TestCategory2")
                .start_date("2023-12-01")
                .end_date("2023-12-31")
                .collaborators(collaboratorsDTO)
                .build();

        wishlistDTOs = Arrays.asList(wishlistDTO, wishlistTwoDTO);
    }

    @Test
    public void WishlistService_CreateWishlist_ReturnWishlistDTO() {

        wishlistList = Arrays.asList(wishlist, wishlistTwo);
        List<WishlistDTO> wishlistDTOs = Arrays.asList(wishlistDTO, wishlistTwoDTO);

        when(wishlistRepository.save((Mockito.any(Wishlist.class)))).thenReturn(wishlist);

        WishlistDTO savedWishlist = wishListService.createWishlist(wishlistDTO);

        Assertions.assertThat(savedWishlist).isNotNull();
    }

    @Test
    public void WishlistService_GetAllWishlists_ReturnsResponseDto() {
        Page<Wishlist> wishlists = Mockito.mock(Page.class);

        when(wishlistRepository.findAll(Mockito.any(Pageable.class))).thenReturn(wishlists);

        WishlistResponse saveWishlist = wishListService.getAllWishlist(1, 10);

        Assertions.assertThat(saveWishlist).isNotNull();
    }

    @Test
    public void WishlistService_GetWishlistById_ReturnWishlistDTO() {
        when(wishlistRepository.findByIdWithCollaborators(1L)).thenReturn(Optional.ofNullable(wishlist));

        WishlistDTO savedWishlist = wishListService.getWishlistById(1L);

        Assertions.assertThat(savedWishlist).isNotNull();
    }

    @Test
    public void WishlistService_UpdateWishlist_ReturnWishlistDTO() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.ofNullable(wishlist));
        when(wishlistRepository.save((Mockito.any(Wishlist.class)))).thenReturn(wishlist);

        WishlistDTO savedWishlist = wishListService.updateWishlist(wishlistDTO, 1L);

        Assertions.assertThat(savedWishlist).isNotNull();
    }

    @Test
    public void WishlistService_DeleteWishlistById_ReturnWishlistDTO() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.ofNullable(wishlist));

        assertAll(() -> wishListService.deleteWishlist(1L));
    }
}