package com.example.android.guitarstore;

import android.content.Context;
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

public class LoginActivity2 extends AppCompatActivity {
    private FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener myAuthStateListener;
    EditText email, pass;
    Button btnSignIn;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        final Context ctx = this;

        myFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_field2);
        pass = findViewById(R.id.password_field2);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvRegister = findViewById(R.id.register_here);

        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (myFirebaseUser != null) {
                    Toast.makeText(LoginActivity2.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity2.this, HomeActivity.class);
                } else {
                    Toast.makeText(LoginActivity2.this, "Please Login!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String e = email.getText().toString();
                String p = pass.getText().toString();
                if (e.isEmpty() && p.isEmpty()) {
                    Toast.makeText(LoginActivity2.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else if (p.isEmpty()) {
                    pass.setError("Please enter your password!");
                    pass.requestFocus();
                } else if (e.isEmpty()) {
                    email.setError("Please enter your email!");
                    email.requestFocus();
                } else if (!e.isEmpty() && !p.isEmpty()) {
                    myFirebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(LoginActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity2.this, "Login Error! Please try again!", Toast.LENGTH_SHORT).show();
                            } else {
                                SaveSharedPreference.setUserName(ctx, e);
                                Intent i = new Intent(LoginActivity2.this, HomeActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity2.this,RegisterActivity.class));
            }
        });
    }
}
