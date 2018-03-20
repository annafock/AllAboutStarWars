//package com.example.allaboutstarwars;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.allaboutstarwars.Models.People;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//import static com.example.allaboutstarwars.MainActivity.EXTRA_CATEGORY;
//
///**
// * Created by anna on 3/20/18.
// */
//
//public class CategoryActivity extends AppCompatActivity implements PeopleAdapter.OnItemClickListener{
//    public static final String EXTRA_DETAILS = "details";
//
//    String categoryName;
//    String hitName = "name";
//    private RecyclerView mRecyclerView;
//    private PeopleAdapter mPeopleAdapter;
//    private ArrayList<People> mStarWarsList;
//    private RequestQueue mRequestQueue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);
//
//        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mStarWarsList = new ArrayList<>();
//
//        Intent intent = getIntent();
//        categoryName = intent.getStringExtra(EXTRA_CATEGORY);
//
//        //Switch loop to determine item for given category
//        switch(categoryName){
//            case "films": hitName = "title";
//                break;
//            default: break;
//
//        }
//
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        parseJSON();
//
//    }
//
//    private void parseJSON(){
//        String url = "https://swapi.co/api/" + categoryName +"/";
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("results");
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//                                JSONObject result = jsonArray.getJSONObject(i);
//                                String item = result.getString(hitName);
//                                mStarWarsList.add(new People(item));
//                            }
//
//                            mPeopleAdapter = new PeopleAdapter(CategoryActivity.this, mStarWarsList);
//                            mRecyclerView.setAdapter(mPeopleAdapter);
//                            mPeopleAdapter.setOnItemClickListener(CategoryActivity.this);
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
//
//    @Override
//    public void onItemClicked(int position) {
//        Intent categoryIntent = new Intent(this, DetailActivity.class);
//        People clickedItem = mStarWarsList.get(position);
//
//        categoryIntent.putExtra(EXTRA_DETAILS, clickedItem.getmTitle());
//
//        startActivity(categoryIntent);
//    }
//}
