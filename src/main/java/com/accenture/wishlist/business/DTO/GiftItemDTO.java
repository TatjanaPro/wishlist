package com.accenture.wishlist.business.DTO;


import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import lombok.Data;

@Data
public class GiftItemDTO {

    private Long gift_item_id;
    private String name;
    private String image_url;
    private String purchase_url;
    private double price;
    private GiftStatus gift_status;
   // @Enumerated(EnumType.STRING)
    private Priority priority;

}
