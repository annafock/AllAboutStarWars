package com.example.allaboutstarwars.Models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by anna on 3/20/18.
 */

public class Category extends StarWarsObject{

    @SerializedName("films")
    public String films;

    @SerializedName("people")
    public String people;

    @SerializedName("planets")
    public String planets;

    @SerializedName("species")
    public String species;

    @SerializedName("starships")
    public String starships;

    @SerializedName("vehicles")
    public String vehicles;


}
