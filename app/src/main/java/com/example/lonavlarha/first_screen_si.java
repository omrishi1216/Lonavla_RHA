package com.example.lonavlarha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class first_screen_si extends AppCompatActivity {
    FirebaseUser currentuser;
    DatabaseReference fbd;
    private static final int PERMISSION_REQUEST_CODE = 1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fbd = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        user = new User();
        Button i1 = findViewById(R.id.i);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_si);
        Intent i = getIntent();

        FloatingActionButton fab = findViewById(R.id.button1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String sms = smsText.getText().toString();
                //String phoneNum = phoneNumber.getText().toString();
                //if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                if (checkPermission()) {

//Get the default SmsManager//

                    SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//
                    smsManager.sendTextMessage("+918377972338", null, "Lonavla RHA Test Mssg", null, null);
                    smsManager.sendTextMessage("+919643959973", null, "Lonavla RHA Test Mssg", null, null);
                    Toast.makeText(first_screen_si.this, "Information Sent Successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(first_screen_si.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }


            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lonavla RHA");

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

    }

    /*private String username() {
        fbd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot usersnapshot : dataSnapshot.getChildren()){

                    user = usersnapshot.getValue(User.class);
                    if(user == null){
                        user.setUserid("000");
                        user.setPhone("+91 X");
                        user.setAddress2("X");
                        user.setAddress1("X");
                        user.setName("!Default!");
                        Toast.makeText(first_screen_si.this,"Unable to retrieve user info",Toast.LENGTH_LONG).show();
                    }
                    if (user !=null && user.getUserid().equals(currentuser.getUid())){
                        break;
                    }else {
                        continue;
                    }
                    //tv.setText(user.getPhone());

                }
                return;

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        return user.getName();
    }*/

    @Override
    protected void onStart() {
        fbd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if (userSnapshot.getChildren().equals(currentuser.getPhoneNumber())) {
                        user = userSnapshot.getValue(User.class);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_first_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.signout):

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), first_screen_wsi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(first_screen_si.this, "Signed Out", Toast.LENGTH_LONG).show();
                startActivity(i);

                return true;


            case (R.id.settings):

                startActivity(new Intent(getApplicationContext(), settings_page.class));
                break;

            case (R.id.about):
                startActivity(new Intent(getApplicationContext(), about_us_2.class));
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(first_screen_si.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(first_screen_si.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(first_screen_si.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    //Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    //sendSMS.setEnabled(false);

                }
                break;
        }
    }
}
