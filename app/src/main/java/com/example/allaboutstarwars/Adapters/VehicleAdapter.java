package com.example.allaboutstarwars.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;
import com.example.allaboutstarwars.Activities.VehicleActivity;

import java.util.ArrayList;

import static com.example.allaboutstarwars.Activities.CategoryActivity.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/19/18.
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){ mListener = listener; }

    public VehicleAdapter(Context context, ArrayList<StarWarsObject> dataSet){

        mContext = context;
        mDataSet = dataSet;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new VehicleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        StarWarsObject object = mDataSet.get(position);
        holder.textType.setText(((Vehicle) object).name);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class VehicleViewHolder extends RecyclerView.ViewHolder{

        public TextView textType;


        public VehicleViewHolder(View itemView) {
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

                            Intent intent = new Intent(mContext, VehicleActivity.class);
                            intent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
                            mContext.startActivity(intent);

                        }

                    }
                }
            });
        }

    }

}