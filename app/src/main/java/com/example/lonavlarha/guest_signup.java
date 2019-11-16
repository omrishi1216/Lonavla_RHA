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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class guest_signup extends AppCompatActivity {
    EditText email_guest;
    EditText password_guest;
    EditText name_guest;
    Button login_button;
    TextView signin_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_signup);


        email_guest = findViewById(R.id.email_guest);
        password_guest = findViewById(R.id.password_guest);
        login_button = findViewById(R.id.login_button);
        signin_text = findViewById(R.id.signin_text);
        name_guest = findViewById(R.id.name_guest);

        email_guest.setText("");
        password_guest.setText("");
        name_guest.setText("");



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_guest.getText().toString().equals("") || password_guest.getText().toString().equals("") || name_guest.getText().toString().equals("")) {
                    Toast.makeText(guest_signup.this, "Some Information is missing!", Toast.LENGTH_LONG).show();
                } else {
                    createAccount();
                }
            }
        });

        signin_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),guest_login.class));
            }
        });


    }

    private void createAccount() {

        final FirebaseAuth guest_auth = FirebaseAuth.getInstance();
        guest_auth.createUserWithEmailAndPassword(email_guest.getText().toString(), password_guest.getText().toString())
                .addOnCompleteListener(guest_signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = guest_auth.getCurrentUser();
                            DatabaseReference fbdb = FirebaseDatabase.getInstance().getReference().child("Users");
                            Toast.makeText(guest_signup.this, "Successfully Signed Up",
                                    Toast.LENGTH_LONG).show();
                            fbdb.child(email_guest.getText().toString()).setValue(name_guest.getText().toString());
                            Intent i = new Intent(getApplicationContext(), first_screen_si.class);
                            i.putExtra("User Name",name_guest.getText().toString() );
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(guest_signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }


                });
    }
}
