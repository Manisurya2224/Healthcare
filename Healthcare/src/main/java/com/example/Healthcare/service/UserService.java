package com.example.Healthcare.service;


import com.example.Healthcare.Entity.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
