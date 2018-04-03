package com.example.allaboutstarwars;

import com.example.allaboutstarwars.Models.StarWarsObject;

import java.util.ArrayList;

/**
 * Created by anna on 4/03/18.
 */


public interface LoadDataCallback {

    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray);
    public void sendUpdate(int itemCount);
}
