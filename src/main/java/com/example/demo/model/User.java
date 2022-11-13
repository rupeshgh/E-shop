package com.example.demo.model;


import lombok.*;


import javax.persistence.*;



@Entity
@Table(name = "User")
@Data @NoArgsConstructor @AllArgsConstructor
@Setter
@Getter
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;



}
