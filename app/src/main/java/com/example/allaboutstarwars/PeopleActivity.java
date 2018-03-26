package com.example.allaboutstarwars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.allaboutstarwars.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

public class PeopleActivity extends DetailActivity implements MultiModelAdapter.OnMultiModelItemClickListener{
    TextView mTextViewDetailTitle;
    private MultiModelAdapter mMultiModelAdapter;

    private StarWarsObject starWarsObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        starWarsObject = (People) getIntent().getSerializableExtra(EXTRA_STAR_WARS_OBJECT);
        mTextViewDetailTitle = (TextView) findViewById(R.id.text_view_detail_title);
        //mTextViewDetailTitle.setText((starWarsObject.name);

        setContentView(R.layout.people);


        Map<Class, ArrayList<String>> map = new HashMap<>();
        map.put(Film.class, ((People) starWarsObject).filmsUrls);
        map.put(Species.class,((People) starWarsObject).speciesUrls );
        map.put(Starship.class,((People) starWarsObject).starshipsUrls );
        map.put(Vehicle.class,((People) starWarsObject).vehiclesUrls );

        super.setRecyclerViewLayout(starWarsObject);
        super.parseJSON(map);


    }

    @Override
    public void onItemClicked(int position) {

        //this will return an item from an enum
        String type = super.mMultiModelAdapter.getItemType();
        System.out.println("type " + type);
        System.out.println("films " + films);

        //Open activity based on enum
       // if (type == MultiModelAdapter.StarWarsType.FILM.toString()){
            StarWarsObject clickedItem = films.get(position);
            Intent filmIntent = new Intent(this, FilmActivity.class);
            filmIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
            startActivity(filmIntent);



//        }else if (type == MultiModelAdapter.StarWarsType.PEOPLE.toString()){
//            StarWarsObject clickedItem = people.get(position);
//            categoryIntent = new Intent(this, PeopleActivity.class);
//            categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
//        }



    }

}