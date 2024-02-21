package com.accenture.wishlist.business.DTO;


import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftItemDTO {

    private Long gift_item_id;
    private String name;
    private String image_url;
    private String purchase_url;
    private double price;
    private GiftStatus gift_status;
    private Priority priority;

}
