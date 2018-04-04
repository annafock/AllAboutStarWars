package com.example.allaboutstarwars.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allaboutstarwars.Activities.FilmActivity;
import com.example.allaboutstarwars.Activities.PeopleActivity;
import com.example.allaboutstarwars.Activities.PlanetActivity;
import com.example.allaboutstarwars.Activities.SpeciesActivity;
import com.example.allaboutstarwars.Activities.StarshipActivity;
import com.example.allaboutstarwars.Activities.VehicleActivity;
import com.example.allaboutstarwars.Models.Film;
import com.example.allaboutstarwars.Models.People;
import com.example.allaboutstarwars.Models.Planet;
import com.example.allaboutstarwars.Models.Species;
import com.example.allaboutstarwars.Models.StarWarsHeader;
import com.example.allaboutstarwars.Models.StarWarsObject;
import com.example.allaboutstarwars.Models.Starship;
import com.example.allaboutstarwars.Models.Vehicle;
import com.example.allaboutstarwars.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by anna on 3/19/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private ArrayList<StarWarsObject> mDataSet;
    int totalTypes;
    private OnMultiModelItemClickListener mListener;
    public static final String EXTRA_STAR_WARS_OBJECT = "star wars object";
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public interface OnMultiModelItemClickListener {
        void onItemClicked(int position);
    }

    public void setOnItemClickListener(OnMultiModelItemClickListener listener){
        mListener = listener;
    }

    public CategoryAdapter(Context context, ArrayList<StarWarsObject> dataSet){

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

                            //Initiate object of the type the user clicked and send the object to a detail activity
                            StarWarsObject clickedItem = (StarWarsObject) mDataSet.get(position);
                            Intent categoryIntent = new Intent();

                            if (clickedItem instanceof People){
                                categoryIntent = new Intent(mContext, PeopleActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);
                            } else if (clickedItem instanceof Film){
                                categoryIntent = new Intent(mContext, FilmActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

                            } else if (clickedItem instanceof Planet){
                                categoryIntent = new Intent(mContext, PlanetActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

                            } else if (clickedItem instanceof Species){
                                categoryIntent = new Intent(mContext, SpeciesActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

                            } else if (clickedItem instanceof Starship){
                                categoryIntent = new Intent(mContext, StarshipActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);

                            } else if (clickedItem instanceof Vehicle){
                                categoryIntent = new Intent(mContext, VehicleActivity.class);
                                categoryIntent.putExtra(EXTRA_STAR_WARS_OBJECT, clickedItem);


                            }

                            mContext.startActivity(categoryIntent);
                        }

                    }
                }
            });
        }

    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        TextView textTypeHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            textTypeHeader = (TextView) itemView.findViewById(R.id.header_id);

        }

    }


    public CategoryAdapter(ArrayList<StarWarsObject>data, Context context) {
        this.mDataSet = data;
        this.mContext = context;
        totalTypes = mDataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER){
            View v = LayoutInflater.from(mContext).inflate(R.layout.header_layout, parent, false);
            return new HeaderViewHolder(v);

        }else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.starwars_item, parent, false);
            return new TextTypeViewHolder(v);
        }

        throw new RuntimeException("No match for " + viewType + ".");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        StarWarsObject object = mDataSet.get(position);

        //Set viewholder for Header
        if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).textTypeHeader.setText(((StarWarsHeader) object).getHeaderTitle());

            //Set viewholder for TextType
        } else if (holder instanceof TextTypeViewHolder){

            if (object instanceof Film){

                ((TextTypeViewHolder) holder).textType.setText(((Film) object).title);

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
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position){
        int viewType = 0;

        if (mDataSet.get(position) instanceof Film || mDataSet.get(position) instanceof People
                || mDataSet.get(position) instanceof Planet || mDataSet.get(position) instanceof Species
                || mDataSet.get(position) instanceof Starship || mDataSet.get(position) instanceof Vehicle){
            viewType= 1;
        } else if(mDataSet.get(position) instanceof StarWarsHeader){
            viewType = 0;
        }
        return viewType;
    }

}
