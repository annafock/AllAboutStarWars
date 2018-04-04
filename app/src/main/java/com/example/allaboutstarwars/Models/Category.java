package com.example.allaboutstarwars.Models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by anna on 3/20/18.
 */

public class Category extends StarWarsObject{

//    @SerializedName("name")
//    public String name;

    public String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
