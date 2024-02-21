package com.accenture.wishlist.business.DTO;

import com.accenture.wishlist.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
