package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class People extends StarWarsObject {

    @SerializedName("name")
    public String name;

    @SerializedName("birth_year")
    public String birthYear;

    @SerializedName("gender")
    public String gender;

    @SerializedName("hair_color")
    public String hairColor;

    @SerializedName("height")
    public String height;

    @SerializedName("homeworld")
    public String homeWorldUrl;

    @SerializedName("mass")
    public String mass;

    @SerializedName("skin_color")
    public String skinColor;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

}
