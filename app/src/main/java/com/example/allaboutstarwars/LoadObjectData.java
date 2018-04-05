package com.example.allaboutstarwars;

import android.os.AsyncTask;

import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.Models.CategoryName;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsHeader;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadObjectData extends AsyncTask<Map<Class, ArrayList<String>>, Integer, ArrayList<StarWarsObject>>{

    LoadDataCallback loadDataCallback;
    StarWarsObject starWarsObject;
    ArrayList<StarWarsObject> starWarsObjectList;

    OkHttpClient client = new OkHttpClient();

    public LoadObjectData(LoadDataCallback loadDataCallback) {
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

        for (final Map.Entry<Class,ArrayList<String>> entry: maps[0].entrySet()){

            final Class modelClass = entry.getKey();
            //Add header to the list
            starWarsObjectList.add(new StarWarsHeader(modelClass.getSimpleName()));

            //Load objects and add to starwarsList
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

                    String object = jsonObject.toString();

                    if (modelClass == Category.class ) {
                        CategoryName categoryName;

                        starWarsObject = gson.fromJson(object, Category.class);

                        //Loop through fields in category object and add each field to starwarsojbectlist
                        Field[] fields = starWarsObject.getClass().getDeclaredFields();

                        for (Field f: fields){
                            System.out.println("f " + f);
                            categoryName = new CategoryName(f.getName());
                            System.out.println("categoryName " + categoryName.getCategoryName());
                            starWarsObjectList.add(categoryName);
                        }


                    }else if (modelClass == Film.class) {
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


                } catch (IOException | IllegalStateException | JsonSyntaxException e ) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

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