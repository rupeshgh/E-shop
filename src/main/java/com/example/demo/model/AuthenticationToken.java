package com.example.demo.model;

import lombok.*;


import javax.persistence.*;
import java.util.Date;

import java.util.UUID;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String token;

    @Column(name="created_date")
    private Date createdDate;


    @OneToOne(targetEntity = User.class,fetch=FetchType.EAGER)
    @JoinColumn(nullable=false,name="user_id")
    private User user;

//creating token when user logs in
    public AuthenticationToken(User currUser){
        this.user=currUser;
        this.createdDate=new Date();
        this.token= UUID.randomUUID().toString();

    }



}



