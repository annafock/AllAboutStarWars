package com.example.allaboutstarwars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class MultiModelAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    int totalTypes;
    private OnMultiModelItemClickListener mListener;

    public interface OnMultiModelItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnMultiModelItemClickListener listener){
        mListener = listener;
    }

    public MultiModelAdapter(Context context, ArrayList<StarWarsObject> dataSet){
        mContext = context;
        mDataSet = dataSet;
    }


    public class TextTypeViewHolder extends RecyclerView.ViewHolder{

        TextView textType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            textType = (TextView) itemView.findViewById(R.id.text_view_category);

            //This is often set in ionBindViewHolder but it takes less cost to put it here
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClicked(position);
                        }

                    }
                }
            });
        }
    }

    public MultiModelAdapter(ArrayList<StarWarsObject>data, Context context) {
        this.mDataSet = data;
        this.mContext = context;
        totalTypes = mDataSet.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new TextTypeViewHolder(v);
        //TODO here we can put a switch case loop to load other type of holders
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        StarWarsObject object = mDataSet.get(position);

        //TODO find a way to make this a switch case - instanceof can't be used with swich
        if (object instanceof Film){
            Film currentItem = (Film) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.title);

        }else if(object instanceof People) {
            People currentItem = (People) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.name);
        }else if(object instanceof Planet) {
            Planet currentItem = (Planet) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.name);

        }else if(object instanceof Species) {
            Species currentItem = (Species) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.name);

        }else if(object instanceof Starship) {
            Starship currentItem = (Starship) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.name);

        }else if(object instanceof Vehicle) {
            Vehicle currentItem = (Vehicle) mDataSet.get(position);

            ((TextTypeViewHolder) holder).textType.setText(currentItem.name);

        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
