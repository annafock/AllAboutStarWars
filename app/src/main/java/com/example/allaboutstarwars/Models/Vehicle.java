package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by anna on 3/20/18.
 */

public class Vehicle extends StarWarsObject implements Serializable{
    @SerializedName("name")
    public String name;

    @SerializedName("model")
    public String model;

    @SerializedName("vehicle_class")
    public String vehicleClass;

    public String manufacturer;

    @SerializedName("cost_in_credits")
    public String costInCredits;

    @SerializedName("name")
    public String length;

    @SerializedName("crew")
    public String crew;

    @SerializedName("passengers")
    public String passengers;

    @SerializedName("max_atmosphering_speed")
    public String maxAtmospheringSpeed;

    @SerializedName("cargo_capacity")
    public String cargoCapacity;

    @SerializedName("consumables")
    public String consumables;

    @SerializedName("pilots")
    public ArrayList<String> pilotsUrls;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;

}
