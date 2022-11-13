package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public String createAccount(@ModelAttribute("user") User user) throws NoSuchAlgorithmException {

//    System.out.println(userService.toString());
        return userService.createAccount(user);

    }

    @PostMapping("/userCart")
    public String userCart(){
        //curnrntly url /user/sigin/usercart
        //redirecting to cartController
        return"redirect:/userCart";

    }


    @PostMapping("/signin")
    public String login( @RequestParam(name = "email" ) String email,
            @RequestParam(name="password") String password,
            HttpServletRequest request, Model model) throws NoSuchAlgorithmException {

        System.out.println(email);

        if (userService.login(email,password,request,model)) {

             System.out.println("valid");

             if(email.equals("xyz@gmail.com")){
                 System.out.println("admin logged");
                 return "admin";
             }

            return "welcomeUser";

        } else {

            System.out.println("invalid user");

            return "error";

        }



    }



}