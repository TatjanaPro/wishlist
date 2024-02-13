package com.accenture.wishlist.business.DTO;

import lombok.Data;

import java.util.List;

@Data
public class WishlistResponse {
    private List<WishlistDTO> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;

}
