package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.DTO.GiftItemDTO;
import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.repository.GiftItemRepository;
import com.accenture.wishlist.business.repository.WishlistRepository;
import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import com.accenture.wishlist.model.GiftItem;
import com.accenture.wishlist.model.Role;
import com.accenture.wishlist.model.UserEntity;
import com.accenture.wishlist.model.Wishlist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GiftItemServiceImplTest {

    @InjectMocks
    private GiftItemServiceImpl giftItemService;
    @Mock
    private GiftItemRepository giftItemRepository;
    @Mock
    WishlistRepository wishlistRepository;

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
    private GiftItem giftItem;
    private GiftItemDTO giftItemDTO;

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

        giftItem = GiftItem.builder()
                .gift_item_id(1L)
                .wishlist(wishlist)
                .name("TestName")
                .image_url("testUrl")
                .purchase_url("TestPurchaseUrl")
                .price(10.00)
                .gift_status(GiftStatus.AVAILABLE)
                .priority(Priority.LOW)
                .build();

        giftItemDTO = GiftItemDTO.builder()
                .gift_item_id(1L)
                .name("TestName")
                .image_url("testUrl")
                .purchase_url("TestPurchaseUrl")
                .price(10.00)
                .gift_status(GiftStatus.AVAILABLE)
                .priority(Priority.LOW)
                .build();
    }

    @Test
    public void GiftItemService_CreateItem_ReturnDTO() {
        when(wishlistRepository.findById(wishlist.getId())).thenReturn(Optional.of(wishlist));
        when(giftItemRepository.save(Mockito.any(GiftItem.class))).thenReturn(giftItem);

        GiftItemDTO savedItem = giftItemService.createGiftItem(wishlist.getId(), giftItemDTO);
        Assertions.assertThat(savedItem).isNotNull();
    }

    @Test
    public void GiftItemService_GetItemByWishlistId_ReturnItemDto() {
        Long gift_item_id = 1L;
        when(giftItemRepository.findByWishlistId(gift_item_id)).thenReturn(Arrays.asList(giftItem));
        List<GiftItemDTO> wishlistReturn = giftItemService.getGiftItemsByWishlistId(gift_item_id);
        Assertions.assertThat(wishlistReturn).isNotNull();
    }

    @Test
    public void GiftItemService_GetItemById_ReturnItemDto() {
        Long gift_item_id = 1L;
        Long wishlist_id = 1L;
        when(wishlistRepository.findById(wishlist_id)).thenReturn(Optional.of(wishlist));
        when(giftItemRepository.findById(gift_item_id)).thenReturn(Optional.of(giftItem));

        GiftItemDTO giftItemReturn = giftItemService.getGiftItemById(gift_item_id, wishlist_id);
        Assertions.assertThat(giftItemReturn).isNotNull();
        Assertions.assertThat(giftItemReturn).isNotNull();
    }

    @Test
    public void GiftItemService_UpdateWishlist_ReturnItemDto() {
        Long gift_item_id = 1L;
        Long wishlist_id = 1L;

        when(wishlistRepository.findById(wishlist_id)).thenReturn(Optional.of(wishlist));
        when(giftItemRepository.findById(gift_item_id)).thenReturn(Optional.of(giftItem));
        when(giftItemRepository.save(giftItem)).thenReturn(giftItem);

        GiftItemDTO updateReturn = giftItemService.updateGiftItem(wishlist_id, gift_item_id, giftItemDTO);

        Assertions.assertThat(updateReturn).isNotNull();

    }

    @Test
    public void GiftItemService_DeleteWishlistById_ReturnVoid() {
        Long gift_item_id = 1L;
        Long wishlist_id = 1L;

        when(wishlistRepository.findById(wishlist_id)).thenReturn(Optional.of(wishlist));
        when(giftItemRepository.findById(gift_item_id)).thenReturn(Optional.of(giftItem));

        assertAll(() -> giftItemService.deleteGiftItem(wishlist_id, gift_item_id));
    }
}