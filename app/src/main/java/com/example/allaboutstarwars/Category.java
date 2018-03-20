package com.example.allaboutstarwars;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by anna on 3/20/18.
 */

class Category implements Serializable{
    String item;

    public Category(String item){
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
