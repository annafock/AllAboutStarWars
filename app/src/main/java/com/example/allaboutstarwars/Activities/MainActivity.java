package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import com.example.allaboutstarwars.LoadObjectData;
import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.Models.StarWarsObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends DetailActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String rootUrl = "https://swapi.co/api/";
        ArrayList<String> urls = new ArrayList<>();
        urls.add(rootUrl);

        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(Category.class, urls);

        LoadObjectData task = new LoadObjectData(this);
        task.execute(map);

    }


    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        super.onDataLoaded(starWarsArray);

    }

    @Override
    public void sendUpdate(int itemCount) {

    }
}
