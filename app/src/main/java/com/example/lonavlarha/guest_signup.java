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
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class guest_signup extends AppCompatActivity {
    EditText phone_guest;
    EditText confirm_guest;
    EditText name_guest,add1_guest,add2_guest;
    TextView confirm;
    Button login_button;

    private String verficationId;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_signup);


        phone_guest = findViewById(R.id.phone_guest);
        confirm_guest = findViewById(R.id.password_guest);
        login_button = findViewById(R.id.login_button);
        confirm = findViewById(R.id.password);
        add1_guest = findViewById(R.id.add1_guest);
        add2_guest = findViewById(R.id.add2_guest);
        name_guest = findViewById(R.id.name_guest);




        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone_guest.getText().toString().isEmpty() || name_guest.getText().toString().isEmpty() || add1_guest.getText().toString().isEmpty() || add2_guest.getText().toString().isEmpty()) {
                    Toast.makeText(guest_signup.this, "Some Information is missing!", Toast.LENGTH_LONG).show();
                } else {
                    String phonenumber = "+91" + phone_guest.getText().toString();
                    confirm.setVisibility(View.VISIBLE);
                    confirm_guest.setVisibility(View.VISIBLE);
                    login_button.setText("Sign Up");
                    verifyphone(phonenumber);

                    login_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(confirm_guest.getText().toString().isEmpty() || confirm_guest.getText().toString().length() < 6){
                                confirm_guest.setError("Valid Code Required!");
                                confirm_guest.requestFocus();
                                return;
                            }

                            verifyCode(confirm_guest.getText().toString());


                        }
                    });

                    //createAccount();
                }
            }
        });




    }

    /*mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:" + credential);

            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.


            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
            }

            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.


            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;

            // ...
        }
    };*/

    private void verifyCode(String Code){

        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verficationId,Code);
        signInWithCridential(phoneAuthCredential);

    }

    private void signInWithCridential(PhoneAuthCredential phoneAuthCredential) {

        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            db = FirebaseDatabase.getInstance().getReference().child("Users");

                            User user = new User();
                            user.setName(name_guest.getText().toString());
                            user.setAddress1(add1_guest.getText().toString());
                            user.setAddress2(add2_guest.getText().toString());
                            user.setPhone("+91" + phone_guest.getText().toString());
                            user.setUserid(mAuth.getUid());
                            db.child("+91" + phone_guest.getText().toString()).setValue(user);
                            //FirebaseUser cuser = mAuth.getCurrentUser();



                            Intent intent = new Intent(getApplicationContext(),first_screen_si.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            Toast.makeText(guest_signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void verifyphone(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,// Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verficationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();

            if(code != null){
                confirm_guest.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(guest_signup.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };



   /* private void createAccount() {

        /*final FirebaseAuth guest_auth = FirebaseAuth.getInstance();
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
                            //fbdb.child(email_guest.getText().toString()).setValue(name_guest.getText().toString());
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
    }*/
}
