package com.example.demo.repository;

import com.example.demo.model.AuthenticationToken;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken ,Integer> {

    //    @Query(value = "select u from tokens u ")
//    AuthenticationToken findTokenByUser(@Param("user") User user,@Param("tokens") AuthenticationToken tokens);

    @Query(value = "select u.token from AuthenticationToken u where u.user=:user1")
    public String findTokenById(@Param("user1") User user1);

}
