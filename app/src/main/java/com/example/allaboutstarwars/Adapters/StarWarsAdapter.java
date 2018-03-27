package com.example.allaboutstarwars.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Models.Category;
import com.example.allaboutstarwars.R;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.StarWarsViewHolder> {
    private Context mContext;
    private ArrayList<Category> mCategoryList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public StarWarsAdapter(Context context, ArrayList<Category> categoryList){
        mContext = context;
        mCategoryList = categoryList;
    }

    @Override
    public StarWarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
        return new StarWarsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StarWarsViewHolder holder, int position) {
        Category currentItem = mCategoryList.get(position);
        String mCategory = currentItem.getItem();

        holder.mTextViewCategory.setText(mCategory);

    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
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
