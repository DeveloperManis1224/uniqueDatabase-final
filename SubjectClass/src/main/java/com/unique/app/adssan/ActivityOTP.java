

package com.unique.app.adssan;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unique.app.adssan.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class ActivityOTP extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
    String mobileno;
    ProgressDialog progressDialog;
    OkHttpClient okHttpClient = new OkHttpClient();
    String year11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mobileno = getIntent().getStringExtra("mobile");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        editText7 = findViewById(R.id.text7);
        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
        year11 = settings.getString("year", "");


        //getAllData(year11);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
           getAllData(year11);
            // Code to run once
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }

//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if (!prefs.getBoolean("firstTime", false)) {
//            // <---- run your one time code here
//
//
//            // mark first time has ran.
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("firstTime", true);
//            editor.commit();
//        }
//        SmsReceiver.bindListener(new SmsListener() {
//            @Override
//            public void messageReceived(String messageText) {
//                Log.d("Text",messageText);
//                Toast.makeText(ActivityOTP.this,"Message: "+messageText,Toast.LENGTH_LONG).show();
//            }
//        });



        editText7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()==4)
                {
                    String otp = editText7.getText().toString();
                    progressDialog.setCancelable(false);
                    progressDialog.show();



                    MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
                    RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"mobile\"\r\n\r\n"+mobileno+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"otp\"\r\n\r\n"+otp+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
                    Request request = new Request.Builder()
                            .url("http://medapp.roadtours.in/api/otp/verify.php")
                            .post(body)
                            .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                            .addHeader("cache-control", "no-cache")
                            .addHeader("postman-token", "ae8bd27b-b71c-277a-5079-82c755ee1c3e")
                            .build();


                    okHttpClient.newCall(request).enqueue(new Callback()
                    {


                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final okhttp3.Response response) throws IOException {


                            if (!response.isSuccessful()) {

                            } else {

                                String bodyy = response.body().string();
                                int responsecode = response.code();

                                String Headers = response.headers().toString();

                                Log.v("rrrrrr", bodyy);

                                Log.v("rrrrrr", String.valueOf(responsecode));
                                Log.v("rrrrrr", Headers);
                                if (bodyy.contains("otp_success")) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.setMessage("Fetching...");
                                           // Toast.makeText(getApplicationContext(),"OTP Success",Toast.LENGTH_LONG).show();
                                           // getAllData(year11);
//                                            if(progressDialog.isShowing())
//                                            {
//                                                progressDialog.dismiss();
                                                SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putString("otp_status", "1");
                                                editor.apply();
                                            //}
                                        }
                                    });
                                }
                                else
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),"OTP wrong",Toast.LENGTH_LONG).show();
                                            if(progressDialog.isShowing()) {
                                                progressDialog.dismiss();
                                                new fetchdata().cancel(true);
                                                new FeedReaderDbHelper(ActivityOTP.this).DEleteAll();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });

                }
                else {



                }

            }
        });


