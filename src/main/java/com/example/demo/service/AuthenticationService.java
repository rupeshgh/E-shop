package com.example.demo.service;



import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;


    public void saveToken(AuthenticationToken authenticationToken) {

        tokenRepository.save(authenticationToken);
    }


    public String getToken(User user) {


//        AuthenticationToken at=tokenRepository.findTokenByUser(user);
//
//
//        return  at.getToken().toString();


        System.out.println(tokenRepository.findTokenById(user));

        return "ok";

    }
}
