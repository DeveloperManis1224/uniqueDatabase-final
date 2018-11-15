package com.unique.app.adssan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import angtrim.com.fivestarslibrary.NegativeReviewListener;
import angtrim.com.fivestarslibrary.ReviewListener;
import com.unique.app.adssan.R;

public class ActivitySubject extends AppCompatActivity implements NegativeReviewListener, ReviewListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private DrawerLayout mDrawerLayout;
    Thread td;

    PieChart pieChart;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chapter);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setVisibility(View.GONE);
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        String year11 = settings.getString("year", "");

        int value= feedReaderDbHelper.getRead(year11);
        int value1  = feedReaderDbHelper.getUnRead(year11);

        setChart();

//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                setChart();
//                ((TextView)findViewById(R.id.progress_txt)).setVisibility(View.GONE);
//                ((ProgressBar)findViewById(R.id.progress)).setVisibility(View.GONE);
//            }
//        };

//        td= new Thread(new Runnable() {
//            public void run(){
//               if(ActivitySplash.dataRecieved)
//               {
//                   handler.sendEmptyMessage(0);
//               }
//
//            }
//        });
//        td.start();

        Log.e("asasasasasasasasasasa",""+value+"   -   " +value1);

//        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
//        pieChart.setVisibility(View.VISIBLE);
//        pieChart.setDescription("");
//        ArrayList<Entry> yvalues = new ArrayList<Entry>();
//        yvalues.add(new Entry(value, 0));
//        yvalues.add(new Entry(value1, 1));
//
//
//        PieDataSet dataSet = new PieDataSet(yvalues, " ");
//        ArrayList<String> xVals = new ArrayList<String>();
//
//        xVals.add("Read");
//        xVals.add("Un Read");
//
//
//        PieData data = new PieData(xVals, dataSet);
//
//        data.setValueFormatter(new PercentFormatter());
//        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
//        pieChart.setData(data);
       // Toast.makeText(getApplicationContext(), "subject", Toast.LENGTH_LONG).show();
        mDrawerLayout = findViewById(R.id.drawer_layout);

        String year = settings.getString("year", "");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewAdapterSubject(getDataSet(year));
        mRecyclerView.setAdapter(mAdapter);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.as_above);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)

                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.feedback:
                                Intent intent1d = new Intent(ActivitySubject.this, Rating.class);
                                startActivity(intent1d);
                                break;
                            case R.id.contactus:
                                Intent intent = new Intent(ActivitySubject.this, ContactusActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.edit_board:
                                Intent inEdit = new Intent(ActivitySubject.this, EditorialBoard.class);
                                startActivity(inEdit);
                                break;

                            case R.id.score:
                                Intent intent11 = new Intent(getApplicationContext(),ScoreView.class);
                                startActivity(intent11);
                                break;
                            case R.id.share:

                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "App link: http://play.google.com/store/apps/details?id=com.unique.app.adssan");
                                sendIntent.setType("text/plain");
                                startActivity(sendIntent);
                                break;
                            case R.id.about_app:

                                Intent intent134 = new Intent(getApplicationContext(),AboutApp.class);
                                startActivity(intent134);
                                break;
                            case R.id.about:

                                Intent intent1 = new Intent(getApplicationContext(),AboutAuthor.class);
                                startActivity(intent1);
                                break;
                            case R.id.update:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.unique.app.adssan")));
                                break;
                        }
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


    }


    private void setChart()
    {
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        String year11 = settings.getString("year", "");

        int value= feedReaderDbHelper.getRead(year11);
        int value1  = feedReaderDbHelper.getUnRead(year11);

        Log.e("asasasasasasasasasasa",""+value+"   -   " +value1);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setVisibility(View.VISIBLE);
        pieChart.setDescription("");
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(value, 0));
        yvalues.add(new Entry(value1, 1));


        PieDataSet dataSet = new PieDataSet(yvalues, " ");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Read");
        xVals.add("Un Read");


        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.setData(data);
    }

    public ArrayList<CardSubjectData> getDataSet(String year) {


        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        ArrayList<CardSubjectData> aaaa = feedReaderDbHelper.getSubject(year);


        return aaaa;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){ // use android.R.id
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNegativeReview(int i) {

    }

    @Override
    public void onReview(int i) {

    }


    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
