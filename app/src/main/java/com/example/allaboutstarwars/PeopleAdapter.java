package com.example.allaboutstarwars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.People;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.StarWarsViewHolder> {
    private Context mContext;
    private ArrayList<People> mPeopleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public PeopleAdapter(Context context, ArrayList<People> peopleList){
        mContext = context;
        mPeopleList = peopleList;
    }

    @Override
    public StarWarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new StarWarsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StarWarsViewHolder holder, int position) {
        People currentItem = mPeopleList.get(position);
        //String mPeople = currentItem.getClass();

        holder.mTextViewCategory.setText("hej");

    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }


    public class StarWarsViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewCategory;


        public StarWarsViewHolder(View itemView) {
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
}
