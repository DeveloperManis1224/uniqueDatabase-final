package com.unique.app.adssan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import com.unique.app.adssan.R;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
         mobileno = getIntent().getStringExtra("mobile");

progressDialog = new ProgressDialog(this);
        editText7 = findViewById(R.id.text7);

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.d("Text",messageText);
                Toast.makeText(ActivityOTP.this,"Message: "+messageText,Toast.LENGTH_LONG).show();
            }
        });



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


progressDialog.setMessage("please wait");
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
                        Intent intent = new Intent(ActivityOTP.this, ActivitySubject.class);
                        startActivity(intent);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"OTP Success",Toast.LENGTH_LONG).show();
                                if(progressDialog.isShowing())
                                {
                                    progressDialog.dismiss();

                                    SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();

                                    editor.putString("status", "1");
                                    editor.apply();

                                }
                            }
                        });


                    }
                    else
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                        Toast.makeText(getApplicationContext(),"OTP wrong",Toast.LENGTH_LONG).show();
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
}
