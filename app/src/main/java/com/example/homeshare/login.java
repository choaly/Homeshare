package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        TextView loginButton = (TextView) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this::onClickLogin);

        TextView loginBackButton = (TextView) findViewById(R.id.loginBackButton);
        loginBackButton.setOnClickListener(this::onClickBack);
    }

    public void onClickLogin(View view) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String pass = ((EditText) findViewById(R.id.password)).getText().toString();
        TextView err = (TextView) findViewById(R.id.loginErrMsg);
        if (email.equals("") || pass.equals("")) {
            err.setText("Error: Please fill out all fields!");
        } else {
            err.setText("");
            logInUser(email, pass);
        }
    }

    private void logInUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, Home.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this, "Error logging in. Please check username and password.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return;
    }
}