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

public class MainActivity extends AppCompatActivity implements LoadDataCallback, CategoryAdapter.OnMultiModelItemClickListener{

    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_category);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        if (0!=starWarsArray.size()){
            mCategoryAdapter = new CategoryAdapter(this, starWarsArray);
            mRecyclerView.setAdapter(mCategoryAdapter);
            mCategoryAdapter.setOnItemClickListener(MainActivity.this);
        }

    }

    @Override
    public void sendUpdate(int itemCount) {

    }
}
