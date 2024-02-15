package com.accenture.wishlist.business.DTO;

import com.accenture.wishlist.model.UserEntity;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    //private List<WishlistDTO> wishlist;

    public static UserEntity mapToUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());

        return userEntity;
    }

    public static UserDTO mapToUserDto(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        return userDTO;
    }

}

