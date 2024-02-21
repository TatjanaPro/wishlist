package com.accenture.wishlist.web;

import com.accenture.wishlist.business.service.GiftItemService;
import com.accenture.wishlist.model.GiftItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WishlistController.class)
class GiftItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GiftItemService giftItemService;

    @Autowired
    private ObjectMapper objectMapper;

    private GiftItem giftItem;

    @BeforeEach
    public void init() {

    }

}