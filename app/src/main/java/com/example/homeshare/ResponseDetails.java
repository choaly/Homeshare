package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResponseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grade = intent.getStringExtra("message");

        TextView backButton = (TextView) findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClickBack);

        TextView responderName =  (TextView) findViewById(R.id.detailResponderName);
        responderName.setText(name);

        TextView responderGrade =  (TextView) findViewById(R.id.detailResponderGrade);
        responderGrade.setText(grade);

    }

    private void onClickBack(View view) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("backBtnFrom", "responseDetailsPage");
        startActivity(intent);

        return;
    }

}