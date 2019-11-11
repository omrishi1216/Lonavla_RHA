package com.example.lonavlarha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class first_screen_si extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_si);

        Intent i = getIntent();

        TextView q = findViewById(R.id.textView);
        q.setText(i.getStringExtra("User Name"));
    }
}
