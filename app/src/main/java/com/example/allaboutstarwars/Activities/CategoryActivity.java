package com.example.allaboutstarwars.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadArrayData;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.LoadObjectData;
import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.Models.CategoryName;
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

public class CategoryActivity extends DetailActivity{

    private StarWarsObject starWarsObject;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_multi);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        starWarsObject = (CategoryName) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        String rootUrl = "https://swapi.co/api/";
        ArrayList<String> urls = new ArrayList<>();
        urls.add(rootUrl+((CategoryName) starWarsObject).getCategoryName());

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();


        switch(((CategoryName) starWarsObject).getCategoryName()){
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
        super.onDataLoaded(starWarsArray);

    }

    @Override
    public void sendUpdate(int itemCount) {
        super.sendUpdate(itemCount);

    }
}
