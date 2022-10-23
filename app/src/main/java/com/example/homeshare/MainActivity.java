package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView loginButton = (TextView) findViewById(R.id.login);
        loginButton.setOnClickListener(this::onClickLogin);
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        return;
    }

}