package com.accenture.wishlist.web;

import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.DTO.WishlistDTO;
import com.accenture.wishlist.business.DTO.WishlistResponse;
import com.accenture.wishlist.business.service.UserService;
import com.accenture.wishlist.business.service.WishlistService;
import com.accenture.wishlist.model.Role;
import com.accenture.wishlist.model.UserEntity;
import com.accenture.wishlist.model.Wishlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(WishlistController.class)
@AutoConfigureMockMvc(addFilters = false)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    private WishlistResponse wishlistResponse;
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

        entity = UserEntity.builder()
                .id(10L)
                .username("Tatjana")
                .password("password")
                .roles(roles)
                .wishlists(wishlists)
                .wishlistList(wishlistList)
                .build();

        userDTO = UserDTO.builder()
                .id(10L)
                .username("Tatjana")
                .build();

        collaborator = UserEntity.builder()
                .id(20L)
                .username("TatjanaCollaborator")
                .password("password")
                .roles(roles)
                .wishlists(wishlists)
                .wishlistList(wishlistList)
                .build();

        collaborator2 = UserEntity.builder()
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

        wishlist = Wishlist.builder().
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

        wishlistDTO = WishlistDTO.builder().
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
    public void WishlistController_CreateWishlist_ReturnCreated() throws Exception {
        given(userService.getUserById(anyLong())).willReturn(userDTO);
        given(wishlistService.createWishlist(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wishlistDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.owner", CoreMatchers.is(wishlistDTO.getOwner())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void WishlistController_GetAllWishlist_ReturnResponseDto() throws Exception {
        WishlistResponse responseDto = WishlistResponse.builder().
                pageSize(10)
                .last(true)
                .pageNo(1)
                .content(wishlistDTOs)
                .build();
        when(wishlistService.getAllWishlist(1, 10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/wishlist")
                        .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", "1")
                .param("pageSize", "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));

    }

    @Test
    public void WishlistController_GetWishlistDetail_ReturnWishlistDto() throws Exception {
        long wishlistId = 1L;
       when(wishlistService.getWishlistById(wishlistId)).thenReturn(wishlistDTO);

        ResultActions response = mockMvc.perform(get("/api/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wishlistDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void WishlistController_UpdateWishlistDetail_ReturnWishlistDto() throws Exception {
        long wishlistId = 1L;
        when(wishlistService.updateWishlist(wishlistDTO, wishlistId)).thenReturn(wishlistDTO);

        ResultActions response = mockMvc.perform(put("/api/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wishlistDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void WishlistController_DeletelistDetail_ReturnString() throws Exception {
        long wishlistId = 1L;
        doNothing().when(wishlistService).deleteWishlist(wishlistId);

        ResultActions response = mockMvc.perform(delete("/api/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}