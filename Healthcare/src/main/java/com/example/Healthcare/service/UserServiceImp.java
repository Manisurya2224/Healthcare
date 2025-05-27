package com.example.Healthcare.service;


import com.example.Healthcare.Entity.User;
import com.example.Healthcare.Repo.UserRepository;
import com.example.Healthcare.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;


    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = findUserByEmail(email);

        return user;
    }


    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User not found.");
        }
        return user;
    }
}
