//package com.example.allaboutstarwars;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import static com.example.allaboutstarwars.CategoryActivity.EXTRA_DETAILS;
//
//public class DetailActivity extends AppCompatActivity {
//    String detailName;
//    ImageView imageView;
//    TextView textViewTitle;
//    TextView textViewDetail;
//    private RequestQueue mRequestQueue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        Intent intent = getIntent();
//        detailName = intent.getStringExtra(EXTRA_DETAILS);
//
//        imageView = findViewById(R.id.image_view_detail);
//        textViewTitle = findViewById(R.id.text_view_detail_title);
//        textViewDetail = findViewById(R.id.text_view_detail);
//
//        textViewTitle.setText(detailName);
//
//        mRequestQueue = Volley.newRequestQueue(this);
//
//        //parseJSON();
//
//    }
//
////    private void parseJSON(){
////        String url = "https://swapi.co/api/" + detailName +"/schema";
////
////        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
////                new Response.Listener<JSONObject>() {
////                    @Override
////                    public void onResponse(JSONObject response) {
////                        response.getString();
////                        try {
////                            JSONArray jsonArray = response.getJSONArray("results");
////
////                            for (int i = 0; i < jsonArray.length(); i++){
////                                JSONObject result = jsonArray.getJSONObject(i);
////                                String item = result.getString(hitName);
////
////                                mStarWarsList.add(new People(item));
////                            }
////
////                            mStarWarsAdapter = new PeopleAdapter(CategoryActivity.this, mStarWarsList);
////                            mRecyclerView.setAdapter(mStarWarsAdapter);
////                            mStarWarsAdapter.setOnItemClickListener(CategoryActivity.this);
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                error.printStackTrace();
////            }
////        });
////
////        mRequestQueue.add(request);
////    }
//}
