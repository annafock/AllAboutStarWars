package com.example.allaboutstarwars.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadArrayData;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.Adapters.CategoryAdapter.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/20/18.
 */

public class CategoryActivity extends AppCompatActivity implements
        CategoryAdapter.OnMultiModelItemClickListener, LoadDataCallback{

    private StarWarsObject starWarsObject;
    String categoryName;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_multi);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //TODO find why this isn't parsing directly (might be because of not gson parsed in loadObject
        starWarsObject = (Category) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);
        Category category = (Category) starWarsObject;
        category.getName();

        String rootUrl = "https://swapi.co/api/";
        ArrayList<String> urls = new ArrayList<>();
        urls.add(rootUrl+category.getName());

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();


        switch(category.getName()){
            case "people":
                map.put(People.class, urls);
                break;
            case "films":
                map.put(Film.class, urls);
                break;
            case "planets":
                map.put(Planet.class, urls);
                break;
            case "species":
                map.put(Species.class, urls);
                break;
            case "starships":
                map.put(Starship.class, urls);
                break;
            case "vehicles":
                map.put(Vehicle.class, urls);
                break;
            default: break;
        }

        LoadArrayData task = new LoadArrayData(this);
        task.execute(map);

    }


    @Override
    public void onItemClicked(int position) {
    // Is set in Category adapter
    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        mCategoryAdapter = new CategoryAdapter(CategoryActivity.this, starWarsArray);
        mRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnItemClickListener(CategoryActivity.this);

    }

    @Override
    public void sendUpdate(int itemCount) {

    }
}
