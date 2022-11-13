package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface categoryRepository extends JpaRepository<Category, Integer> {

    @Query(value ="select c.categoryName from Category c where c.categoryName=:CategoryName" )
    String checkExistingCategory(@Param("CategoryName") String categoryName);

    @Query(value = "select c.categoryName from Category c where c.categoryName=:CategoryName")
    public String checkCategory(@Param("CategoryName") String categoryName);

    @Query(value = "select c from Category c where c.categoryName=:CategoryName")
    public Category getCategoryByName(@Param("CategoryName") String categoryName);
}
