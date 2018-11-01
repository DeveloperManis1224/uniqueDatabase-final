package com.unique.app.adssan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import com.unique.app.adssan.R;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    @Override
    protected void onDestroy() {
        super.onDestroy();


//        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
//        feedReaderDbHelper.DEleteAll();

    }


    public void Parse(final String response) {
        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);


        try {


            Log.v("trace_response", response.toString());
            JSONObject jsonObject = new JSONObject(response);

            JSONArray subject = jsonObject.getJSONArray("subject");
            JSONArray chapter = jsonObject.getJSONArray("chapter");
            JSONArray questions = jsonObject.getJSONArray("questions");
            JSONArray questions1 = jsonObject.getJSONArray("questions1");
            DataSubject dataSubject;
            DataChapter chapterdata;

            for (int i = 0; i < subject.length(); i++)
            {

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
                Log.v("valuevalue", jsonObject1.getString("chapter_name"));
                Log.v("valuevalue", jsonObject1.getString("que"));
                Log.v("valuevalue", jsonObject1.getString("part"));



            }
            for (int i = 0; i < questions1.length(); i++) {

                JSONObject jsonObject1 = questions1.getJSONObject(i);
                Log.v("valuevalue", jsonObject1.getString("id"));
                Log.v("valuevalue", jsonObject1.getString("tid"));
                Log.v("valuevalue", jsonObject1.getString("years"));
                Log.v("valuevalue", jsonObject1.getString("subject"));
                Log.v("valuevalue", jsonObject1.getString("part"));
                Log.v("valuevalue", jsonObject1.getString("chapter  "));
                Log.v("valuevalue", jsonObject1.getString("que"));
                Log.v("valuevalue", jsonObject1.getString("ques"));
                Log.v("valuevalue", jsonObject1.getString("cno"));
                Log.v("valuevalue", jsonObject1.getString("rno"));

            }
           // Log.v("trace_database", feedReaderDbHelper.getSubject().toString());
            //Log.v("sadasdasdsa",String.valueOf(subject.length()));
        } catch (Exception e) {
            Log.v("sadasdasdsd", e.toString());

        }
    }


}


