package com.example.demo.service;


//import com.example.demo.controller.SessionController;
import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service



public class UserService {



//    @Autowired
//    SessionController sessionController;

    @Autowired
    UserRepository userRepository;

   @Autowired
    TokenRepository tokenRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    productService prodService;

    @ModelAttribute("currentUser")
    public User getCurrentUser(){
        return new User();
    }




    public String createAccount(User user) throws NoSuchAlgorithmException{

        Optional<User> currUser= Optional.ofNullable(userRepository.findByEmail(user.getEmail()));


        if(currUser.isPresent()){
            System.out.println("user already exist");
            return "error";
        }


        else {
            User newUser = new User();

            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setEmail(user.getEmail());

            String encryptedPassword=hashPassword(user.getPassword());
            newUser.setPassword(encryptedPassword);

            User createdUser = userRepository.save(newUser);

            if (createdUser != null) {
                return "index";
            } else {
                return "error";
            }



        }
    }

    public boolean login(String email, String password, HttpServletRequest request, Model model) throws NoSuchAlgorithmException {

        System.out.println(email);
       Optional <User> currUser= Optional.ofNullable(userRepository.findByEmail(email));

//       User tmp= currUser.get();


        if(!(currUser.isPresent() && currUser.get().getPassword().equals(hashPassword(password))))
        {
            System.out.println("not user");
            return false;
        }


        HttpSession session=request.getSession();
        session.setAttribute("id",currUser.get().getId());
        session.setAttribute("firstname",currUser.get().getFirstname());
        session.setAttribute("lastname",currUser.get().getLastname());
        session.setAttribute("email",currUser.get().getEmail());

        List<Product> Products=prodService.getAll();

//        System.out.println(Products);
        model.addAttribute("products",Products);
        model.addAttribute("username",currUser.get().getFirstname());

//        AuthenticationToken authenticationToken=new AuthenticationToken(currUser.get());
//        authenticationService.saveToken(authenticationToken);
//
//
//        String ak=authenticationService.getToken(currUser.get());

//        sessionController.createSession(currUser.get());





//        System.out.println("token is"+ak);
        return true;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();

        String hash= DatatypeConverter.printHexBinary(digest).toUpperCase();

        return hash;
    }



}




