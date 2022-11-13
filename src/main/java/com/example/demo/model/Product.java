package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data @NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String name;
    private String description;
    private Integer price;
    private String image_url;


//1 cataegory has many product
//one to many relationship
    @ManyToOne
    @JoinColumn(name="category_id")
Category cat;


}
