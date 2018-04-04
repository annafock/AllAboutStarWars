package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadData;
import com.example.allaboutstarwars.LoadData2;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.Adapters.CategoryAdapter.EXTRA_STAR_WARS_OBJECT;

public class FilmActivity extends AppCompatActivity implements
        CategoryAdapter.OnMultiModelItemClickListener, LoadDataCallback {

    TextView mTextViewDetailTitle;
    private StarWarsObject starWarsObject;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recieves object from categoriy activity
        starWarsObject = (Film) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.film);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_multi);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(People.class, ((Film) starWarsObject).charactersUrls);
        map.put(Planet.class,((Film) starWarsObject).planetsUrls);
        map.put(Species.class,((Film) starWarsObject).speciesUrls );
        map.put(Starship.class,((Film) starWarsObject).starshipsUrls );
        map.put(Vehicle.class,((Film) starWarsObject).vehiclesUrls );

        LoadData2 task = new LoadData2(this);
        task.execute(map);
    }

    @Override
    public void sendUpdate(int itemCount) {

    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
        mTextViewDetailTitle.setText(((Film) starWarsObject).title);

        mCategoryAdapter = new CategoryAdapter(FilmActivity.this, starWarsArray);
        mRecyclerView.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnItemClickListener(FilmActivity.this);

    }


    @Override
    public void onItemClicked(int position) {

    }
}