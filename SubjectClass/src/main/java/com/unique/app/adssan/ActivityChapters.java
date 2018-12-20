package com.unique.app.adssan;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

import com.unique.app.adssan.R;

public class ActivityChapters extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    String selectedvalue;
    String part;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        selectedvalue = getIntent().getStringExtra("subject");
        part = getIntent().getStringExtra("part");
        mDrawerLayout = findViewById(R.id.drawer_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ViewAdapterChapter(getDataSet(selectedvalue,part));
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
                                Intent intent1d = new Intent(ActivityChapters.this, Rating.class);
                                startActivity(intent1d);
                                break;
                            case R.id.contactus:
                                Intent intent = new Intent(ActivityChapters.this, ContactusActivity.class);
                                startActivity(intent);
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

                            case R.id.edit_board:
                                Intent inEdit = new Intent(ActivityChapters.this, EditorialBoard.class);
                                startActivity(inEdit);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){ // use android.R.id
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        int id = item.getItemId();
        if (id == R.id.cart_btn_bar) {
//            session.setPreferences(HomeActivity.this,Constants.LOGIN_STATUS,Constants.LOGOUT);
//            startActivity(new Intent(HomeActivity.this,HomeActivity.class));
//            Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();
            SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
            String year11 = settings.getString("year", "");

            String msg = "";
            if(year11.equalsIgnoreCase("1year"))
            {
                msg = "First Year MBBS";
            }
            else if(year11.equalsIgnoreCase("2year"))
            {
                msg = "Second Year MBBS";
            }
            else if(year11.equalsIgnoreCase("3year"))
            {
                msg = "Pre Final Year MBBS";
            }
            else if(year11.equalsIgnoreCase("4year"))
            {
                msg = "Final Year MBBS";
            }
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ActivityChapters.this);
            builder1.setTitle("Current Page");
            builder1.setMessage(""+msg +" - "+getIntent().getStringExtra("subject")+" - "+
                    getIntent().getStringExtra("part"));
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Close",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<CardChapterData> getDataSet(String subjectname, String part) {

        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        ArrayList<CardChapterData> aaaa = feedReaderDbHelper.getChapter(subjectname,part);


        return aaaa;
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(ActivityChapters.this,ActivitySubject.class);
        startActivity(in);
        finish();
    }
}