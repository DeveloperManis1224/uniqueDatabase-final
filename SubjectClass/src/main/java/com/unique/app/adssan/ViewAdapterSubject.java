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

public class ViewAdapterSubject extends RecyclerView
        .Adapter<ViewAdapterSubject
        .DataObjectHolder> {
    private static String LOG_TAG = "ViewAdapterSubject";
    private ArrayList<CardSubjectData> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        private Context context;
        TextView label;
        TextView dateTime;
        RelativeLayout relativeLayout;
        TextView oneletter;

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
                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();


                    editor.putString("subject", label.getText().toString()); // Saving string
                    editor.putString("part", ""); // Saving string
                    editor.apply(); //

                    Intent intent = new Intent(context, SelectPart.class);
                    intent.putExtra("subject", label.getText().toString());
                   // intent.putExtra("selected",label.getText().toString());
                    context.startActivity(intent);
//
//                    Log.v("sadasdasdas", label.getText().toString());
//                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
//                    ArrayList<String> arrayList = feedReaderDbHelper.getPart(label.getText().toString());
//                    Set<String> hashMap = new LinkedHashSet<>(arrayList);
//
//                    arrayList.clear();
//                    arrayList.addAll(hashMap);
//                    // custom dialog
//                    final Dialog dialog = new Dialog(context);
//                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    dialog.setContentView(R.layout.radiobutton_dialog);
//
//                    RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);
//
//
////                    if (arrayList.size() == 0) {
////                       // Toast.makeText(context, "No Values", Toast.LENGTH_SHORT).show();
////                        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
////                        SharedPreferences.Editor editor = pref.edit();
////
////
////                        editor.putString("subject", label.getText().toString()); // Saving string
////                        editor.putString("part", ""); // Saving string
////                        editor.apply(); // commit changes
////
////
////                        Intent intent = new Intent(context, ActivityChapters.class);
////                        intent.putExtra("subject", label.getText().toString());
////                        intent.putExtra("part", "");
////                        context.startActivity(intent);
////                        dialog.dismiss();
////                    }
////                    else
////                    {
//                    for (int i = 0; i < arrayList.size(); i++) {
//                        Log.v("sadasdsa", arrayList.get(i));
//
//
//                        LayoutInflater layoutInflater = LayoutInflater.from(context);
//                        View promptView = layoutInflater.inflate(R.layout.card_view_row, null);
//                        final TextView rb =promptView.findViewById(R.id.oneletter);
//
//
//                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                        rb.setLayoutParams(lp);
//                        rb.setText(arrayList.get(i));
//                        rg.addView(rb);
//                        rb.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Log.v("sdasdsd", rb.getText().toString());
//
//
//                                SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = pref.edit();
//
//
//                                editor.putString("subject", label.getText().toString()); // Saving string
//                                editor.putString("part", rb.getText().toString()); // Saving string
//                                editor.apply(); // commit changes
//
//
//                                Intent intent = new Intent(context, ActivityChapters.class);
//                                intent.putExtra("subject", label.getText().toString());
//                                intent.putExtra("part", rb.getText().toString());
//                                context.startActivity(intent);
//                                dialog.dismiss();
//                            }
//                        });
//
//
//                    }
//
//
//                    dialog.show();
//
//               // }
//

                }
            });
            Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public ViewAdapterSubject(ArrayList<CardSubjectData> myDataset) {
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
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getChaptername());
       // holder.oneletter.setText(mDataset.get(position).getChaptername().substring(0,1));

    }

    public void addItem(CardSubjectData dataObj, int index) {
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