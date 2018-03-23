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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

public class DetailActivity extends AppCompatActivity {
    String personName;
    TextView textViewDetailTitle;
    private RecyclerView mRecyclerViewFilms, mRecyclerViewSpecies, mRecyclerViewPeople,
            mRecyclerViewPlanet, mRecyclerViewStarship, mRecyclerViewVehicle;
    private MultiModelAdapter mMultiModelAdapter;
    private StarWarsObject starWarsObject;
    private ArrayList<StarWarsObject> mStarWarsObjectList;
    private RequestQueue mRequestQueue;
    private ArrayList<String> filmUrl;
    private Film film;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStarWarsObjectList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        starWarsObject = (People) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);
        if(starWarsObject instanceof People){

            setContentView(R.layout.people);
            setRecyclerView();

            textViewDetailTitle = findViewById(R.id.text_view_detail_title);
            textViewDetailTitle.setText(((People) starWarsObject).name);

            filmUrl = ((People) starWarsObject).filmsUrls;

            parseJSON();

            mMultiModelAdapter = new MultiModelAdapter(DetailActivity.this, mStarWarsObjectList);
            mRecyclerViewFilms.setAdapter(mMultiModelAdapter);
        }
    }

    private void parseJSON(){

        gson = new Gson();

        String url;
        for (int i = 0; i < filmUrl.size(); i++){
            url = filmUrl.get(i);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String result = response.toString();

                            film = gson.fromJson(result, Film.class);

                            mStarWarsObjectList.add(film);
                            mMultiModelAdapter.notifyDataSetChanged();

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

    //TODO not if instance of class - but all this if the class is People
    public void setRecyclerView(){

        if(!(starWarsObject instanceof Film)) {
            mRecyclerViewFilms = (RecyclerView) findViewById(R.id.recycler_view_films);
            mRecyclerViewFilms.setHasFixedSize(true);
            mRecyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        }

        if(!(starWarsObject instanceof People)) {
            mRecyclerViewPeople = (RecyclerView) findViewById(R.id.recycler_view_people);
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

}

