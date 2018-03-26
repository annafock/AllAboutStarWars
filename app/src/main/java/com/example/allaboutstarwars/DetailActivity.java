package com.example.allaboutstarwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

public abstract class DetailActivity extends AppCompatActivity implements MultiModelAdapter.OnMultiModelItemClickListener{
    private RecyclerView mRecyclerViewFilms, mRecyclerViewSpecies, mRecyclerViewPeople,
            mRecyclerViewPlanet, mRecyclerViewStarship, mRecyclerViewVehicle;
    private MultiModelAdapter mMultiModelAdapter;
    private StarWarsObject starWarsObject;
    final ArrayList<StarWarsObject> films = new ArrayList<>();
    final ArrayList<StarWarsObject> people = new ArrayList<>();
    final ArrayList<StarWarsObject> planets = new ArrayList<>();
    final ArrayList<StarWarsObject> species = new ArrayList<>();
    final ArrayList<StarWarsObject> starships = new ArrayList<>();
    final ArrayList<StarWarsObject> vehicles = new ArrayList<>();
    private RequestQueue mRequestQueue;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRequestQueue = Volley.newRequestQueue(this);

        Map<Class, ArrayList<String>> map = new HashMap<>();

        parseJSON(map);
    }

    public void setRecyclerViewLayout(StarWarsObject starWarsObject){

        if(!(starWarsObject instanceof Film)) {
            mRecyclerViewFilms = (RecyclerView) findViewById(R.id.recycler_view_films);
            mRecyclerViewFilms.setHasFixedSize(true);
            mRecyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof People)) {
            mRecyclerViewPeople = (RecyclerView) findViewById(R.id.recycler_view_characters);
            mRecyclerViewPeople.setHasFixedSize(true);
            mRecyclerViewPeople.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof Planet || (starWarsObject instanceof People)))  {
            mRecyclerViewPlanet = (RecyclerView) findViewById(R.id.recycler_view_planets);
            mRecyclerViewPlanet.setHasFixedSize(true);
            mRecyclerViewPlanet.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof Species)) {
            mRecyclerViewSpecies = (RecyclerView) findViewById(R.id.recycler_view_species);
            mRecyclerViewSpecies.setHasFixedSize(true);
            mRecyclerViewSpecies.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof Starship)) {
            mRecyclerViewStarship = (RecyclerView) findViewById(R.id.recycler_view_starships);
            mRecyclerViewStarship.setHasFixedSize(true);
            mRecyclerViewStarship.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof Vehicle)) {
            mRecyclerViewVehicle = (RecyclerView) findViewById(R.id.recycler_view_vehicles);
            mRecyclerViewVehicle.setHasFixedSize(true);
            mRecyclerViewVehicle.setLayoutManager(new LinearLayoutManager(this));
        }

    }

    protected void parseJSON(Map<Class, ArrayList<String>> map){

        gson = new Gson();

        String url;

        for (final Map.Entry<Class,ArrayList<String>> entry: map.entrySet()){
            final Class modelClass = entry.getKey();
            System.out.println(modelClass.toString());
            ArrayList<String> value = entry.getValue();
            for (String urlString : value){
                url = urlString;
                System.out.println(url);


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                String result = response.toString();

                                // Parse results and populate each recyclerview
                                if (modelClass == Film.class){

                                    populateRecyclerView(result, films, Film.class);
                                    mRecyclerViewFilms.setAdapter(mMultiModelAdapter);

                                } else if (modelClass == People.class){

                                    populateRecyclerView(result, people, People.class);
                                    mRecyclerViewPeople.setAdapter(mMultiModelAdapter);

                                } else if (modelClass == Planet.class){

                                    populateRecyclerView(result, planets, Planet.class);
                                    mRecyclerViewPlanet.setAdapter(mMultiModelAdapter);

                                } else if (modelClass == Species.class){

                                    populateRecyclerView(result, species, Species.class);
                                    mRecyclerViewSpecies.setAdapter(mMultiModelAdapter);

                                } else if (modelClass == Starship.class){

                                    populateRecyclerView(result, starships, Starship.class);
                                    mRecyclerViewStarship.setAdapter(mMultiModelAdapter);

                                } else if (modelClass == Vehicle.class){

                                    populateRecyclerView(result, vehicles, Vehicle.class);
                                    mRecyclerViewVehicle.setAdapter(mMultiModelAdapter);

                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                mRequestQueue.add(request);

            }

        }
    }

    public void populateRecyclerView(String requestResult, ArrayList<StarWarsObject> starWarsObjectList, Class modelClass){
        //Parse result and create star wars object of different type
        starWarsObject = (StarWarsObject)gson.fromJson(requestResult, modelClass);

        starWarsObjectList.add(starWarsObject);
        //mStarWarsObjectList.add(starWarsObject);
        mMultiModelAdapter = new MultiModelAdapter(DetailActivity.this, starWarsObjectList);

        mMultiModelAdapter.setOnItemClickListener(DetailActivity.this);

    }

    @Override
    public void onItemClicked(int position) {



    }

}

