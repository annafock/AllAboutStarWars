package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.widget.TextView;

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

import static com.example.allaboutstarwars.Activities.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/27/18.
 */

public class PlanetActivity extends DetailActivity{
    TextView mTextViewDetailTitle;
    private StarWarsObject starWarsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starWarsObject = (Planet) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.starship);


        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(Film.class, ((Planet) starWarsObject).filmsUrls);
        map.put(People.class,((Planet) starWarsObject).residentsUrls);

        super.setRecyclerViewLayout(starWarsObject);
        super.parseJSON(map);

        mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
        mTextViewDetailTitle.setText(((Planet) starWarsObject).name);

    }

}
