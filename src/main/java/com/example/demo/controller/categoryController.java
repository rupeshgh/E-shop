package com.example.demo.controller;


import com.example.demo.model.Category;
import com.example.demo.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/category")
public class categoryController {
    @Autowired
   categoryService catService;

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category item){


    //id will be generated inside service method
   return catService.createCategory(item);



    }

   @PostMapping("/update/{categoryId}")
   public String update(@PathVariable("categoryId") int categoryId,@ModelAttribute Category item){

        catService.editCategory(categoryId,item);
    return "updated";
   };


    @GetMapping("/")
    public String homePage(){

        return "index";
    }
@RequestMapping ("/hello")
    public String xyz(){

        return "error";

}



}
