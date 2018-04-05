package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadObjectData;
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

public class FilmActivity extends DetailActivity {

    private StarWarsObject starWarsObject;
    TextView mTextViewDetailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recieves object from categoriy activity
        starWarsObject = (Film) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.film);

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(People.class, ((Film) starWarsObject).charactersUrls);
        map.put(Planet.class,((Film) starWarsObject).planetsUrls);
        map.put(Species.class,((Film) starWarsObject).speciesUrls );
        map.put(Starship.class,((Film) starWarsObject).starshipsUrls );
        map.put(Vehicle.class,((Film) starWarsObject).vehiclesUrls );

        LoadObjectData task = new LoadObjectData(this);
        task.execute(map);
    }

    @Override
    public void sendUpdate(int itemCount) {

    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        super.onDataLoaded(starWarsArray);

           mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
           mTextViewDetailTitle.setText(((Film) starWarsObject).title);

    }


    @Override
    public void onItemClicked(int position) {

    }
}