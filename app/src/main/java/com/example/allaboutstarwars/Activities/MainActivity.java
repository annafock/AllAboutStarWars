package com.example.allaboutstarwars.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allaboutstarwars.Adapters.StarWarsAdapter;
import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StarWarsAdapter.OnItemClickListener{
    public static final String EXTRA_CATEGORY = "category_name";
    private RecyclerView mRecyclerView;
    private StarWarsAdapter mStarWarsAdapter;
    private ArrayList<Category> mCategoryList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_category);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCategoryList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON(){
        String url = "https://swapi.co/api/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.names();

                            for (int i = 0; i < jsonArray.length(); i++){
                                String hit = jsonArray.get(i).toString();
                                mCategoryList.add(new Category(hit));
                            }

                            mStarWarsAdapter = new StarWarsAdapter(MainActivity.this, mCategoryList);
                            mRecyclerView.setAdapter(mStarWarsAdapter);
                            mStarWarsAdapter.setOnItemClickListener(MainActivity.this);

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
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClicked(int position) {
        Intent categoryIntent = new Intent(this, CategoryActivity.class);
        Category clickedItem = mCategoryList.get(position);

        categoryIntent.putExtra(EXTRA_CATEGORY, clickedItem.getItem());

        startActivity(categoryIntent);
    }
}
