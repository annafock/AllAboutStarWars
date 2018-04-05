package com.example.allaboutstarwars;

import android.os.AsyncTask;
import android.util.Log;

import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadArrayData extends AsyncTask<Map<Class, ArrayList<String>>, Integer, ArrayList<StarWarsObject>>{

    LoadDataCallback loadDataCallback;
    StarWarsObject starWarsObject;
    ArrayList<StarWarsObject> starWarsObjectList;
    Date currentTime = Calendar.getInstance().getTime();

    OkHttpClient client = new OkHttpClient();

    public LoadArrayData(LoadDataCallback loadDataCallback) {
        this.loadDataCallback = loadDataCallback;
    }

    @Override
    protected void onPreExecute() {

        starWarsObjectList = new ArrayList<>();
    }

    @Override
    protected ArrayList<StarWarsObject> doInBackground(Map<Class, ArrayList<String>>... maps) {
        final Gson gson = new Gson();
        String url;

        Log.d("Before request" + this.getClass(), "message");
        for (final Map.Entry<Class,ArrayList<String>> entry: maps[0].entrySet()){
            final Class modelClass = entry.getKey();

            ArrayList<String> value = entry.getValue();
            for (String urlString : value){
                url = urlString;



                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;


                try {
                    response = client.newCall(request).execute();

                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);

                    JSONArray jsonArray = jsonObject.getJSONArray("results");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        String object = jsonArray.getJSONObject(i).toString();

                        if (modelClass == Film.class) {
                            starWarsObject = gson.fromJson(object, Film.class);
                            starWarsObjectList.add(starWarsObject);

                        }else if(modelClass == People.class){
                            starWarsObject = gson.fromJson(object, People.class);
                            starWarsObjectList.add(starWarsObject);

                        }else if(modelClass == Planet.class){
                            starWarsObject = gson.fromJson(object, Planet.class);
                            starWarsObjectList.add(starWarsObject);

                        }else if(modelClass == Species.class){
                            starWarsObject = gson.fromJson(object, Species.class);
                            starWarsObjectList.add(starWarsObject);

                        }else if(modelClass == Starship.class){
                            starWarsObject = gson.fromJson(object, Starship.class);
                            starWarsObjectList.add(starWarsObject);

                        }else if(modelClass == Vehicle.class){
                            starWarsObject = gson.fromJson(object, Vehicle.class);
                            starWarsObjectList.add(starWarsObject);
                        }

                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        Log.d("After request" + this.getClass(), "message");
        return starWarsObjectList;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        loadDataCallback.sendUpdate(values[0]);

    }

    @Override
    //runs on UI thread
    protected void onPostExecute(ArrayList<StarWarsObject> starWarsObjectList){
        loadDataCallback.onDataLoaded(starWarsObjectList);

    }

}