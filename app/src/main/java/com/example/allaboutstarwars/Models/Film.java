package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by anna on 3/20/18.
 */

public class Film extends StarWarsObject implements Serializable{

    @SerializedName("title")
    public String title;

    @SerializedName("rotation_period")
    public String rotationPeriod;

    @SerializedName("episode_id")
    public int episodeId;

    @SerializedName("opening_crawl")
    public String openingCrawl;

    @SerializedName("director")
    public String director;

    @SerializedName("producer")
    public String producer;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

    @SerializedName("planets")
    public ArrayList<String> planetsUrls;

    @SerializedName("characters")
    public ArrayList<String> charactersUrls;

}
