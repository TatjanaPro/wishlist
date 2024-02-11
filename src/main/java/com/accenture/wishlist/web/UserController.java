package com.accenture.wishlist.web;

import com.accenture.wishlist.business.service.UserService;
import com.accenture.wishlist.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/v1/user")
public class UserController {

/*
    @Autowired
    UserService userService;

    @PostMapping()
        public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("Received values {}", user);
        User postUser = userService.saveUser(user);
        log.info("New user is created: {}", postUser);
        return new ResponseEntity<>(postUser, HttpStatus.CREATED);

        }
*/

}
