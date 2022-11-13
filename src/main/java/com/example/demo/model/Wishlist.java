package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="created_date")
    private Date createdDate;

    //a product part of many wishlist
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;



}
