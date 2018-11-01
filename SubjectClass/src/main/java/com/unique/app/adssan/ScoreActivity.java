package com.unique.app.adssan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        String year11 = settings.getString("year", "");
int value= feedReaderDbHelper.getRead(year11);
int value1  = feedReaderDbHelper.getUnRead(year11);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(value, 0));
        yvalues.add(new Entry(value1, 1));




        PieDataSet dataSet = new PieDataSet(yvalues, "");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Read");
        xVals.add("Un Read");


        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieChart.setData(data);
        pieChart.setDescription("");

    }
}
