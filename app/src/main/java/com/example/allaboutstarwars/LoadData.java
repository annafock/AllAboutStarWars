package com.example.allaboutstarwars;

import android.os.AsyncTask;

import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadData extends AsyncTask<String, Integer, ArrayList<StarWarsObject>>{

    LoadDataCallback loadDataCallback;
    StarWarsObject starWarsObject;
    ArrayList<StarWarsObject> starWarsObjectList;

    OkHttpClient client = new OkHttpClient();

    public LoadData(LoadDataCallback loadDataCallback) {
        this.loadDataCallback = loadDataCallback;
    }

    @Override
    protected void onPreExecute() {

        starWarsObjectList = new ArrayList<>();
    }

    @Override
    protected ArrayList<StarWarsObject> doInBackground(String ... params) {

        final Gson gson = new Gson();

        Request request = new Request.Builder()
                .url(params[0])
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();

            String jsonData = response.body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            for (int i = 0; i < jsonArray.length(); i++) {
                String object = jsonArray.getJSONObject(i).toString();
                System.out.println(object);

                starWarsObject = gson.fromJson(object, StarWarsObject.class);
                starWarsObjectList.add(starWarsObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
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
