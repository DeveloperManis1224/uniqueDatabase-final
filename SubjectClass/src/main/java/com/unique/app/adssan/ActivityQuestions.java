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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.morsebyte.shailesh.twostagerating.FeedbackReceivedListener;
import com.morsebyte.shailesh.twostagerating.TwoStageRate;

import angtrim.com.fivestarslibrary.NegativeReviewListener;
import angtrim.com.fivestarslibrary.ReviewListener;
import com.unique.app.adssan.R;

public class ActivityQuestions extends AppCompatActivity implements NegativeReviewListener, ReviewListener {
    public static RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    public static RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    String selectedvalue;
    ImageView imageView3, hum, filter;
    String part;
    private DrawerLayout mDrawerLayout;
    Spinner spinner;
    android.widget.TextView pathLocation;

    @Override
    public void onBackPressed() {
        Intent in = new Intent(ActivityQuestions.this,SelectType.class);
        startActivity(in);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        imageView3 = findViewById(R.id.imageView3);
        hum = findViewById(R.id.hum);
        filter = findViewById(R.id.filter);
        spinner = findViewById(R.id.spinner);

        pathLocation = findViewById(R.id.txt_path);


        Log.v("asdsadas", ""+getIntent().getStringExtra("chapter"));
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        mDrawerLayout = findViewById(R.id.drawer_layout);


        final String subject = settings.getString("subject", "");
        final String part = settings.getString("part", "");
        final String chapter = settings.getString("chapter", "");
        final String year = settings.getString("year", "");
        final String type = settings.getString("type", "");
       // Log.d("asdasdasdasd", subject + part + chapter);

        pathLocation.setText(chapter+" - "+type);
        pathLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityQuestions.this);
                alert.setTitle("Current Page");
                alert.setMessage(""+subject +" - "+ part +" - "+ chapter+" - "+type);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                       dialog.dismiss();

                    }
                });
                alert.show();
            }
        });
//        ArrayList<String> quelist = feedReaderDbHelper.getQue(subject, part, chapter, year);
//        Set<String> myset = new LinkedHashSet<>(quelist);
//        quelist.clear();
//        quelist.addAll(myset);
//
//        String[] value = new String[quelist.size()];
//
//        for (int i = 0; i < quelist.size(); i++) {
//
//            value[i] = quelist.get(i);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, value);
//        spinner.setAdapter(adapter);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestions(subject, part, chapter, year,type));
        mRecyclerView.setAdapter(mAdapter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(ActivityQuestions.this, imageView3);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Chapter wise"))
                        {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingCRN(subject, part, chapter, year,type));
                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);
                        } else if (item.getTitle().equals("Repeated wise")) {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingRNO(subject, part, chapter, year,type));

                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);

                        } else if (item.getTitle().equals("Read/Unread wise")) {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingRead(subject, part, chapter, year,type));

                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);

                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }

        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ActivityQuestions.this, imageView3);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        if (item.getTitle().equals("Chapter wise"))
                        {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingCRN(subject, part, chapter, year,type));
                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);
                        } else if (item.getTitle().equals("Repeated wise")) {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingRNO(subject, part, chapter, year,type));
                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);
                        } else if (item.getTitle().equals("Read/Unread wise")) {
                            mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingRead(subject, part, chapter, year,type));
                            mRecyclerView.setAdapter(null);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
        hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                String selected = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_LONG).show();


                mAdapter = new ViewAdapterQuestions(feedReaderDbHelper.getQuestionsusingReadSpinner(subject, part, chapter, year,selected));

                mRecyclerView.setAdapter(null);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)

                    {
                        switch (menuItem.getItemId()) {


                            case R.id.feedback:
                                Intent intent1d = new Intent(ActivityQuestions.this, Rating.class);
                                startActivity(intent1d);
                                break;
                            case R.id.contactus:
                                Intent intent = new Intent(ActivityQuestions.this, ContactusActivity.class);
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
    public void initTwoStage (){
        TwoStageRate twoStageRate = TwoStageRate.with(this);
        //Setting conditions
        twoStageRate.setInstallDays(5).setEventsTimes(5).setLaunchTimes(5);
        twoStageRate.resetOnDismiss(true).resetOnFeedBackDeclined(true).resetOnRatingDeclined(false);
        twoStageRate.showIfMeetsConditions();

        //Setting feedback listener
        twoStageRate.setFeedbackReceivedListener(new FeedbackReceivedListener() {
            @Override
            public void onFeedbackReceived(String feedback) {
                Toast.makeText(ActivityQuestions.this, feedback, Toast.LENGTH_SHORT).show();
            }
        });

        //Setting texts for initial prompt
        twoStageRate.with(this).setRatePromptTitle("INITIAL_TITLE").
                setRatePromptLaterText("LATER_TEXT").setRatePromptNeverText("NEVER_TEXT").setRatePromptDismissible(false);

        //Setting texts for confirmation dialog
        twoStageRate.with(this).setConfirmRateDialogTitle("CONFIRMATION_TITLE").
                setConfirmRateDialogDescription("CONFIRMATION_DESCRITPION").
                setConfirmRateDialogPositiveText("POSITIVE_BUTTON_TEXT").
                setConfirmRateDialogNegativeText("NEGATIVE_BUTTON_TEXT").
                setConfirmRateDialogDismissible(true);

        //Setting texts for feedback title
        twoStageRate.with(this).setFeedbackDialogTitle("FEEDBACK_TITLE").
                setFeedbackDialogDescription("FEEDBACK_DIALOG_DESCRIPTION").
                setFeedbackDialogPositiveText("POSITIVE_BUTTON_TEXT").
                setFeedbackDialogNegativeText("NEGATIVE_BUTTON_TEXT").
                setFeedbackDialogDismissible(false);
    }
    @Override
    public void onNegativeReview(int i) {

    }

    @Override
    public void onReview(int i) {

    }

}
