package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.model.Product;
import com.example.demo.repository.WishlistRepository;
import com.example.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
public class WishlistService {
    @Autowired
    WishlistRepository wishlistRepo;

    @Autowired
    productRepository productRepo;
    public void createWishlist(Integer productId,HttpServletRequest request) {

        HttpSession session=request.getSession();
        Integer id= (Integer) session.getAttribute("id");



       Product item= productRepo.getReferenceById(productId);
       User user=wishlistRepo.findUserById(id);


       if(onWishlist(item,user)) {
           long millis=System.currentTimeMillis();

           Date date= new Date(millis);
           Wishlist wl=new Wishlist();

           wl.setProduct(item);

           wl.setCreatedDate(date);

           wl.setUser(user);

           wishlistRepo.save(wl);

           System.out.println("Produt id" + productId);
           System.out.println("session id" + id);

       }

    }


    public boolean onWishlist(Product item,User user){
        System.out.println("on wish");
       Optional<Wishlist> wl= Optional.ofNullable(wishlistRepo.getUserWishlistBy(item, user));

       if(wl.isPresent()){
           System.out.println("already wishlisted");
        return false;
       }
        return true;
   }

    public List<Wishlist> getUserWishlist(Integer id) {

        User user=wishlistRepo.findUserById(id);
        return (List <Wishlist>) wishlistRepo.getUserWishlist(user);
    }

    public void removeWishlist(Integer wishlistId) {
        wishlistRepo.deleteById(wishlistId);
    }
}
