package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    @Query(value = "select u from User u where u.id=:id")
    User findUserById(@Param("id") Integer id);

    @Query(value="select u from Wishlist u where u.user=:user1")
    public List<Wishlist> getUserWishlist(@Param("user1") User user1);

    @Query(value = "select w from Wishlist w where w.product=:item and w.user=:user")
    public Wishlist getUserWishlistBy(@Param("item") Product item, @Param("user") User user);
}
