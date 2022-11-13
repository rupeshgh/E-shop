package com.example.demo.controller;

import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller

public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("quantity") Integer quantity,
                          @RequestParam("productId") Integer productId, HttpServletRequest request){
        System.out.println("from add to cart");
        cartService.addToCart(quantity,productId,request);
        System.out.println("added to cart");

        return"cart";

    }

    @GetMapping ("/userCart")
    public String viewCart(Model model,HttpServletRequest request){

        HttpSession session= request.getSession();
        Integer id= (Integer) session.getAttribute("id");

        cartService.getUserCart(id,model);
        System.out.println("form usercart");
        model.addAttribute("username",session.getAttribute("firstname"));
        return "cart";
    }

    @GetMapping("/cartReset")
    public String resetCart(HttpServletRequest request){
        HttpSession session=request.getSession();

        Integer uid= (Integer) session.getAttribute("id");
        cartService.resetCart(uid);


        return "welcomeUser";
    }

}
