package com.example.allaboutstarwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DetailActivity extends AppCompatActivity implements MultiModelAdapter.OnMultiModelItemClickListener{
    TextView textViewDetailTitle;
    private RecyclerView mRecyclerViewFilms, mRecyclerViewSpecies, mRecyclerViewPeople,
            mRecyclerViewPlanet, mRecyclerViewStarship, mRecyclerViewVehicle;
    private MultiModelAdapter mMultiModelAdapter;
    private StarWarsObject starWarsObject;
    private ArrayList<StarWarsObject> mStarWarsObjectList;
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

        mStarWarsObjectList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        starWarsObject = (StarWarsObject) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        if(starWarsObject instanceof People){

            setContentView(R.layout.people);
            setRecyclerViewLayout();

            Map<Class, ArrayList<String>> map = new HashMap<>();
            map.put(Film.class, ((People) starWarsObject).filmsUrls);
            map.put(Species.class,((People) starWarsObject).speciesUrls );
            map.put(Starship.class,((People) starWarsObject).starshipsUrls );
            map.put(Vehicle.class,((People) starWarsObject).vehiclesUrls );

            parseJSON(map);

        } else if(starWarsObject instanceof Film ){

            setContentView(R.layout.film);
            setRecyclerViewLayout();

            Map<Class, ArrayList<String>> map = new HashMap<>();
            map.put(People.class, ((Film) starWarsObject).charactersUrls);
            map.put(Planet.class, ((Film) starWarsObject).planetsUrls);
            map.put(Species.class,((Film) starWarsObject).speciesUrls );
            map.put(Starship.class,((Film) starWarsObject).starshipsUrls );
            map.put(Vehicle.class,((Film) starWarsObject).vehiclesUrls );

            parseJSON(map);

            textViewDetailTitle = findViewById(R.id.text_view_detail_title);
            textViewDetailTitle.setText(((Film) starWarsObject).title);

        }
    }

    private void parseJSON(Map<Class, ArrayList<String>> map){

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

                                // Parse results and populates each recyclerview
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

                                }
                                else if (modelClass == Vehicle.class){

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

    public void setRecyclerViewLayout(){

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

    public void populateRecyclerView(String requestResult, ArrayList<StarWarsObject> starWarsObjectList, Class modelClass){
        starWarsObject = (StarWarsObject)gson.fromJson(requestResult, modelClass);
        starWarsObjectList.add(starWarsObject);
        mMultiModelAdapter = new MultiModelAdapter(DetailActivity.this, starWarsObjectList);

        mMultiModelAdapter.setOnItemClickListener(DetailActivity.this);

    }

    @Override
    public void onItemClicked(int position) {

        System.out.println("clicked");
        //TODO switch based on class
        Intent categoryIntent = new Intent(this, DetailActivity.class);
        StarWarsObject clickedItemFilm = (Film) films.get(position);

        categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItemFilm);

        startActivity(categoryIntent);
    }

}

