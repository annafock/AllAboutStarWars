package com.example.allaboutstarwars.Models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by anna on 3/20/18.
 */

public class Category extends StarWarsObject{

    @SerializedName("films")
    public String film;

    @SerializedName("people")
    public String people;

    @SerializedName("planets")
    public String planet;

    @SerializedName("species")
    public String species;

    @SerializedName("starships")
    public String starship;

    @SerializedName("vehicles")
    public String vehicle;


}
