package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.Wishlist;
import com.example.demo.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/additem")
    public String addToWishlist(HttpServletRequest request,
                                @RequestParam("id") Integer id , Model model){

        System.out.println("product id" +id);
    wishlistService.createWishlist(id,request);
        return "yourWishlist";
    }

    @PostMapping("/remove")
    public String removeWishlist(@RequestParam("id") Integer wishlistId){


        wishlistService.removeWishlist(wishlistId);


    return "redirect:/user/wishlist/see";
    }

    @GetMapping("/see")
    public String yourWishlist(HttpServletRequest request,Model model){
        System.out.println("fromm see");
        HttpSession session =request.getSession();
        Integer id= (Integer)session.getAttribute("id");

     List<Wishlist> wishlist=wishlistService.getUserWishlist(id);

        Map<Integer ,Product> mp=new HashMap<>();
     for (Wishlist wl:wishlist){
         mp.put(wl.getId(),wl.getProduct());

     }
        System.out.println("from see wishlist");
     model.addAttribute("wishlistMap",mp);
        return "yourWishlist";
    }
}
