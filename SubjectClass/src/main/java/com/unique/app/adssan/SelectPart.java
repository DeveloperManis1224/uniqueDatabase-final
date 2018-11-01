package com.unique.app.adssan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.unique.app.adssan.Adapter.PartModel;
import com.unique.app.adssan.Adapter.ViewAdapterPart;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class SelectPart extends AppCompatActivity {
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;

    public static String subject ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_part);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        mRecyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.my_recycler_view1);
        mRecyclerView.setHasFixedSize(true);
        android.content.SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        mLayoutManager = new android.support.v7.widget.LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter =  new ViewAdapterPart(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<PartModel> getDataSet() {
        subject = getIntent().getStringExtra("subject");
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(SelectPart.this);
        ArrayList<PartModel> arrayList = feedReaderDbHelper.getPartNames(subject);
       // mAdapter.notifyDataSetChanged();
        return arrayList;
    }
}