//        Log.v("asdasdasd", mobileno);
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//
//                .addFormDataPart("mobile", mobileno)
//                .addFormDataPart("otp", "1year")
//                .build();
//
//        okhttp3.Request request = new okhttp3.Request.Builder()
//                .url("http://medapp.roadtours.in/api/otp/verify.php")
//                .post(requestBody)
//                .build();
//
//
//        okHttpClient.newCall(request).enqueue(new Callback()
//        {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//
//            @Override
//            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
//
//
//                if (!response.isSuccessful()) {
//
//                } else {
//
//
//                    String bodyy = response.body().string();
//                    int responsecode = response.code();
//
//                    String Headers = response.headers().toString();
//
//                    Log.v("sdfsdfdsf", bodyy);
//
//                    Log.v("sdfsdfdsf", String.valueOf(responsecode));
//                    Log.v("sdfsdfdsf", Headers);
//                    if (bodyy.contains("otp_sent") || bodyy.contains("otp_update")) {
////                        Intent intent = new Intent(ActivitySignUp.this, ActivityOTP.class);
////                        startActivity(intent);
//
//                    }
//
//                }
//            }
//        });


    }

    public void onSkipClick(View v) {


            progressDialog.show();
            new fetchdata().cancel(true);
            new FeedReaderDbHelper(ActivityOTP.this).DEleteAll();
        SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("otp_status", "1");
        editor.apply();
        getAllData(year11);
        //v.setVisibility(View.GONE);



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try
//                {
//                    progressDialog.dismiss();
//
//                    Intent intent = new Intent(ActivityOTP.this, ActivitySubject.class);
//                    startActivity(intent);
//                    //Toast.makeText(getApplicationContext(),"Verification Skipped...",Toast.LENGTH_LONG).show();
//                    SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("status", "1");
//                    editor.apply();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }, 3000);
        getAllData(year11);

    }


    private void getAllData(String year) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://medapp.roadtours.in/api/product/read.php?year="+year;
        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Log.v("thisisresponse", response);
                        // Parse(response);
                        new fetchdata().execute(response);
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


    private class fetchdata extends AsyncTask<String,String, String>
    {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            Parse(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }



            SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
            String otp_status = settings.getString("otp_status", "");
            if(otp_status.equalsIgnoreCase("1"))
            {
                SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("status", "1");
                editor.apply();
                Intent intent = new Intent(ActivityOTP.this, ActivitySubject.class);
                startActivity(intent);
            }
            else {

            }
            //Toast.makeText(ActivityOTP.this, "Question updated...", Toast.LENGTH_SHORT).show();

            // super.onPostExecute(s);
        }
    }

    public void Parse(final String response) {

        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(ActivityOTP.this);
        try {
            Log.v("trace_response", response.toString());
            JSONObject jsonObject = new JSONObject(response);
            JSONArray subject = jsonObject.getJSONArray("subject");
            JSONArray chapter = jsonObject.getJSONArray("chapter");
            JSONArray questions = jsonObject.getJSONArray("questions");
            final JSONArray questions1 = jsonObject.getJSONArray("questions1");
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

                final int finalI = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(progressDialog.isShowing()) {
                            progressDialog.setMessage("Questions Downloading.Please wait... ("+ finalI+" / "+questions1.length()+")");
                        }
                    }
                });
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




    }


}


