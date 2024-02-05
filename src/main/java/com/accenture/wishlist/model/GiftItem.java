package com.accenture.wishlist.model;

import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;

public class GiftItem {
    private Long gift_item_id;
    private Long wishlist_id;
    private String name;
    private String image_url;
    private String purchase_url;
    private double price;
    private GiftStatus gift_status;
    private Priority priority;

}
