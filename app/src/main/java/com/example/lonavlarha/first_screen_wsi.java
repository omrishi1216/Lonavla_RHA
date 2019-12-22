package com.example.lonavlarha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class first_screen_wsi extends AppCompatActivity {

    RecyclerView bigrecycler;
    Button guest_signup;
    Button about_us;
    TextView dev;

    private static final int RC_SIGN_IN = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_wsi);

        guest_signup =  findViewById(R.id.guest);
        about_us = findViewById(R.id.about);



        ArrayList<MyModel> upperdata = fill_with_data();

        bigrecycler = findViewById(R.id.up);
        MyAdapter adapter = new MyAdapter(upperdata);
        bigrecycler.setAdapter(adapter);
        bigrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        guest_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),guest_signup.class));

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

    public ArrayList<MyModel> fill_with_data() {


        ArrayList<MyModel> upperdata = new ArrayList<>();

        upperdata.add(new MyModel(R.drawable.rha_img));
        upperdata.add(new MyModel(R.drawable.rha_img2));
        upperdata.add(new MyModel(R.drawable.rharmy_img));
        upperdata.add(new MyModel(R.drawable.rha_img4));
        upperdata.add(new MyModel(R.drawable.image1));
        upperdata.add(new MyModel(R.drawable.image3));

        return upperdata;
    }
}
