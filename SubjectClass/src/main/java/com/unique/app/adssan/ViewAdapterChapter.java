package com.unique.app.adssan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class ViewAdapterChapter extends RecyclerView
        .Adapter<ViewAdapterChapter
        .DataObjectHolder> {
    private static String LOG_TAG = "ViewAdapterSubject";
    private ArrayList<CardChapterData> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        private Context context;
        TextView label;
        TextView oneletter;
        TextView dateTime;
        RelativeLayout relativeLayout;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView22);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            relativeLayout = itemView.findViewById(R.id.button2);
            context = itemView.getContext();
            oneletter = itemView.findViewById(R.id.oneletter);
//            relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.v("sadasdasdas", label.getText().toString());
////                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
////                    feedReaderDbHelper.getChapter(label.getText().toString());
////
////                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
////                    SharedPreferences.Editor editor = pref.edit();
////
////                    editor.putString("chapter", label.getText().toString());
////
////
////                    editor.apply(); // commit changes
////
////                    Intent intent = new Intent(context, ActivityQuestions.class);
////                    intent.putExtra("chapter", label.getText().toString());
////
////                    context.startActivity(intent);
//
//                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//
//
//                    editor.putString("chapter", label.getText().toString()); // Saving string
//
//                    editor.apply(); //
//
//                    Intent intent = new Intent(context, SelectType.class);
//                    intent.putExtra("chapter", label.getText().toString());
//                    // intent.putExtra("selected",label.getText().toString());
//                    context.startActivity(intent);
//
//                }
//            });
            Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public ViewAdapterChapter(ArrayList<CardChapterData> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.label.setText(mDataset.get(position).getChaptername());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("sadasdasdas", mDataset.get(position).getChaptername());
//                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
//                    feedReaderDbHelper.getChapter(label.getText().toString());
//
//                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//
//                    editor.putString("chapter", label.getText().toString());
//
//
//                    editor.apply(); // commit changes
//
//                    Intent intent = new Intent(context, ActivityQuestions.class);
//                    intent.putExtra("chapter", label.getText().toString());
//
//                    context.startActivity(intent);

                SharedPreferences pref = holder.context.getSharedPreferences("MyPref", holder.context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();


                editor.putString("chapter", mDataset.get(position).getChaptername()); // Saving string

                editor.apply(); //

                Intent intent = new Intent(holder.context, SelectType.class);
                intent.putExtra("chapter", mDataset.get(position).getChaptername());
                // intent.putExtra("selected",label.getText().toString());
                holder.context.startActivity(intent);

            }
        });

    }

    public void addItem(CardChapterData dataObj, int index) {
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