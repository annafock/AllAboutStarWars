package com.example.allaboutstarwars;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.StarWarsObject;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class MultiModelAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    int totalTypes;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MultiModelAdapter(Context context, ArrayList<StarWarsObject> dataSet){
        mContext = context;
        mDataSet = dataSet;
    }


    public static class TextTypeViewHolder extends RecyclerView.ViewHolder{

        TextView textType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);
            textType = (TextView) itemView.findViewById(R.id.text_view_category);
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

        if (object != null) {
            switch (object.getClass().getName()) {
                case "People":
                    People currentItem = (People)mDataSet.get(position);

                    ((TextTypeViewHolder) holder).textType.setText(currentItem.name);
                    break;
            }
        }
    }


    public class MultiModelViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewCategory;


        public MultiModelViewHolder(View itemView) {
            super(itemView);
            mTextViewCategory = itemView.findViewById(R.id.text_view_category);

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

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
