package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class People implements Serializable {
    public String name;

    @SerializedName("birth_year")
    public String birthYear;

    public String gender;

    @SerializedName("hair_color")
    public String hairColor;

    public String height;

    @SerializedName("homeworld")
    public String homeWorldUrl;

    public String mass;

    @SerializedName("skin_color")
    public String skinColor;

    public String created;
    public String edited;
    public String url;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;
}
