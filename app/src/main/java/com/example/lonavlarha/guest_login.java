package com.example.lonavlarha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class guest_login extends AppCompatActivity {

    EditText email_guest;
    EditText password_guest;
    Button login_button;
    TextView signup_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);


        email_guest = findViewById(R.id.email_guest_1);
        password_guest = findViewById(R.id.password_guest_1);
        login_button = findViewById(R.id.login_button);
        signup_text = findViewById(R.id.signup_text);

        email_guest.setText("");
        password_guest.setText("");


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_guest.getText().toString().equals("") || password_guest.getText().toString().equals("")) {
                    Toast.makeText(guest_login.this, "Email and Password are required!", Toast.LENGTH_LONG).show();
                } else {
                    singinUser();
                }
            }

        });

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), guest_signup.class));
            }
        });
    }

    private void singinUser() {
        final FirebaseAuth guest_signin = FirebaseAuth.getInstance();
        guest_signin.signInWithEmailAndPassword(email_guest.getText().toString(), password_guest.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(guest_login.this, "Signed in Successfully!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), first_screen_si.class));
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = guest_signin.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(guest_login.this, "Authentication failed!",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }


}