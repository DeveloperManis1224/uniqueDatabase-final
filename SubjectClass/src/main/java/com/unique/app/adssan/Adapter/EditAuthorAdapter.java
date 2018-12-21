package com.unique.app.adssan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unique.app.adssan.ActivityChapters;
import com.unique.app.adssan.DataEditorial;
import com.unique.app.adssan.R;
import com.unique.app.adssan.SelectPart;
import com.unique.app.adssan.ViewAdapterChapter;

import java.util.ArrayList;

public class EditAuthorAdapter extends RecyclerView
        .Adapter<EditAuthorAdapter.DataObjectHolder> {
    private static String LOG_TAG = "ViewAdapterPart";
    private ArrayList<DataEditorial> mDataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
       ImageView drImg;
       TextView txtDr;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtDr = (TextView) itemView.findViewById(R.id.txt_dr);
           drImg = (ImageView) itemView.findViewById(R.id.img_dr);
        }
    }

    public EditAuthorAdapter(ArrayList<DataEditorial> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public EditAuthorAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_board_lyt, parent, false);


        DataObjectHolder dataObjectHolder = new EditAuthorAdapter.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {


        holder.txtDr.setText(mDataset.get(position).get_drName());


        if(mDataset.get(position).get_drName().equalsIgnoreCase("Subjects Contributors")) {
        holder.drImg.setVisibility(View.GONE);
        holder.txtDr.setGravity(Gravity.CENTER);
        }
        else {
            Glide.with(holder.drImg.getContext()).load(mDataset.get(position).get_drImage()).into(holder.drImg);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
