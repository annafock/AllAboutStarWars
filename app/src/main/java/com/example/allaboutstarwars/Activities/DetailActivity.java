package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class DetailActivity extends AppCompatActivity implements
        CategoryAdapter.OnMultiModelItemClickListener, LoadDataCallback {

    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

    }

    @Override
    public void onItemClicked(int position) {
        //On click is set in the CategoryAdapter
    }

    @Override
    public void sendUpdate(int itemCount) {
            showProgressBarSpinner();

    }

    public void showProgressBarSpinner() {

        if (CardView.class != null) {
            View progressBarSpinner = (View) findViewById(R.id.progress_bar_spinner);
            if (progressBarSpinner != null) {
                progressBarSpinner.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideProgressBarSpinner() {

        if (CardView.class != null) {
            View progressBarSpinner = (View) findViewById(R.id.progress_bar_spinner);
            if (progressBarSpinner != null) {
                progressBarSpinner.setVisibility(View.INVISIBLE);
            }
        }
    }


    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {

        if (0 != starWarsArray.size()) {
            hideProgressBarSpinner();

            mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_multi);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            Log.d("Before calling adapter "+ this.getClass(), "message");
            mCategoryAdapter = new CategoryAdapter(this, starWarsArray);
            mRecyclerView.setAdapter(mCategoryAdapter);
            mCategoryAdapter.setOnItemClickListener(this);

            Log.d("After calling adapter " + this.getClass(), "message");
        }
    }

}

