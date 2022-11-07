package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        TextView submitBtn = (TextView) findViewById(R.id.signUpButton);
        submitBtn.setOnClickListener(this::onClickSignup);

        TextView backBtn = (TextView) findViewById(R.id.signupBackButton);
        backBtn.setOnClickListener(this::onClickBack);
    }

    public void onClickSignup(View view) {
        String fName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String email = ((EditText) findViewById(R.id.signUpEmail)).getText().toString();
        String pass = ((EditText) findViewById(R.id.signUpPassword)).getText().toString();
        String confPass = ((EditText) findViewById(R.id.confPassword)).getText().toString();
        TextView err = (TextView) findViewById(R.id.signUpErrMsg);
        if (fName.equals("") || lName.equals("") || email.equals("") || pass.equals("") || confPass.equals("")) {
            err.setText("Error: Please fill out all fields!");
        } else if (!pass.equals(confPass)) {
            err.setText("Error: Passwords don't match!");
        } else {
            err.setText("");
            registerUser(email, pass);
        }
    }

    private void registerUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "Success, welcome to Homeshare!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Signup.this, "ERROR creating user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return;
    }
}