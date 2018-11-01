package com.unique.app.adssan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.unique.app.adssan.Adapter.TypeModel;
import com.unique.app.adssan.Adapter.ViewAdapterType;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class SelectType extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        mRecyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.my_recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new android.support.v7.widget.LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter =  new ViewAdapterType(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<TypeModel> getDataSet() {
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        final String subject = settings.getString("subject", "");
        final String part = settings.getString("part", "");
        final String chapter = settings.getString("chapter", "");
        final String year = settings.getString("year", "");
        Log.d("asdasdasdasd", subject + part + chapter);
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(SelectType.this);
        ArrayList<TypeModel> quelist = feedReaderDbHelper.getTypeQuest(subject, part, chapter, year);

        return quelist;
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(SelectType.this,ActivitySubject.class);
        startActivity(in);
        finish();
    }
}
