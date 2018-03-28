package com.example.allaboutstarwars.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Activities.PeopleActivity;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;

import static com.example.allaboutstarwars.Adapters.MultiModelAdapter.EXTRA_STAR_WARS_OBJECT;

/**
 * Created by anna on 3/19/18.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){ mListener = listener; }

    public PeopleAdapter(Context context, ArrayList<StarWarsObject> dataSet){

        mContext = context;
        mDataSet = dataSet;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new PeopleViewHolder(v);
    }


    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        StarWarsObject object = mDataSet.get(position);
        holder.textType.setText(((People) object).name);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder{

        public TextView textType;


        public PeopleViewHolder(View itemView) {
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

                            Intent intent = new Intent(mContext, PeopleActivity.class);
                            intent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
                            mContext.startActivity(intent);
                        }

                    }
                }
            });
        }

    }


}
