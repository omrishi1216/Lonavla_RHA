package com.example.lonavlarha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class first_screen_wsi extends AppCompatActivity {

    RecyclerView bigrecycler;
    TextView guest_login;

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_wsi);

        guest_login =  findViewById(R.id.guest);



        ArrayList<MyModel> upperdata = fill_with_data();

        bigrecycler = findViewById(R.id.up);
        MyAdapter adapter = new MyAdapter(upperdata);
        bigrecycler.setAdapter(adapter);
        bigrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        guest_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),guest_signup.class));

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
