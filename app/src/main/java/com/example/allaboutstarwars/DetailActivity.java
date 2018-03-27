package com.example.allaboutstarwars;

import android.content.Intent;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

public abstract class DetailActivity extends AppCompatActivity implements MultiModelAdapter.OnMultiModelItemClickListener,
        PeopleAdapter.OnItemClickListener, FilmAdapter.OnItemClickListener {

    private RecyclerView mRecyclerViewFilms, mRecyclerViewSpecies, mRecyclerViewPeople,
            mRecyclerViewPlanet, mRecyclerViewStarship, mRecyclerViewVehicle;
    protected MultiModelAdapter mMultiModelAdapter;
    protected FilmAdapter mFilmAdapter;
    protected PeopleAdapter mPeopleAdapter;
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

                                }
//                                else if (modelClass == Planet.class){
//
//                                    populateRecyclerView(result, planets, Planet.class);
//                                    mRecyclerViewPlanet.setAdapter(mMultiModelAdapter);
//
//                                } else if (modelClass == Species.class){
//                                    System.out.println("Species " + modelClass);
//                                    populateRecyclerView(result, species, Species.class);
//                                    mRecyclerViewSpecies.setAdapter(mMultiModelAdapter);
//
//                                } else if (modelClass == Starship.class){
//                                    System.out.println("Starship " + modelClass);
//                                    populateRecyclerView(result, starships, Starship.class);
//                                    mRecyclerViewStarship.setAdapter(mMultiModelAdapter);
//
//                                } else if (modelClass == Vehicle.class){
//                                    populateRecyclerView(result, vehicles, Vehicle.class);
//                                    mRecyclerViewVehicle.setAdapter(mMultiModelAdapter);
//
//                                }

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

//        StarWarsObject clickedItem;
//
//        if(films.get(position) != null){
//            clickedItem = films.get(position);
//            Intent filmIntent = new Intent(this, FilmActivity.class);
//            filmIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
//            startActivity(filmIntent);
//        } else if (people.get(position)!=null){
//            clickedItem = people.get(position);
//            Intent peopleIntent = new Intent(this, PeopleActivity.class);
//            peopleIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
//            startActivity(peopleIntent);

//        }



    }

}

