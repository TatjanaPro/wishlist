package com.accenture.wishlist.web;

import com.accenture.wishlist.business.DTO.WishlistResponse;
import com.accenture.wishlist.business.service.WishlistService;
import com.accenture.wishlist.model.Wishlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;

@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;

    @Autowired
    private ObjectMapper objectMapper;
    private Wishlist wishlist;
    private WishlistResponse wishlistResponse;

    @BeforeEach
    public void init () {
        wishlist = Wishlist.builder()
                .id(1L)
                .title("testTitle")
                .description("testDescription")
                .event_category("testCategory")
                .start_date("2023-12-01")
                .end_date("2023-12-31")
                .build();
    }

}