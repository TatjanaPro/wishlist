package com.accenture.wishlist.model;

import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class GiftItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gift_item_id;

    private Long wishlist_id;
    private String name;
    private String image_url;
    private String purchase_url;
    private double price;
    private GiftStatus gift_status;
    private Priority priority;

}
