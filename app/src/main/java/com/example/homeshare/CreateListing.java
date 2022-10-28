package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateListing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        TextView postBtn = (TextView) findViewById(R.id.postListingButton);
        postBtn.setOnClickListener(this::handleCreateListing);
    }

    public void handleCreateListing(View view) {
        // Implement create listing and add to db functionality, reroute to home, etc
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        return;
    }
}