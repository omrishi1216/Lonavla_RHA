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

public class guest_signup extends AppCompatActivity {
    EditText email_guest;
    EditText password_guest;
    EditText name_guest;
    Button login_button;
    TextView signup_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_signup);


        email_guest = findViewById(R.id.email_guest);
        password_guest = findViewById(R.id.password_guest);
        login_button = findViewById(R.id.login_button);
        signup_text = findViewById(R.id.signup_text);
        name_guest = findViewById(R.id.name_guest);



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
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
                            Toast.makeText(guest_signup.this, "Succesfully Signed Up",
                                    Toast.LENGTH_LONG).show();
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
