package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.cartRepository;
import com.example.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    productRepository productRepo;
    @Autowired
    UserRepository userRepository;

    @Autowired
    cartRepository cartRepo;
    public void addToCart(Integer quantity, Integer productId, HttpServletRequest request) {

        HttpSession session=request.getSession();
       Integer id = (Integer) session.getAttribute("id");

       User user=userRepository.getReferenceById(id);
        Product prod=productRepo.getReferenceById(productId);


//if product already on crate for current user increase quantity only
       Optional<Integer> updated= Optional.ofNullable(cartRepo.checkForExistingCartItem(user, prod, quantity));

        if (updated.get().equals(1)) {

            System.out.println("updated value"+updated.get());
        }

        else {
            Long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            Cart cart = new Cart();
            cart.setCreatedDate(date);
            cart.setQuantity(quantity);
            cart.setProduct(prod);
            cart.setUser(user);

            cartRepo.save(cart);

            System.out.println(quantity + "Added for Id" + id);
        }
        }


    public void getUserCart(Integer id, Model model) {

        User user=userRepository.getReferenceById(id);
//        List<Product> product=cartRepo.getcartProductByUser(user);
//        model.addAttribute("products",product);
//        System.out.println(product);

        List<Cart> userCart=cartRepo.getUserCart(user);

        Map<Product,Integer> mp=new HashMap<>();
        Integer Price=0;
        for(Cart c:userCart){
            mp.put(c.getProduct(),c.getQuantity());
            Price=Price+c.getProduct().getPrice()*c.getQuantity();
        }

        System.out.println(Price);
        System.out.println("from cart service");
        model.addAttribute("totalCost",Price);
        model.addAttribute("cartMap",mp);

    }

    public void resetCart(Integer uid) {
        User user=userRepository.getReferenceById(uid);

        cartRepo.resetUserCart(user);
    }
}
