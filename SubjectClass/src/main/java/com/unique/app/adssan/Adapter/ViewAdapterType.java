package com.unique.app.adssan.Adapter;


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

import com.unique.app.adssan.ActivityQuestions;
import com.unique.app.adssan.ViewAdapterChapter;

import java.util.ArrayList;

import com.unique.app.adssan.R;
//
//public class ViewAdapterPart extends RecyclerView.Adapter<ViewAdapterPart.BankListViewHolder> {
//
//    ArrayList<PartModel> bankListModels;
//
//    public ViewAdapterPart(ArrayList<PartModel> bankListModels) {
//        this.bankListModels=bankListModels;
//
//    }
//
//
//    @Override
//    public BankListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View bankListLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_view_row, null);
//        BankListViewHolder bankListViewHolder = new BankListViewHolder(bankListLayout);
//        return bankListViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(BankListViewHolder holder, int position) {
//        holder.partName.setText(bankListModels.get(position).getPartName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return bankListModels.size();
//    }
//
//    public class BankListViewHolder extends RecyclerView.ViewHolder {
//        TextView partName;
//        LinearLayout relativeLayout;
//
//        public BankListViewHolder(View itemView) {
//            super(itemView);
//            partName = (TextView) itemView.findViewById(R.id.textView22);
//            relativeLayout = itemView.findViewById(R.id.button2);
//
//            relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), ""+partName.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}


public class ViewAdapterType extends RecyclerView
        .Adapter<ViewAdapterType.DataObjectHolder> {
    private static String LOG_TAG = "forgetchapter1212";
    private ArrayList<TypeModel> mDataset;
    private static ViewAdapterChapter.MyClickListener myClickListener;

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
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("sadasdasdas", label.getText().toString());
//                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
//                    feedReaderDbHelper.getChapter(label.getText().toString());

                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("type", label.getText().toString().trim());
                    // Saving string
                    editor.apply(); // commit changes
                    Intent intent = new Intent(context, ActivityQuestions.class);
                    intent.putExtra("type",label.getText().toString());
                    context.startActivity(intent);
                }
            });
            Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public ViewAdapterType(ArrayList<TypeModel> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewAdapterType.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new ViewAdapterType.DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getTypeQuestion());

    }

    public void addItem(TypeModel dataObj, int index) {
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