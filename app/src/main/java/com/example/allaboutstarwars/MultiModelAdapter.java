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

        TextView textType, textTypeName;


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
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        StarWarsObject object = mDataSet.get(position);

        //TODO find a way to make this a switch case - instanceof can't be used with switch
        if (object instanceof Film){

            ((TextTypeViewHolder) holder).textType.setText(((Film) object).title);
            ((TextTypeViewHolder) holder).textType.setTag(object);

        }else if(object instanceof People) {

            ((TextTypeViewHolder) holder).textType.setText(((People) object).name);

        }else if(object instanceof Planet) {

            ((TextTypeViewHolder) holder).textType.setText(((Planet) object).name);

        }else if(object instanceof Species) {

            ((TextTypeViewHolder) holder).textType.setText(((Species) object).name);

        }else if(object instanceof Starship) {

            ((TextTypeViewHolder) holder).textType.setText(((Starship) object).name);

        }else if(object instanceof Vehicle) {

            ((TextTypeViewHolder) holder).textType.setText(((Vehicle) object).name);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
