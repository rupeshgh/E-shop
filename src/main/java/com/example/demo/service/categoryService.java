package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class categoryService {
    @Autowired
    categoryRepository categoryRepo;

    public String createCategory(Category item){

        String categoryName= item.getCategoryName();
       Optional<String> catName= Optional.ofNullable(categoryRepo.checkExistingCategory(categoryName));

        if(catName.isPresent()){
        //categoryname already exist..
            System.out.println("existing category");
            return "error";

        }
        else{
            System.out.println("new category added");
            categoryRepo.save(item);

            return "test";
        }



    }

    public List<Category> listcategory(){
        return categoryRepo.findAll();
    }

    public void editCategory(Integer id, Category item) {

        Category cat =categoryRepo.getReferenceById(id);
        cat.setId(item.getId());
        cat.setCategoryName(item.getCategoryName());
        cat.setDescription(item.getDescription());
        cat.setImage(item.getImage());
        categoryRepo.save(cat);
    }
}
