package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class welcomeUser {

    boolean checkSession(HttpServletRequest request){
        HttpSession session=request.getSession();

        if (session.getAttribute("id")==null) {
            return false;
        }
        return true;
    }

    @GetMapping("/user/welcome")
        public String returnView(HttpServletRequest request, Model model) {
        System.out.println("welcome user controller "+model.getAttribute("id"));

        if (checkSession(request)) {
            return "welcomeUser";
        } else {
            return "error";
        }

    }

}
