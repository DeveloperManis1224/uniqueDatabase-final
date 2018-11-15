package com.unique.app.adssan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import com.unique.app.adssan.R;

public class ActivitySplash extends AppCompatActivity {
    TextView textView;
    ProgressDialog progressDialog;
    Handler handler;

    static boolean dataRecieved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(isInternetAvailable())
        {
            getAllData();
        }
        else
        {
            noInternetAlert();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching...");
        progressDialog.setCancelable(false);
        final ImageView splash = (ImageView) findViewById(R.id.imageView);
        textView = findViewById(R.id.textt);
       // FeedReaderDbHelper frdh = new FeedReaderDbHelper(ActivitySplash.this);
       // frdh.DEleteAll();
        String styledText = "MBBS <font color='red'>Uni</font>versity <font color='red'>Que</font>stions Collections";
        textView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);
        splash.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
                String session = settings.getString("status", "");
                try
                {
                    if(session.equals("1"))
                    {
                        Intent intent = new Intent(ActivitySplash.this, ActivitySubject.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(ActivitySplash.this, ActivitySignUp2.class);
                        startActivity(intent);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, 5000);



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
               // progressDialog.dismiss();
                dataRecieved = true;
//                        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
//                        String session = settings.getString("status", "");
//                        try {
//                            if (session.equals("1")) {
//                                //progressDialog.dismiss();
//                                Intent intent = new Intent(ActivitySplash.this, ActivitySubject.class);
//                                startActivity(intent);
//                            } else {
//                               // progressDialog.dismiss();
//                                Intent intent = new Intent(ActivitySplash.this, ActivitySignUp2.class);
//                                startActivity(intent);
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

            }
        };
    }

    private boolean isInternetAvailable()
    {
        boolean val= true;
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                val = true;

            }else {
                //No internet
              val = false;
            }
        } else {
            //No internet
            val = false;

        }
        return val;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }

    private void noInternetAlert()
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent in = new Intent (ActivitySplash.this,ActivitySplash.class);
                startActivity(in);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getAllData() {
        // Instantiate the RequestQueue.


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://medapp.roadtours.in/api/product/read.php";
        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.v("thisisresponse", response);
                        Parse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("sadasdasd", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void Parse(final String response) {
       // progressDialog.show();
        Thread td =new Thread(){
            @Override
            public void run() {
                try {
                final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(ActivitySplash.this);
                try {
                    Log.v("trace_response", response.toString());
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray subject = jsonObject.getJSONArray("subject");
                    JSONArray chapter = jsonObject.getJSONArray("chapter");
                    JSONArray questions = jsonObject.getJSONArray("questions");
                    JSONArray questions1 = jsonObject.getJSONArray("questions1");
                    DataSubject dataSubject;
                    DataChapter chapterdata;
                    DataQuestions dataQuestions2;
                    DataQuestions1 dataQuestions11;

                    for (int i = 0; i < subject.length(); i++) {

                        JSONObject jsonObject1 = subject.getJSONObject(i);
                        Log.v("valuevalue", jsonObject1.getString("cid"));
                        Log.v("valuevalue", jsonObject1.getString("category_name"));
                        Log.v("valuevalue", jsonObject1.getString("year"));


                        String cid = jsonObject1.getString("cid");
                        String category_name = jsonObject1.getString("category_name");
                        String year = jsonObject1.getString("year");
                        dataSubject = new DataSubject(String.valueOf(i), cid, category_name, year);
                        feedReaderDbHelper.addSubject(dataSubject);

                    }

                    for (int i = 0; i < chapter.length(); i++) {

                        JSONObject jsonObject1 = chapter.getJSONObject(i);
                        Log.v("valuevalue", jsonObject1.getString("id"));
                        Log.v("valuevalue", jsonObject1.getString("chapter_name"));
                        Log.v("valuevalue", jsonObject1.getString("subject"));
                        Log.v("valuevalue", jsonObject1.getString("year"));

                        String columnid = jsonObject1.getString("id");
                        String chapter_name = jsonObject1.getString("chapter_name");
                        String chapter_subject = jsonObject1.getString("subject");
                        String year = jsonObject1.getString("year");

                        chapterdata = new DataChapter(String.valueOf(i), columnid, chapter_name, chapter_subject, year);

                        feedReaderDbHelper.addChapter(chapterdata);


                    }
                    for (int i = 0; i < questions.length(); i++) {

                        JSONObject jsonObject1 = questions.getJSONObject(i);
                        Log.v("valuevalue", jsonObject1.getString("tid"));
                        Log.v("valuevalue", jsonObject1.getString("years"));
                        Log.v("valuevalue", jsonObject1.getString("subject"));
                        Log.v("valuevalue", jsonObject1.getString("part"));
                        Log.v("valuevalue", jsonObject1.getString("chapter_name"));
                        Log.v("valuevalue", jsonObject1.getString("que"));

                        String tid = jsonObject1.getString("tid");
                        String years = jsonObject1.getString("years");
                        String subject1 = jsonObject1.getString("subject");
                        String part = jsonObject1.getString("part");
                        String chapter_name = jsonObject1.getString("chapter_name");
                        String que = jsonObject1.getString("que");
                        //  DataQuestions(String id, String tid, String years, String subject, String part, String chapter_name, String que) {

                        dataQuestions2 = new DataQuestions(String.valueOf(i), tid, years, subject1, part, chapter_name, que);
                        feedReaderDbHelper.addQuestions(dataQuestions2);
                    }
                    for (int i = 0; i < questions1.length(); i++) {

                        JSONObject jsonObject1 = questions1.getJSONObject(i);
                        Log.v("valuevalue", jsonObject1.getString("id"));
                        Log.v("valuevalue", jsonObject1.getString("tid"));
                        Log.v("valuevalue", jsonObject1.getString("years"));
                        Log.v("valuevalue", jsonObject1.getString("subject"));
                        Log.v("valuevalue", jsonObject1.getString("part"));
                        Log.v("valuevalue", jsonObject1.getString("chapter"));
                        Log.v("valuevalue", jsonObject1.getString("que"));
                        Log.v("valuevalue", jsonObject1.getString("ques"));
                        Log.v("valuevalue", jsonObject1.getString("cno"));
                        Log.v("valuevalue", jsonObject1.getString("rno"));
// DataQuestions1(String idvalue, String id, String tid, String years, String subject, String part, String chapter, String que, String ques, String cno, String rno) {

                        String id = jsonObject1.getString("id");
                        String tid = jsonObject1.getString("tid");
                        String years = jsonObject1.getString("years");
                        String subject1 = jsonObject1.getString("subject");
                        String part = jsonObject1.getString("part");
                        String chapter1 = jsonObject1.getString("chapter");
                        String que = jsonObject1.getString("que");
                        String ques = jsonObject1.getString("ques");
                        String cno = jsonObject1.getString("cno");
                        String rno = jsonObject1.getString("rno");


                        dataQuestions11 = new DataQuestions1(String.valueOf(i), id, tid, years, subject1, part, chapter1, que, ques, cno, rno);

                        feedReaderDbHelper.addQuestions1(dataQuestions11);
                    }
                    // Log.v("trace_database", feedReaderDbHelper.getSubject().toString());

                    //Log.v("sadasdasdsa",String.valueOf(subject.length()));
                } catch (Exception e) {
                    Log.v("sadasdasdsd", e.toString());
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        };
        td.start();

    }
}
