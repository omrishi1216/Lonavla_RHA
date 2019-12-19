package com.example.lonavlarha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class about_us_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_2);

        Button know =findViewById(R.id.know_more);

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse("https://robinhoodarmy.com/"));

                startActivity(website);
            }
        });
    }
}
