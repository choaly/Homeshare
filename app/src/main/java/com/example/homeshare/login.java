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
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, Listings.class);
        startActivity(intent);
        return;
    }
}