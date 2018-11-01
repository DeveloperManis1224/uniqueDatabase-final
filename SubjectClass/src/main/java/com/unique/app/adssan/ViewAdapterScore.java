package com.unique.app.adssan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class ViewAdapterScore extends RecyclerView
        .Adapter<ViewAdapterScore
        .DataObjectHolder> {
    private static String LOG_TAG = "ViewAdapterSubject";
    private ArrayList<CardScoreData> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        private Context context;
        TextView subject;
        TextView read;

        TextView unread;


        public DataObjectHolder(View itemView)
        {
            super(itemView);
            subject =    itemView.findViewById(R.id.subject);
            read = itemView.findViewById(R.id.read);

            context = itemView.getContext();
            unread = itemView.findViewById(R.id.unread);


        }


    }


    public ViewAdapterScore(ArrayList<CardScoreData> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_score_chart_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {



        Log.v("sdas",mDataset.get(position).getRead()+mDataset.get(position).getUnread());
        holder.subject.setText(mDataset.get(position).getSubject());
        holder.read.setText("Read :"+mDataset.get(position).getRead());
        holder.unread.setText("Unread :"+mDataset.get(position).getUnread());
    }

    public void addItem(CardScoreData dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}