//package com.unique.app.adssan;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.io.IOException;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.unique.app.adssan.R;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//
//
//public class ActivityOTP extends AppCompatActivity {
//
//    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
//    String mobileno;
//    ProgressDialog progressDialog;
//    OkHttpClient okHttpClient = new OkHttpClient();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_otp);
//         mobileno = getIntent().getStringExtra("mobile");
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait");
//        progressDialog.setCancelable(false);
//        editText7 = findViewById(R.id.text7);
//        SharedPreferences settings = getSharedPreferences("MyPref", MODE_PRIVATE);
//        String year11 = settings.getString("year", "");
//
//
//        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
//        if (isFirstRun)
//        {
//            getAllData(year11);
//            // Code to run once
//            SharedPreferences.Editor editor = wmbPreference.edit();
//            editor.putBoolean("FIRSTRUN", false);
//            editor.commit();
//        }
//
////
////        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
////        if (!prefs.getBoolean("firstTime", false)) {
////            // <---- run your one time code here
////
////
////            // mark first time has ran.
////            SharedPreferences.Editor editor = prefs.edit();
////            editor.putBoolean("firstTime", true);
////            editor.commit();
////        }
////        SmsReceiver.bindListener(new SmsListener() {
////            @Override
////            public void messageReceived(String messageText) {
////                Log.d("Text",messageText);
////                Toast.makeText(ActivityOTP.this,"Message: "+messageText,Toast.LENGTH_LONG).show();
////            }
////        });
//
//
//
//        editText7.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                if(editable.toString().length()==4)
//                {
//
//                String otp = editText7.getText().toString();
//                    progressDialog.setMessage("please wait");
//                    progressDialog.setCancelable(false);
//                    progressDialog.show();
//
//
//
//                    MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//                    RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"mobile\"\r\n\r\n"+mobileno+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"otp\"\r\n\r\n"+otp+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
//                    Request request = new Request.Builder()
//                            .url("http://medapp.roadtours.in/api/otp/verify.php")
//                            .post(body)
//                            .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
//                            .addHeader("cache-control", "no-cache")
//                            .addHeader("postman-token", "ae8bd27b-b71c-277a-5079-82c755ee1c3e")
//                            .build();
//
//
//        okHttpClient.newCall(request).enqueue(new Callback()
//        {
//
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
//
//
//                if (!response.isSuccessful()) {
//
//                } else {
//
//                    String bodyy = response.body().string();
//                    int responsecode = response.code();
//
//                    String Headers = response.headers().toString();
//
//                    Log.v("rrrrrr", bodyy);
//
//                    Log.v("rrrrrr", String.valueOf(responsecode));
//                    Log.v("rrrrrr", Headers);
//                    if (bodyy.contains("otp_success")) {
//                        Intent intent = new Intent(ActivityOTP.this, ActivitySubject.class);
//                        startActivity(intent);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(getApplicationContext(),"OTP Success",Toast.LENGTH_LONG).show();
//                                if(progressDialog.isShowing())
//                                {
//                                    progressDialog.dismiss();
//
//                                    SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = pref.edit();
//
//                                    editor.putString("status", "1");
//                                    editor.apply();
//
//                                }
//                            }
//                        });
//
//
//                    }
//                    else
//                    {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                        Toast.makeText(getApplicationContext(),"OTP wrong",Toast.LENGTH_LONG).show();
//                                if(progressDialog.isShowing()) {
//                                    progressDialog.dismiss();
//                                }
//
//                            }
//                        });
//                    }
//
//                }
//            }
//        });
//
//                }
//                else {
//
//
//
//                }
//
//            }
//        });
//
//
////        Log.v("asdasdasd", mobileno);
////        OkHttpClient okHttpClient = new OkHttpClient();
////
////
////        RequestBody requestBody = new MultipartBody.Builder()
////                .setType(MultipartBody.FORM)
////
////                .addFormDataPart("mobile", mobileno)
////                .addFormDataPart("otp", "1year")
////                .build();
////
////        okhttp3.Request request = new okhttp3.Request.Builder()
////                .url("http://medapp.roadtours.in/api/otp/verify.php")
////                .post(requestBody)
////                .build();
////
////
////        okHttpClient.newCall(request).enqueue(new Callback()
////        {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                e.printStackTrace();
////            }
////
////
////            @Override
////            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
////
////
////                if (!response.isSuccessful()) {
////
////                } else {
////
////
////                    String bodyy = response.body().string();
////                    int responsecode = response.code();
////
////                    String Headers = response.headers().toString();
////
////                    Log.v("sdfsdfdsf", bodyy);
////
////                    Log.v("sdfsdfdsf", String.valueOf(responsecode));
////                    Log.v("sdfsdfdsf", Headers);
////                    if (bodyy.contains("otp_sent") || bodyy.contains("otp_update")) {
//////                        Intent intent = new Intent(ActivitySignUp.this, ActivityOTP.class);
//////                        startActivity(intent);
////
////                    }
////
////                }
////            }
////        });
//
//
//    }
//
//    public void onSkipClick(View v) {
//        progressDialog.show();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try
//                {
//                    progressDialog.dismiss();
//
//                    Intent intent = new Intent(ActivityOTP.this, ActivitySubject.class);
//                    startActivity(intent);
//                    //Toast.makeText(getApplicationContext(),"Verification Skipped...",Toast.LENGTH_LONG).show();
//                    SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("status", "1");
//                    editor.apply();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }, 3000);
//
//    }
//
//
//    private void getAllData(String year) {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "http://medapp.roadtours.in/api/product/read.php?year="+year;
//        final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(final String response) {
//                        Log.v("thisisresponse", response);
//                        // Parse(response);
//                        new fetchdata().execute(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("sadasdasd", error.toString());
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
//
//
//    private class fetchdata extends AsyncTask<String,String, String>
//    {
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Parse(strings[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Toast.makeText(ActivityOTP.this, "Question updated...", Toast.LENGTH_SHORT).show();
//            super.onPostExecute(s);
//        }
//    }
//
//    public void Parse(final String response) {
//        // progressDialog.show();
//        Thread td =new Thread(){
//            @Override
//            public void run() {
//                try {
//                    final FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(ActivityOTP.this);
//                    try {
//                        Log.v("trace_response", response.toString());
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray subject = jsonObject.getJSONArray("subject");
//                        JSONArray chapter = jsonObject.getJSONArray("chapter");
//                        JSONArray questions = jsonObject.getJSONArray("questions");
//                        JSONArray questions1 = jsonObject.getJSONArray("questions1");
//                        DataSubject dataSubject;
//                        DataChapter chapterdata;
//                        DataQuestions dataQuestions2;
//                        DataQuestions1 dataQuestions11;
//
//                        for (int i = 0; i < subject.length(); i++) {
//
//                            JSONObject jsonObject1 = subject.getJSONObject(i);
//                            Log.v("valuevalue", jsonObject1.getString("cid"));
//                            Log.v("valuevalue", jsonObject1.getString("category_name"));
//                            Log.v("valuevalue", jsonObject1.getString("year"));
//
//
//                            String cid = jsonObject1.getString("cid");
//                            String category_name = jsonObject1.getString("category_name");
//                            String year = jsonObject1.getString("year");
//                            dataSubject = new DataSubject(String.valueOf(i), cid, category_name, year);
//                            feedReaderDbHelper.addSubject(dataSubject);
//
//                        }
//
//                        for (int i = 0; i < chapter.length(); i++) {
//
//                            JSONObject jsonObject1 = chapter.getJSONObject(i);
//                            Log.v("valuevalue", jsonObject1.getString("id"));
//                            Log.v("valuevalue", jsonObject1.getString("chapter_name"));
//                            Log.v("valuevalue", jsonObject1.getString("subject"));
//                            Log.v("valuevalue", jsonObject1.getString("year"));
//
//                            String columnid = jsonObject1.getString("id");
//                            String chapter_name = jsonObject1.getString("chapter_name");
//                            String chapter_subject = jsonObject1.getString("subject");
//                            String year = jsonObject1.getString("year");
//
//                            chapterdata = new DataChapter(String.valueOf(i), columnid, chapter_name, chapter_subject, year);
//
//                            feedReaderDbHelper.addChapter(chapterdata);
//
//
//                        }
//                        for (int i = 0; i < questions.length(); i++) {
//
//                            JSONObject jsonObject1 = questions.getJSONObject(i);
//                            Log.v("valuevalue", jsonObject1.getString("tid"));
//                            Log.v("valuevalue", jsonObject1.getString("years"));
//                            Log.v("valuevalue", jsonObject1.getString("subject"));
//                            Log.v("valuevalue", jsonObject1.getString("part"));
//                            Log.v("valuevalue", jsonObject1.getString("chapter_name"));
//                            Log.v("valuevalue", jsonObject1.getString("que"));
//
//                            String tid = jsonObject1.getString("tid");
//                            String years = jsonObject1.getString("years");
//                            String subject1 = jsonObject1.getString("subject");
//                            String part = jsonObject1.getString("part");
//                            String chapter_name = jsonObject1.getString("chapter_name");
//                            String que = jsonObject1.getString("que");
//                            //  DataQuestions(String id, String tid, String years, String subject, String part, String chapter_name, String que) {
//
//                            dataQuestions2 = new DataQuestions(String.valueOf(i), tid, years, subject1, part, chapter_name, que);
//                            feedReaderDbHelper.addQuestions(dataQuestions2);
//                        }
//                        for (int i = 0; i < questions1.length(); i++) {
//
//                            JSONObject jsonObject1 = questions1.getJSONObject(i);
//                            Log.v("valuevalue", jsonObject1.getString("id"));
//                            Log.v("valuevalue", jsonObject1.getString("tid"));
//                            Log.v("valuevalue", jsonObject1.getString("years"));
//                            Log.v("valuevalue", jsonObject1.getString("subject"));
//                            Log.v("valuevalue", jsonObject1.getString("part"));
//                            Log.v("valuevalue", jsonObject1.getString("chapter"));
//                            Log.v("valuevalue", jsonObject1.getString("que"));
//                            Log.v("valuevalue", jsonObject1.getString("ques"));
//                            Log.v("valuevalue", jsonObject1.getString("cno"));
//                            Log.v("valuevalue", jsonObject1.getString("rno"));
//// DataQuestions1(String idvalue, String id, String tid, String years, String subject, String part, String chapter, String que, String ques, String cno, String rno) {
//
//                            String id = jsonObject1.getString("id");
//                            String tid = jsonObject1.getString("tid");
//                            String years = jsonObject1.getString("years");
//                            String subject1 = jsonObject1.getString("subject");
//                            String part = jsonObject1.getString("part");
//                            String chapter1 = jsonObject1.getString("chapter");
//                            String que = jsonObject1.getString("que");
//                            String ques = jsonObject1.getString("ques");
//                            String cno = jsonObject1.getString("cno");
//                            String rno = jsonObject1.getString("rno");
//                            dataQuestions11 = new DataQuestions1(String.valueOf(i), id, tid, years, subject1, part, chapter1, que, ques, cno, rno);
//
//                            feedReaderDbHelper.addQuestions1(dataQuestions11);
//                        }
//                        // Log.v("trace_database", feedReaderDbHelper.getSubject().toString());
//
//                        //Log.v("sadasdasdsa",String.valueOf(subject.length()));
//                    } catch (Exception e) {
//                        Log.v("sadasdasdsd", e.toString());
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        td.start();
//
//    }
//
//
//}
