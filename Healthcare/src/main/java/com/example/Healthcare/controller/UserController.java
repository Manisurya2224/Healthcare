package com.example.Healthcare.controller;


import com.example.Healthcare.Entity.User;
import com.example.Healthcare.service.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImp UserServiceImp;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = UserServiceImp.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
