package com.example.allaboutstarwars.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anna on 3/20/18.
 */

public abstract class StarWarsObject implements Serializable {

    @SerializedName("created")
    public String created;

    @SerializedName("edited")
    public String edited;

    @SerializedName("url")
    public String url;
}
