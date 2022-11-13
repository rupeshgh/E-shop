package com.example.demo.service;


import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.categoryRepository;
import com.example.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    productRepository productRepo;

    @Autowired
    categoryRepository catRepository;
    public  List<Product> getAll() {
        List<Product>prod=new ArrayList<>();
         prod= productRepo.findAll();


        return  prod;
    }


    public void createProduct (Product item, String categoryname){

        Product prod= new Product();

//        prod.setId(item.getId());
        prod.setDescription(item.getDescription());
        prod.setName(item.getName());
        prod.setImage_url(item.getImage_url());
        prod.setPrice(item.getPrice());

        Category existingCategory=catRepository.getCategoryByName(categoryname);

        prod.setCat(existingCategory);

    productRepo.save(prod);



    }


    public String updateProduct(Integer product_id, Product prod) {

        Optional<Product> optionalcategory=productRepo.findById(product_id);

        if(!optionalcategory.isPresent()){
            return "no such product";
        }

        Product item= optionalcategory.get();

        item.setImage_url(prod.getImage_url());
        item.setName(prod.getName());
        item.setDescription(prod.getDescription());
        item.setPrice(prod.getPrice());

        productRepo.save(item);
        return "update success";
    }

    public boolean checkProductCategory(String categoryname) {

                Optional<String> catName= Optional.ofNullable(catRepository.checkCategory(categoryname));

        if(!catName.isPresent()){
            System.out.println("No such category to create a product");
            return false;

        }
        else {
            System.out.println("such category exist");
            return true;}


    }
}
