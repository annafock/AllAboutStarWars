package com.example.allaboutstarwars.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.LoadObjectData;
import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends DetailActivity{

    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //Is set in MainAdapter
    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        super.onDataLoaded(starWarsArray);

    }

    @Override
    public void sendUpdate(int itemCount) {

    }
}
