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
    private RecyclerView mRecyclerViewFilms;
    private MultiModelAdapter mMultiModelAdapter;
    private ArrayList<StarWarsObject> mStarWarsObjectList;
    private RequestQueue mRequestQueue;
    private ArrayList<String> filmUrl;
    private Film film;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StarWarsObject starWarsObject = (StarWarsObject) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);
        if(starWarsObject instanceof People){


        setContentView(R.layout.people);

        mRecyclerViewFilms = (RecyclerView)findViewById(R.id.recycler_view_films);
        mRecyclerViewFilms.setHasFixedSize(true);
        mRecyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));

        textViewDetailTitle = findViewById(R.id.text_view_detail_title);
        textViewDetailTitle.setText(((People) starWarsObject).name);

        mStarWarsObjectList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        filmUrl = ((People) starWarsObject).filmsUrls;

        parseJSON();


    }}

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

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

        }

        mMultiModelAdapter = new MultiModelAdapter(DetailActivity.this, mStarWarsObjectList);
        mMultiModelAdapter.notifyDataSetChanged();
        mRecyclerViewFilms.setAdapter(mMultiModelAdapter);
    }

}

