package com.example.allaboutstarwars.Activities;

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
import com.example.allaboutstarwars.Adapters.MultiModelAdapter;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.allaboutstarwars.Activities.MainActivity.EXTRA_CATEGORY;

/**
 * Created by anna on 3/20/18.
 */

public class CategoryActivity extends AppCompatActivity implements MultiModelAdapter.OnMultiModelItemClickListener {
    public static final String EXTRA_STAR_WARS_OBJECT = "star wars object";

    String categoryName;
    private RecyclerView mRecyclerView;
    private TextView mTextViewTitle;
    private MultiModelAdapter mMultiModelAdapter;
    private StarWarsObject starWarsObject;
    private ArrayList<StarWarsObject> mStarWarsObjectList;
    private RequestQueue mRequestQueue;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_category);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mStarWarsObjectList = new ArrayList<>();

        Intent intent = getIntent();
        categoryName = intent.getStringExtra(EXTRA_CATEGORY);

        mTextViewTitle = (TextView) findViewById(R.id.text_view_category_title);
        mTextViewTitle.setText(categoryName);

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON(){
        String url = "https://swapi.co/api/" + categoryName +"/";

        gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++){
                                String result = jsonArray.getJSONObject(i).toString();

                                switch(categoryName){
                                    case "people":
                                        starWarsObject = gson.fromJson(result, People.class);
                                        break;
                                    case "films":
                                        starWarsObject = gson.fromJson(result, Film.class);
                                        break;
                                    case "planets":
                                        starWarsObject = gson.fromJson(result, Planet.class);
                                        break;
                                    case "species":
                                        starWarsObject = gson.fromJson(result, Species.class);
                                        break;
                                    case "starships":
                                        starWarsObject = gson.fromJson(result, Starship.class);
                                        break;
                                    case "vehicles":
                                        starWarsObject = gson.fromJson(result, Vehicle.class);
                                        break;
                                    default: break;
                                }

                                mStarWarsObjectList.add(starWarsObject);


                            }

                            mMultiModelAdapter = new MultiModelAdapter(CategoryActivity.this, mStarWarsObjectList);
                            mRecyclerView.setAdapter(mMultiModelAdapter);
                            mMultiModelAdapter.setOnItemClickListener(CategoryActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
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

    @Override
    public void onItemClicked(int position) {



        //TODO switch based on class

        StarWarsObject clickedItem = (StarWarsObject) mStarWarsObjectList.get(position);
        Intent categoryIntent = new Intent();

        if (clickedItem instanceof People){
            categoryIntent = new Intent(this, PeopleActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
        } else if (clickedItem instanceof Film){
            categoryIntent = new Intent(this, FilmActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

        } else if (clickedItem instanceof Planet){
            categoryIntent = new Intent(this, PlanetActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

        } else if (clickedItem instanceof Species){
            categoryIntent = new Intent(this, SpeciesActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

        } else if (clickedItem instanceof Starship){
            categoryIntent = new Intent(this, StarshipActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

        } else if (clickedItem instanceof Vehicle){
            categoryIntent = new Intent(this, VehicleActivity.class);
            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);


        }

        startActivity(categoryIntent);
    }

}