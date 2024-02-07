package com.accenture.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long user_id;

    @NonNull
    private String username;

    @NonNull
    private String password;

}
