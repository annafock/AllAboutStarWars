package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by anna on 3/20/18.
 */

public class Planet extends StarWarsObject implements Serializable{

    @SerializedName("name")
    public String name;

    @SerializedName("diameter")
    public String diameter;

    @SerializedName("gravity")
    public String gravity;

    @SerializedName("population")
    public String population;

    @SerializedName("climate")
    public String climate;

    @SerializedName("terrain")
    public String terrain;

    @SerializedName("rotation_period")
    public String rotationPeriod;

    @SerializedName("orbital_period")
    public String orbitalPeriod;

    @SerializedName("surface_water")
    public String surfaceWater;

    @SerializedName("residents")
    public ArrayList<String> residentsUrls;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;

}
