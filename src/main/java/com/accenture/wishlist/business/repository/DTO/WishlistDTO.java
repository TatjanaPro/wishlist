package com.accenture.wishlist.business.repository.DTO;

import lombok.Data;

@Data
public class WishlistDTO {

    private Long id;
    private Long owner_id;
    //private User collaborator;
    private String title;
    private String description;
    private String event_category;
    private String start_date;
    private String end_date;

}
