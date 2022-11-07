package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListingDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        TextView listingDetailsBackBtn = (TextView) findViewById(R.id.listDetailsbackButton);
        listingDetailsBackBtn.setOnClickListener(this::onClickBack);

        TextView listingDetailsSendResponseBtn = (TextView) findViewById(R.id.listDetailsSendResponseButton);
        listingDetailsSendResponseBtn.setOnClickListener(this::onClickSend);
    }

    private void onClickBack(View view) {
        Intent intent = new Intent(this, ListingsFragment.class);
        startActivity(intent);
        return;
    }

    private void onClickSend(View view) {
        Intent intent = new Intent(this, SendResponse.class);
        startActivity(intent);
        return;
    }



}