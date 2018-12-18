package com.unique.app.adssan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.io.IOException;

import com.unique.app.adssan.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Email With Attachments example
 */

public class ActivitySignUp extends Activity {
    ProgressDialog progressDialog;
    OkHttpClient okHttpClient = new OkHttpClient();
    String subject;
    String message;
    RadioButton student, teacher;
    Spinner spinner;
    String[] studentarray = new String[]{"YEAR 1", "YEAR 2", "YEAR 3", "YEAR 4"};
    String[] teacherarray = new String[]{"YEAR 1", "YEAR 2", "YEAR 3", "YEAR 4"};
    RelativeLayout button;
    EditText mobileno;

    String studentName,collegeName,eMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        progressDialog = new ProgressDialog(this);
        mobileno = findViewById(R.id.editText);
        button = findViewById(R.id.button2);

        studentName = getIntent().getStringExtra("name");
        collegeName = getIntent().getStringExtra("college");
        eMail = getIntent().getStringExtra("email");

        student = findViewById(R.id.radio_student);
        teacher = findViewById(R.id.radio_teacher);
        spinner = findViewById(R.id.spinner);
        spinner.setPrompt("test");
        final ArrayAdapter<String> studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentarray);
        final ArrayAdapter<String> teacherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teacherarray);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (student.isChecked()) {

                    teacher.setChecked(false);
                    spinner.setAdapter(null);
                    spinner.setAdapter(studentAdapter);

                } else {

                    teacher.setChecked(true);
                    spinner.setAdapter(null);
                    spinner.setAdapter(teacherAdapter);

                }
            }
        });


        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teacher.isChecked()) {

                    student.setChecked(false);
                    spinner.setAdapter(null);
                    spinner.setAdapter(teacherAdapter);
                } else {

                    student.setChecked(true);
                    spinner.setAdapter(null);
                    spinner.setAdapter(studentAdapter);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Intent intent = new Intent(ActivitySignUp.this, ActivitySubject.class);
//                intent.putExtra("mobile", mobileno.getText().toString());
//                startActivity(intent);

                progressDialog.setMessage("Please wait..");
                progressDialog.setCancelable(false);
                progressDialog.show();
                String role = "";

                String year = "";
                if (student.isChecked()) {

                    role = "student";
                } else {
                    role = "teacher";

                }
                if (spinner.getSelectedItem().toString() == "YEAR 1") {
                    year = "1year";
                } else if (spinner.getSelectedItem().toString() == "YEAR 2") {
                    year = "2year";
                }
                if (spinner.getSelectedItem().toString() == "YEAR 3") {
                    year = "3year";
                }
                if (spinner.getSelectedItem().toString() == "YEAR 4") {
                    year = "4year";
                }
                if (spinner.getSelectedItem().toString() == "YEAR 5") {
                    year = "5year";
                }

                SharedPreferences pref = getApplicationContext().getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("year", year);
                editor.apply();
              //  Toast.makeText(getApplicationContext(),year,Toast.LENGTH_LONG).show();

                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("mobile", mobileno.getText().toString())
                        .addFormDataPart("role", role)
                        .addFormDataPart("year", year)
                        .addFormDataPart("name", studentName)
                        .addFormDataPart("college", collegeName)
                        .addFormDataPart("mail", eMail)
                        .build();
                Request request = new Request.Builder()
                        .url("http://medapp.roadtours.in/api/otp/otp.php")
                        .post(requestBody)
                        .build();



//                java.lang.RuntimeException: Unable to start activity ComponentInfo{com.unique.app.adssan/com.unique.app.adssan.AboutAuthor}: android.view.InflateException: Binary XML file line #143: Could not inflate Behavior subclass database.unique.com.database.AvatarImageBehavior
//                at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2744)
//                at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2805)
                okHttpClient.newCall(request).enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }


                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {


                        if (!response.isSuccessful()) {

                        } else {

if(progressDialog.isShowing())
{

    progressDialog.dismiss();

}
                            String bodyy = response.body().string();
                            int responsecode = response.code();

                            String Headers = response.headers().toString();

                            Log.v("sdfsdfdsf", bodyy);

                            Log.v("sdfsdfdsf", String.valueOf(responsecode));
                            Log.v("sdfsdfdsf", Headers);
                            if (bodyy.contains("otp_sent") || bodyy.contains("otp_update")) {
                                Intent intent = new Intent(ActivitySignUp.this, ActivityOTP.class);
                                intent.putExtra("mobile", mobileno.getText().toString());
                                startActivity(intent);

                            }

                        }
                    }
                });


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


//    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
//    feedReaderDbHelper.resetDatabase();
    }
}
