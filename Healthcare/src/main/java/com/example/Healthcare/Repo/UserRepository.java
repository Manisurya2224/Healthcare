package com.example.Healthcare.Repo;


import com.example.Healthcare.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public  User findByEmail(String username);



}