package com.example.allaboutstarwars.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Activities.PlanetActivity;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;

import static com.example.allaboutstarwars.Activities.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/19/18.
 */

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){ mListener = listener; }

    public PlanetAdapter(Context context, ArrayList<StarWarsObject> dataSet){

        mContext = context;
        mDataSet = dataSet;
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new PlanetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        StarWarsObject object = mDataSet.get(position);
        holder.textType.setText(((Planet) object).name);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class PlanetViewHolder extends RecyclerView.ViewHolder{

        public TextView textType;


        public PlanetViewHolder(View itemView) {
            super(itemView);

            textType = itemView.findViewById(R.id.text_view_category);

            //This is often set in ionBindViewHolder but it takes less cost to put it here
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClicked(position);
                            StarWarsObject clickedItem = mDataSet.get(position);

                            Intent intent = new Intent(mContext, PlanetActivity.class);
                            intent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
                            mContext.startActivity(intent);

                        }

                    }
                }
            });
        }

    }

}
