package com.accenture.wishlist.business.DTO;

import com.accenture.wishlist.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class WishlistDTO {

    private Long id;
    private UserDTO owner;
    private String title;
    private String description;
    private String event_category;
    private String start_date;
    private String end_date;
    private List<UserDTO> collaborators;

}
