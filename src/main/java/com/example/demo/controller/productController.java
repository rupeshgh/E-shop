package com.example.demo.controller;





import com.example.demo.model.Product;

import com.example.demo.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class productController {

//product creation at dto


@Autowired
productService productservice;


    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product item, Model model,@RequestParam ("categoryname") String categoryname){



        if (productservice.checkProductCategory(categoryname)){

            productservice.createProduct(item,categoryname);

        }


        return "added dto product";

    }

    @GetMapping("/listproduct")
    public List<Product> listproduct(){
    List<Product> prod= productservice.getAll();
    return prod;

}

@PostMapping("/updateProduct/{product_id}")
    public String updateProduct(@PathVariable("product_id")  Integer product_id,@RequestBody Product prod){

    productservice.updateProduct(product_id,prod);
    return "update success";
}



}
