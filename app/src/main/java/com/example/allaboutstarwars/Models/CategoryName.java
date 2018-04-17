package com.example.allaboutstarwars.Models;


/**
 * Created by anna on 4/5/18.
 */

public class CategoryName extends StarWarsObject{

    private String categoryName;

    public CategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {

        return categoryName;
    }


}
