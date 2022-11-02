package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editView = (EditText) findViewById(R.id.email);
        String message = editView.getText().toString();

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
            Intent intent = new Intent(this, Notifications.class);
            startActivity(intent);
            return;
        }
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return;
    }
}