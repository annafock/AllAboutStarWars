package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.Activities.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/27/18.
 */

public class VehicleActivity extends DetailActivity{
    TextView mTextViewDetailTitle;
    private StarWarsObject starWarsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recieves object from categoriy activity
        starWarsObject = (Vehicle) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.species);

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(Film.class, ((Vehicle) starWarsObject).filmsUrls);
        map.put(People.class,((Vehicle) starWarsObject).pilotsUrls);

        //Sets all the recyclerviews in this view
        super.setRecyclerViewLayout(starWarsObject);

        //Populates all recyclerviews in this view
        super.parseJSON(map);

        //Sets all the recyclerviews in this view
        mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
        mTextViewDetailTitle.setText(((Vehicle) starWarsObject).name);

    }

}
