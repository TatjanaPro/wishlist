package com.accenture.wishlist.model;

import com.accenture.wishlist.model.Enum.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Wishlist {
    private Long wishlist_id;
    private Long owner_id;
    private List<User> contributor;
    private String title;
    private String description;
    private EventCategory event_category;
    private String start_date;
    private String end_date;

}
