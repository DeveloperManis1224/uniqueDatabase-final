package com.unique.app.adssan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.github.mikephil.charting.utils.ViewPortHandler;
import com.unique.app.adssan.R;

public class ScoreView extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        Map<String,String> map = new HashMap<>();
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        String year11 = settings.getString("year", "");
        ArrayList<String> arrayList = feedReaderDbHelper.getSubjectAlone(year11);
            ArrayList<CardScoreData> cardScoreData = new ArrayList<>();
        for(int i=0;i<arrayList.size();i++)
        {
            Log.v("sdasdsad",arrayList.get(i));
            //map.put(arrayList.get(i),feedReaderDbHelper.getReadall(arrayList.get(i))+"@@"+feedReaderDbHelper.getUnReadAll(arrayList.get(i)));
            cardScoreData.add(new CardScoreData(String.valueOf(feedReaderDbHelper.getReadall(arrayList.get(i))),String.valueOf(feedReaderDbHelper.getUnReadAll(arrayList.get(i))),arrayList.get(i)));
        }


        Log.v("sdasdsdasd",map.toString());
        mRecyclerView = (RecyclerView) findViewById(R.id.scorerecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewAdapterScore(cardScoreData);
       mRecyclerView.setAdapter(mAdapter);



        int value= feedReaderDbHelper.getRead(year11);
        int value1  = feedReaderDbHelper.getUnRead(year11);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setDescription("");
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(value, 0));
        yvalues.add(new Entry(value1, 1));




        PieDataSet dataSet = new PieDataSet(yvalues, "");
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Read");
        xVals.add("Un Read");
        PieData data = new PieData(xVals, dataSet);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ""+((int) value);
            }
        };
        data.setValueFormatter(formatter);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.setData(data);
    }


//
//    public ArrayList<CardSubjectData> getDataSet(String year) {
//
//
//        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
//        ArrayList<CardScoreData> aaaa = feedReaderDbHelper.getSubject(year);
//
//
//        return aaaa;
//    }
}
