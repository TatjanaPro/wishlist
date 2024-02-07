package com.accenture.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Wishlist {

    private Long wishlist_id;
    private Long owner_id;
    private List<User> collaborator;

    private String title;
    private String description;
    private String event_category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end_date;

}
