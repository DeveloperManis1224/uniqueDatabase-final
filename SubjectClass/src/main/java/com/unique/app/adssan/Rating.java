package com.unique.app.adssan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.unique.app.adssan.R;

public class Rating extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rating);


    button = findViewById(R.id.button55);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(Rating.this,ActivitySubject.class);
//            startActivity(intent);
//            Toast.makeText(getApplicationContext(),"submitted rating sucessfully!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.unique.app.adssan")));

        }
    });
    }
}
