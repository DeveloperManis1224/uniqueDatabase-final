package com.unique.app.adssan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unique.app.adssan.R;

public class ContactusActivity extends AppCompatActivity {
Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"submitted successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ContactusActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });
    }
}
