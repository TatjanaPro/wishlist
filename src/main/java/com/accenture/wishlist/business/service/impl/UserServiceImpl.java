package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.DTO.UserDTO;
import com.accenture.wishlist.business.repository.UserRepository;
import com.accenture.wishlist.business.service.UserService;
import com.accenture.wishlist.exceptions.EntityNotFoundException;
import com.accenture.wishlist.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return UserDTO.mapToUserDto(userOptional.get());
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }
}
