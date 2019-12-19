package com.example.lonavlarha;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class settings_page extends AppCompatActivity {

    TextView delete_acc, bug, dev_info, privacy;
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        delete_acc = findViewById(R.id.delete_acc);
        bug = findViewById(R.id.bug_report);
        dev_info = findViewById(R.id.developer_details);
        privacy = findViewById(R.id.privacy_policy);
        tool = findViewById(R.id.toolbar_settings);

        setSupportActionBar(tool);
        setTitle("Settings");

        delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete_acc_dailog delete = new delete_acc_dailog();
                delete.show(getSupportFragmentManager(),"AccountDeleteDailog");

            }
        });

        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bug_report_dailog bug_report = new bug_report_dailog();
                bug_report.show(getSupportFragmentManager(),"BugReportDailog");

            }
        });




    }
}
