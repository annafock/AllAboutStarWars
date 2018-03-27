package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allaboutstarwars.Adapters.FilmAdapter;
import com.example.allaboutstarwars.Adapters.MultiModelAdapter;
import com.example.allaboutstarwars.Adapters.PeopleAdapter;
import com.example.allaboutstarwars.Adapters.PlanetAdapter;
import com.example.allaboutstarwars.Adapters.SpeciesAdapter;
import com.example.allaboutstarwars.Adapters.StarshipAdapter;
import com.example.allaboutstarwars.Adapters.VehicleAdapter;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public abstract class DetailActivity extends AppCompatActivity implements MultiModelAdapter.OnMultiModelItemClickListener,
        PeopleAdapter.OnItemClickListener, FilmAdapter.OnItemClickListener, PlanetAdapter.OnItemClickListener,
        SpeciesAdapter.OnItemClickListener, StarshipAdapter.OnItemClickListener, VehicleAdapter.OnItemClickListener {

    private RecyclerView mRecyclerViewFilms, mRecyclerViewSpecies, mRecyclerViewPeople,
            mRecyclerViewPlanet, mRecyclerViewStarship, mRecyclerViewVehicle;
    protected FilmAdapter mFilmAdapter;
    protected PeopleAdapter mPeopleAdapter;
    protected PlanetAdapter mPlanetAdapter;
    protected SpeciesAdapter mSpeciesAdapter;
    protected StarshipAdapter mStarshipAdapter;
    protected VehicleAdapter mVehicleAdapter;

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

    }

    public void setRecyclerViewLayout(StarWarsObject starWarsObject){

        if(starWarsObject instanceof Film) {
            setmRecyclerViewPeople();
            setmRecyclerViewPlanet();
            setmRecyclerViewSpecies();
            setmRecyclerViewStarship();
            setmRecyclerViewVehicle();

        }

        if(starWarsObject instanceof People) {
            setmRecyclerViewFilms();
            setmRecyclerViewSpecies();
            setmRecyclerViewStarship();
            setmRecyclerViewVehicle();

        }

        if(starWarsObject instanceof Planet)  {
            setmRecyclerViewPeople();
            setmRecyclerViewFilms();

        }

        if(starWarsObject instanceof Species) {
            setmRecyclerViewPeople();
            setmRecyclerViewFilms();

        }

        if(starWarsObject instanceof Starship) {
            setmRecyclerViewPeople();
            setmRecyclerViewFilms();

        }

        if(starWarsObject instanceof Vehicle) {
            setmRecyclerViewPeople();
            setmRecyclerViewFilms();

        }

    }

    public void setmRecyclerViewFilms(){

        mRecyclerViewFilms = (RecyclerView) findViewById(R.id.recycler_view_films);
        mRecyclerViewFilms.setHasFixedSize(true);
        mRecyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setmRecyclerViewPeople(){

        mRecyclerViewPeople = (RecyclerView) findViewById(R.id.recycler_view_characters);
        mRecyclerViewPeople.setHasFixedSize(true);
        mRecyclerViewPeople.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setmRecyclerViewPlanet(){

        mRecyclerViewPlanet = (RecyclerView) findViewById(R.id.recycler_view_planets);
        mRecyclerViewPlanet.setHasFixedSize(true);
        mRecyclerViewPlanet.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setmRecyclerViewSpecies(){

        mRecyclerViewSpecies = (RecyclerView) findViewById(R.id.recycler_view_species);
        mRecyclerViewSpecies.setHasFixedSize(true);
        mRecyclerViewSpecies.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setmRecyclerViewStarship(){

        mRecyclerViewStarship = (RecyclerView) findViewById(R.id.recycler_view_starships);
        mRecyclerViewStarship.setHasFixedSize(true);
        mRecyclerViewStarship.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setmRecyclerViewVehicle(){

        mRecyclerViewVehicle = (RecyclerView) findViewById(R.id.recycler_view_vehicles);
        mRecyclerViewVehicle.setHasFixedSize(true);
        mRecyclerViewVehicle.setLayoutManager(new LinearLayoutManager(this));

    }

    protected void parseJSON(Map<Class, ArrayList<String>> map){
        final ArrayList<StarWarsObject> starWarsObjectList = new ArrayList<>();

        gson = new Gson();

        String url;

        for (final Map.Entry<Class,ArrayList<String>> entry: map.entrySet()){
            final Class modelClass = entry.getKey();
            System.out.println(modelClass.toString());
            ArrayList<String> value = entry.getValue();
            for (String urlString : value){
                url = urlString;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                String result = response.toString();

                                // Parse results and populate each recyclerview
                                if (modelClass == Film.class){

                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    films.add(starWarsObject);

                                    mFilmAdapter = new FilmAdapter(DetailActivity.this, films);

                                    mRecyclerViewFilms.setAdapter(mFilmAdapter);
                                    mFilmAdapter.setOnItemClickListener(DetailActivity.this);

                                }
                                else if (modelClass == People.class){
                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    people.add(starWarsObject);
                                    mPeopleAdapter = new PeopleAdapter(DetailActivity.this, people);

                                    mRecyclerViewPeople.setAdapter(mPeopleAdapter);
                                    mPeopleAdapter.setOnItemClickListener(DetailActivity.this);

                                } else if (modelClass == Planet.class){

                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    planets.add(starWarsObject);
                                    mPlanetAdapter = new PlanetAdapter(DetailActivity.this, planets);

                                    mRecyclerViewPlanet.setAdapter(mPeopleAdapter);
                                    mPlanetAdapter.setOnItemClickListener(DetailActivity.this);

                                } else if (modelClass == Species.class){
                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    species.add(starWarsObject);
                                    mSpeciesAdapter = new SpeciesAdapter(DetailActivity.this, species);

                                    mRecyclerViewSpecies.setAdapter(mSpeciesAdapter);
                                    mSpeciesAdapter.setOnItemClickListener(DetailActivity.this);

                                } else if (modelClass == Starship.class){
                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    starships.add(starWarsObject);
                                    mStarshipAdapter = new StarshipAdapter(DetailActivity.this, starships);

                                    mRecyclerViewStarship.setAdapter(mStarshipAdapter);
                                    mStarshipAdapter.setOnItemClickListener(DetailActivity.this);

                                } else if (modelClass == Vehicle.class){
                                    starWarsObject = (StarWarsObject)gson.fromJson(result, modelClass);

                                    vehicles.add(starWarsObject);
                                    mVehicleAdapter = new VehicleAdapter(DetailActivity.this, vehicles);

                                    mRecyclerViewVehicle.setAdapter(mVehicleAdapter);
                                    mVehicleAdapter.setOnItemClickListener(DetailActivity.this);
                                }

                            }

                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });


                mRequestQueue.add(request);

            }
        }

    }

    @Override
    public void onItemClicked(int position) {

    }

}

