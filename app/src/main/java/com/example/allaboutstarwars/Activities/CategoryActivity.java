package com.example.allaboutstarwars.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadData;
import com.example.allaboutstarwars.LoadDataCallback;
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
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.Adapters.MainAdapter.EXTRA_CATEGORY;

/**
 * Created by anna on 3/20/18.
 */

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.OnMultiModelItemClickListener,
        LoadDataCallback{


    String categoryName;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private StarWarsObject starWarsObject;
    private RequestQueue mRequestQueue;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_multi);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        categoryName = intent.getStringExtra(EXTRA_CATEGORY);

        String rootUrl = "https://swapi.co/api/";
        ArrayList<String> urls = new ArrayList<>();
        urls.add(rootUrl+categoryName);

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();


        switch(categoryName){
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

        LoadData task = new LoadData(this);
        task.execute(map);

        //parseJSON();
    }

//    private void parseJSON(){
//        String url = "https://swapi.co/api/" + categoryName +"/";
//
//        gson = new Gson();
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("results");
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//                                String result = jsonArray.getJSONObject(i).toString();
//
//                                switch(categoryName){
//                                    case "people":
//                                        starWarsObject = gson.fromJson(result, People.class);
//                                        break;
//                                    case "films":
//                                        starWarsObject = gson.fromJson(result, Film.class);
//                                        break;
//                                    case "planets":
//                                        starWarsObject = gson.fromJson(result, Planet.class);
//                                        break;
//                                    case "species":
//                                        starWarsObject = gson.fromJson(result, Species.class);
//                                        break;
//                                    case "starships":
//                                        starWarsObject = gson.fromJson(result, Starship.class);
//                                        break;
//                                    case "vehicles":
//                                        starWarsObject = gson.fromJson(result, Vehicle.class);
//                                        break;
//                                    default: break;
//                                }
//
//                                mStarWarsObjectList.add(starWarsObject);
//
//
//                            }
//
//                            mCategoryAdapter = new CategoryAdapter(CategoryActivity.this, mStarWarsObjectList);
//                            mRecyclerView.setAdapter(mCategoryAdapter);
//                            mCategoryAdapter.setOnItemClickListener(CategoryActivity.this);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        mRequestQueue.add(request);
//    }

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
