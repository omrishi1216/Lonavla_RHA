package com.example.lonavlarha;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class developer_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);

        TextView email = findViewById(R.id.mail);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) developer_info.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText("teamcipherror@gmail.com");
                Toast.makeText(developer_info.this,"Email Copied to Clipboard",Toast.LENGTH_LONG ).show();
            }
        });

        findViewById(R.id.contact_dev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")));
            }
        });
    }
}
