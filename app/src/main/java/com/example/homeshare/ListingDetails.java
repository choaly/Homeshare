package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListingDetails extends AppCompatActivity {

    String lisitngKey, posterId, posterName, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);

        TextView listingDetailsBackBtn = (TextView) findViewById(R.id.listDetailsbackButton);
        listingDetailsBackBtn.setOnClickListener(this::onClickBack);

        TextView listingDetailsSendResponseBtn = (TextView) findViewById(R.id.listDetailsSendResponseButton);
        listingDetailsSendResponseBtn.setOnClickListener(this::onClickSend);

        Intent intent = getIntent();

        lisitngKey = intent.getStringExtra("key");
        posterId = intent.getStringExtra("posterId");
        posterName = intent.getStringExtra("poster");
        title = intent.getStringExtra("title");

        String id = intent.getStringExtra("id");
        String description = intent.getStringExtra("descrip");
        String leaseStart = intent.getStringExtra("leaseStart");
        String leaseEnd = intent.getStringExtra("leaseEnd");
        String address = intent.getStringExtra("address");
        String prefGender = intent.getStringExtra("prefGen");
        String price = intent.getStringExtra("price");
        String numSpots = intent.getStringExtra("numSpots");


        TextView tv1 =  (TextView) findViewById(R.id.listingPostTitle);
        tv1.setText(title);
        TextView tv2 =  (TextView) findViewById(R.id.posterName);
        String posterStr = "Posted by " +  posterName;
        tv2.setText(posterStr);
        TextView tv3 =  (TextView) findViewById(R.id.listingDescription);
        tv3.setText(description);

        TextView tv4 =  (TextView) findViewById(R.id.listingStartDate);
        String leaseStartStr = "Lease Start:" +  leaseStart;
        tv4.setText(leaseStartStr);
        TextView tv5 =  (TextView) findViewById(R.id.listingEndDate);
        String leaseEndStr = "Lease End:" +  leaseEnd;
        tv5.setText(leaseEndStr);

        Button hideListingBtn = findViewById(R.id.hideLisitng);
        hideListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                FirebaseDatabase root = FirebaseDatabase.getInstance();
                DatabaseReference ref = root.getReference().child("Users/"+uid+"/hiddenListings/" + id);
                ref.setValue("true");
                Toast.makeText(ListingDetails.this, "Listing " + title + " hidden", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListingDetails.this, Home.class);
                startActivity(intent);
                return;
            }
        });

    }

    private void onClickBack(View view) {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("backBtnFrom", "listingDetailsPage");
        startActivity(intent);
        return;
    }

    private void onClickSend(View view) {
        Intent intent = new Intent(this, SendResponse.class);
        // need to get the listing id from the db
        intent.putExtra("listingKey", lisitngKey);
        intent.putExtra("posterId", posterId);
        intent.putExtra("posterName", posterName);
        intent.putExtra("postTitle", title);
        startActivity(intent);
        return;
    }

}