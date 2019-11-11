package com.example.lonavlarha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView bigrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<MyModel> upperdata = fill_with_data();

        bigrecycler = findViewById(R.id.up);
        MyAdapter adapter = new MyAdapter(upperdata);
        bigrecycler.setAdapter(adapter);
        bigrecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
        public ArrayList<MyModel> fill_with_data(){


            ArrayList<MyModel> upperdata = new ArrayList<>();

            upperdata.add(new MyModel(R.drawable.image1));
            upperdata.add(new MyModel(R.drawable.image2));
            upperdata.add(new MyModel(R.drawable.image3));


            return upperdata;


        }


    }
