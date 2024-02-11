package com.accenture.wishlist.business.service.impl;

import com.accenture.wishlist.business.mappers.UserMapStructMapper;
import com.accenture.wishlist.business.repository.UserRepository;
import com.accenture.wishlist.business.repository.model.UserDAO;
import com.accenture.wishlist.business.service.UserService;
import com.accenture.wishlist.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

/*    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapStructMapper userMapper;

    @Override
    public User saveUser(User user) {
        UserDAO userDAO = userMapper.userToDAO(user);
        UserDAO saved = userRepository.save(userDAO);
        log.info("New user is saved: {}", saved);
        return userMapper.daoToUser(userDAO);
    }*/
}
