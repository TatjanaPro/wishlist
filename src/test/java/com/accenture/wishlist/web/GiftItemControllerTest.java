package com.accenture.wishlist.web;

import com.accenture.wishlist.business.DTO.GiftItemDTO;
import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.service.GiftItemService;
import com.accenture.wishlist.business.service.UserService;
import com.accenture.wishlist.business.service.WishlistService;
import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import com.accenture.wishlist.model.GiftItem;
import com.accenture.wishlist.model.Role;
import com.accenture.wishlist.model.UserEntity;
import com.accenture.wishlist.model.Wishlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(GiftItemController.class)
@AutoConfigureMockMvc(addFilters = false)
class GiftItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GiftItemService giftItemService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void GiftItemController_GetItemsByWishlistId_ReturnItemDto() throws Exception {
        Long wishlistId = 1L;
        when(giftItemService.getGiftItemsByWishlistId(wishlistId)).thenReturn(Arrays.asList(giftItemDTO));

        ResultActions response = mockMvc.perform(get("/api/wishlist/1/gift/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wishlistDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(giftItemDTO).size())));
    }

    @Test
    public void GiftItemController_UpdateItem_ReturnItemDto() throws Exception {
        Long wishlistId = 1L;
        Long giftItemId = 1L;

        when(giftItemService.updateGiftItem(wishlistId, giftItemId, giftItemDTO)).thenReturn(giftItemDTO);

        ResultActions response = mockMvc.perform(put("/api/wishlist/1/gift/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(giftItemDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void GiftItemController_CreateItem_ReturnItemDto() throws Exception {
        Long wishlistId = 1L;

        when(giftItemService.createGiftItem(wishlistId, giftItemDTO)).thenReturn(giftItemDTO);

        ResultActions response = mockMvc.perform(post("/api/wishlist/1/gift/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(giftItemDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void GiftItemController_GetItemById_ReturnItemDto() throws Exception {
        Long wishlistId = 1L;
        Long giftItemId = 1L;
        when(giftItemService.getGiftItemById(giftItemId, wishlistId)).thenReturn(giftItemDTO);

        ResultActions response = mockMvc.perform(get("/api/wishlist/1/gift/items")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void GiftItemController_DeleteItem_ReturnOk() throws Exception {
        Long wishlistId = 1L;
        Long giftItemId = 1L;
        doNothing().when(giftItemService).deleteGiftItem(wishlistId, giftItemId);

        ResultActions response = mockMvc.perform(delete("/api/wishlist/1/gift/items/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}