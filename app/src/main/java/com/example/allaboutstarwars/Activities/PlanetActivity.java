package com.example.allaboutstarwars.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.allaboutstarwars.Adapters.CategoryAdapter;
import com.example.allaboutstarwars.LoadDataCallback;
import com.example.allaboutstarwars.LoadObjectData;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.Adapters.CategoryAdapter.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/27/18.
 */

public class PlanetActivity extends DetailActivity {

    private StarWarsObject starWarsObject;
    TextView mTextViewDetailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recieves object from categoriy activity
        starWarsObject = (Planet) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);

        setContentView(R.layout.starship);

        //Saves map of url:s with more relating info about this object
        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(Film.class, ((Planet) starWarsObject).filmsUrls);
        map.put(People.class,((Planet) starWarsObject).residentsUrls);

        LoadObjectData task = new LoadObjectData(this);
        task.execute(map);

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDataLoaded(ArrayList<StarWarsObject> starWarsArray) {
        super.onDataLoaded(starWarsArray);

        mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
        mTextViewDetailTitle.setText(((Planet) starWarsObject).name);

    }

    @Override
    public void sendUpdate(int itemCount) {

    }
}
