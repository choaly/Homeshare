package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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
        String id = intent.getStringExtra("id");


        DatabaseReference listingRef = FirebaseDatabase.getInstance().getReference().child("Listings/"+lisitngKey);
        listingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Listing l = snapshot.getValue(Listing.class);

                String numSpots = String.valueOf(l.getNumSpotsAvail());
                String price = String.valueOf(l.getPrice());
                String numBeds = String.valueOf(l.getNumBeds());
                String numBaths = String.valueOf(l.getNumBaths());
                String dist = String.valueOf(l.getDistToCampus());


                TextView tv1 =  (TextView) findViewById(R.id.listingPostTitle);
                tv1.setText(l.getTitle());
                TextView tv2 =  (TextView) findViewById(R.id.posterName);
                tv2.setText("Posted by " +  l.getPosterName());
                TextView tv3 =  (TextView) findViewById(R.id.listingAddress);
                tv3.setText("Address: " +  l.getAddress());
                TextView tv4 =  (TextView) findViewById(R.id.listingNumSpotsAvailible);
                tv4.setText("Number of Spots Available: " + numSpots);
                TextView tv5 =  (TextView) findViewById(R.id.listingDescription);
                tv5.setText("Description: " +  l.getDescription());

                TextView tv6 =  (TextView) findViewById(R.id.listingNumBeds);
                tv6.setText("Beds: " + numBeds);
                TextView tv7 =  (TextView) findViewById(R.id.listingNumBaths);
                tv7.setText("Baths: " + numBaths);
                TextView tv8 =  (TextView) findViewById(R.id.rent);
                tv8.setText("Rent: " +  price + " per month");
                TextView tv9 =  (TextView) findViewById(R.id.listingDist);
                tv9.setText("Distance to Campus " +  dist + " mi");
                TextView tv10 =  (TextView) findViewById(R.id.preferredGender);
                tv10.setText("Preferred Gender: " + l.getPrefGender());
                TextView tv11 =  (TextView) findViewById(R.id.listingStartDate);
                tv11.setText("Lease Start: " +  l.getLeaseStart());
                TextView tv12 =  (TextView) findViewById(R.id.listingEndDate);
                tv12.setText("Lease End: " +  l.getLeaseEnd());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users/"+posterId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String bio = user.getBio();
                String year = user.getYear();
                String gender = user.getGender();


                TextView usrBioView = (TextView) findViewById(R.id.posterBio);
                usrBioView.setText("User Bio: " + bio);
                TextView usrYearView = (TextView) findViewById(R.id.posterGrade);
                usrYearView.setText("Year: " + year);
                TextView usrGenderView = (TextView) findViewById(R.id.posterGender);
                usrGenderView.setText("Gender: " + gender);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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