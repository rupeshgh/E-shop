package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="category")
@Data @NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="category_name")
    private String categoryName;

    private String  description;

    @Column(name="image_url")
    private String image;


}
