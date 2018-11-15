package com.unique.app.adssan;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class ViewAdapterQuestions extends RecyclerView
        .Adapter<ViewAdapterQuestions
        .DataObjectHolder> {
    private static String LOG_TAG = "ViewAdapterSubject";
    private ArrayList<CardQuestionsData> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        private Context context;
        TextView label;
        TextView dateTime;
        LinearLayout lyt;
        CardView cardView;
        ImageView imgTick;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView22);
            cardView = itemView.findViewById(R.id.button2);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            imgTick = (ImageView) itemView.findViewById(R.id.img_tick);
            lyt = itemView.findViewById(R.id.lyt_ques);

            context = itemView.getContext();


//            relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.v("sadasdasdas", label.getText().toString());
//                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
//                    feedReaderDbHelper.getChapter(label.getText().toString());
//                    SharedPreferences settings = context.getSharedPreferences("MyPref", MODE_PRIVATE);
//                    String subject = settings.getString("subject", "");
//                    String part = settings.getString("part", "");
//
//
//                    Intent intent = new Intent(context, ActivityChapters.class);
//
//                    intent.putExtra("", label.getText().toString());
//                    intent.putExtra("subject", subject);
//                    intent.putExtra("value", part);
//                    context.startActivity(intent);
//                }
//            });
//            Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public ViewAdapterQuestions(ArrayList<CardQuestionsData> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_questions, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int clickPosition;
                try {
                    final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(view.getContext());
                    clickPosition = ActivityQuestions.mRecyclerView.getChildAdapterPosition(view);
                    Log.e("asasasasas1111",""+mDataset.get(clickPosition).get_id());
                    feedReaderDbHelper.setColor(mDataset.get(clickPosition).get_id());
                    ActivityQuestions.mAdapter.notifyItemChanged(clickPosition);
                }catch(Exception e)
                {
                    Log.e("asasasasas1111",""+e.getMessage());
                    e.printStackTrace();
                }
            }
        });


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {


        holder.label.setText(mDataset.get(position).getChaptername());
        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(holder.context);
//        if(feedReaderDbHelper.getColor(mDataset.get(position).get_id()).equals("1"))
//        {
//            holder.label.setTextColor(Color.parseColor("#2A6BAC"));
//            holder.imgTick.setBackgroundResource(R.drawable.check);
//        }
//       // holder.imgTick.setBackgroundResource(R.drawable.blue_tick);

//        holder.lyt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.e("asasasasas1111",""+mDataset.get(position).get_id());
//                feedReaderDbHelper.setColor(mDataset.get(position).get_id());
//
////                Intent in = new Intent(holder.context,ActivityQuestions.class);
////                holder.context.startActivity(in);
//              //  ActivityQuestions.mRecyclerView.setAdapter(ActivityQuestions.mAdapter);
//               // ActivityQuestions.mAdapter.notifyDataSetChanged();
//                ActivityQuestions.mAdapter.notifyItemChanged(position);
//            }
//        });

        try {
            Log.e("asasasasasas34534",""+feedReaderDbHelper.getColor(mDataset.get(position).get_id()));
            if(feedReaderDbHelper.getColor(mDataset.get(position).get_id()).equals("1"))
            {
                holder.label.setTextColor(Color.parseColor("#2A6BAC"));
                holder.imgTick.setBackgroundResource(R.drawable.tick_grey);
            }
        }
        catch (Exception e)
        {
//            Toast.makeText(, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
           Log.e("asasasasasas34534",""+e.getMessage());
        }
    }


    public void addItem(CardQuestionsData dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}