package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateListing extends AppCompatActivity {

    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        Log.i( "tag", "create page");

        TextView postBtn = findViewById(R.id.postListingButton);
        postBtn.setOnClickListener(this::handleCreateListing);
    }

    public void handleCreateListing(View view) {
        String listingTitle = ((TextView) findViewById(R.id.CLlistingTitle)).getText().toString();
        String address = ((TextView) findViewById(R.id.CLlistingAddr)).getText().toString();
        String leaseStartString = ((TextView) findViewById(R.id.CLleaseStart)).getText().toString();
        String leaseEndString = ((TextView) findViewById(R.id.CLleaseEnd)).getText().toString();
        String description = ((TextView) findViewById(R.id.CLlistingDescription)).getText().toString();
        String pricePerMonthString = ((TextView) findViewById(R.id.CLpricePerMonth)).getText().toString();
        String numSpotsAvailableString = ((TextView) findViewById(R.id.CLnumSpotsAvailable)).getText().toString();
        int selectedGenderId = ((RadioGroup) findViewById(R.id.CLprefGenderRadioGroup)).getCheckedRadioButtonId();
        String preferredGender = "";
        if (selectedGenderId != -1) {
            preferredGender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();
        }
        String responseDeadlineString = ((TextView) findViewById(R.id.CLresponseDeadline)).getText().toString();
        Date responseDeadline = null;
        try {
            responseDeadline = new SimpleDateFormat("dd/MM/yyyy").parse(leaseEndString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
        }

        boolean dateErr = false;
        Date leaseStart = null;
        try {
            leaseStart = new SimpleDateFormat("dd/MM/yyyy").parse(leaseStartString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
            dateErr = true;
        }
        Date leaseEnd = null;
        try {
            leaseEnd = new SimpleDateFormat("dd/MM/yyyy").parse(leaseEndString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
            dateErr = true;
        }

        if (!dateErr && listingTitle.length() > 0 && address.length() > 0 && leaseStartString.length() > 0
                && leaseEndString.length() > 0 && description.length() > 0 && pricePerMonthString.length() > 0
                && numSpotsAvailableString.length() > 0 && preferredGender.length() > 0 && responseDeadlineString.length() > 0)
        {
            double pricePerMonth = Double.parseDouble(pricePerMonthString);
            int numSpotsAvailable = Integer.parseInt(numSpotsAvailableString);

            // Add all to db
            root = FirebaseDatabase.getInstance();
            reference = root.getReference().child("Listings");
            Listing l = new Listing( listingTitle, description, address, leaseStart.toString(), leaseEnd.toString(), preferredGender, "emma", pricePerMonth, numSpotsAvailable);
            reference.push().setValue(l);
            Log.i( "clicked", "This button was clicked!");
            //System.out.println("This button was clicked!");

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            return;

        } else {
            String msg = "";
            if (dateErr) {
                msg += "Date error";
            }
            msg += " Please fill in all fields";
            TextView errMsg = findViewById(R.id.createListingErrMsg);
            errMsg.setText(msg);
        }
    }
}