package com.example.lonavlarha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


public class first_screen_wsi extends AppCompatActivity {

    Button guest_sign;
    Button about_us;
    TextView dev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_wsi);


        guest_sign = findViewById(R.id.guest_wsi);
        about_us = findViewById(R.id.about);

        guest_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), guest_signup.class));

            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), About_Us_Page.class));
            }
        });

        dev = findViewById(R.id.devDetails);

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent devDet = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(devDet);
            }
        });

    }

}
