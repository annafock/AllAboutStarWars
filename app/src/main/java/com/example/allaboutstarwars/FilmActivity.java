package com.example.allaboutstarwars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

public class FilmActivity extends DetailActivity implements MultiModelAdapter.OnMultiModelItemClickListener{
    TextView textViewDetailTitle;
    private MultiModelAdapter mMultiModelAdapter;

    private StarWarsObject starWarsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starWarsObject = (StarWarsObject) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.film);

        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(People.class, ((Film) starWarsObject).charactersUrls);
        map.put(Planet.class,((Film) starWarsObject).planetsUrls);
        map.put(Species.class,((Film) starWarsObject).speciesUrls );
        map.put(Starship.class,((Film) starWarsObject).starshipsUrls );
        map.put(Vehicle.class,((Film) starWarsObject).vehiclesUrls );

        super.setRecyclerViewLayout(starWarsObject);
        super.parseJSON(map);

    }

    @Override
    public void onItemClicked(int position) {
    super.onItemClicked(position);

    }

}