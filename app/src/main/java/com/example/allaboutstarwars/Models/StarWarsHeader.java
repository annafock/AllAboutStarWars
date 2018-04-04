package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anna on 4/04/18.
 */

public class StarWarsHeader extends StarWarsObject implements Serializable{

    private String headerTitle;

    public StarWarsHeader(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }
}
