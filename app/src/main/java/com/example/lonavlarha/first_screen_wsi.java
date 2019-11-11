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
    TextView rho_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_wsi);

        guest_login =  findViewById(R.id.guest);
        rho_login = findViewById(R.id.rho);


        ArrayList<MyModel> upperdata = fill_with_data();

        bigrecycler = findViewById(R.id.up);
        MyAdapter adapter = new MyAdapter(upperdata);
        bigrecycler.setAdapter(adapter);
        bigrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        guest_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),guest_login.class));
            }
        });

        rho_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),rho_login.class));
            }
        });
    }

    public ArrayList<MyModel> fill_with_data() {


        ArrayList<MyModel> upperdata = new ArrayList<>();

        upperdata.add(new MyModel(R.drawable.image1));
        upperdata.add(new MyModel(R.drawable.image2));
        upperdata.add(new MyModel(R.drawable.image3));

        return upperdata;
    }
}
