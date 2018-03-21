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
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_DETAILS;

public class DetailActivity extends AppCompatActivity {
    String personName;
    TextView textViewDetailTitle;
    private RecyclerView mRecyclerViewFilms;
    private MultiModelAdapter mMultiModelAdapter;
    private ArrayList<StarWarsObject> mStarWarsObjectList;
    private RequestQueue mRequestQueue;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO switch based on class from intent
        Intent intent = getIntent();
        personName = intent.getStringExtra(EXTRA_DETAILS);

        setContentView(R.layout.people);

        mRecyclerViewFilms = (RecyclerView)findViewById(R.id.recycler_view_films);
        mRecyclerViewFilms.setHasFixedSize(true);
        mRecyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));

        mStarWarsObjectList = new ArrayList<>();

        textViewDetailTitle = findViewById(R.id.text_view_detail_title);

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON(){
        String url = "https://swapi.co/api/people/";

        gson = new Gson();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                String result = jsonArray.getJSONObject(i).toString();

                                StarWarsObject starWarsObject = new StarWarsObject() {
                                    @Override
                                    public int hashCode() {
                                        return super.hashCode();
                                    }
                                };
                                starWarsObject = gson.fromJson(result, People.class);
                                mStarWarsObjectList.add(starWarsObject);
                            }
                            mMultiModelAdapter = new MultiModelAdapter(DetailActivity.this, mStarWarsObjectList);
                            mRecyclerViewFilms.setAdapter(mMultiModelAdapter);


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
}
