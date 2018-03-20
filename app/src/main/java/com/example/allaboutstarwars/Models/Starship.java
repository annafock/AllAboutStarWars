package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anna on 3/20/18.
 */

public class Starship extends StarWarsObject implements Serializable{
    @SerializedName("name")
    public String name;

    @SerializedName("model")
    public String model;

    @SerializedName("manufacturer")
    public String manufacturer;

    @SerializedName("cost_in_credits")
    public String costInCredits;

    @SerializedName("length")
    public String length;

    @SerializedName("max_atmosphering_speed")
    public String maxAtmospheringSpeed;

    @SerializedName("crew")
    public String crew;

    @SerializedName("passengers")
    public String passengers;

    @SerializedName("cargo_capacity")
    public String cargoCapacity;

    @SerializedName("consumables")
    public String consumables;

    @SerializedName("hyperdrive_rating")
    public String hyperDriveRating;

    @SerializedName("MGLT")
    public String MGLT;

    @SerializedName("starship_class")
    public String starShipClass;

    @SerializedName("pilots")
    public ArrayList<String> pilots;

    @SerializedName("films")
    public ArrayList<String> filmsUrls;



}
