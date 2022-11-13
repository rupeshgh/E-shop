package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface cartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c.product from Cart c where c.user=:user")
    List<Product> getcartProductByUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query("Delete from Cart c where c.user=:user")
    void resetUserCart(@Param("user")User user);

    @Query("select c from Cart c where c.user=:user")
    List<Cart> getUserCart(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("Update Cart c set c.quantity= c.quantity + :quantity where c.user=:user and c.product=:product ")
    Integer checkForExistingCartItem(@Param("user") User user, @Param("product")Product product,@Param("quantity") Integer quantity);
}
