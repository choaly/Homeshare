package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.UUID;

public class ResponseDetails extends AppCompatActivity {

    DatabaseReference reference, responderRef;
    DatabaseReference ref;
    FirebaseAuth auth;

    String postTITLE, listingID, responseID, posterID, posterNAME, responderID, responderNAME, responderEmail;
    Integer numSpotsAvail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_details);

        reference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String msg = intent.getStringExtra("message");
        //postTITLE = intent.getStringExtra("postTitle");
        listingID = intent.getStringExtra("listingId");
        responseID = intent.getStringExtra("responseId");
        posterID = intent.getStringExtra("posterId");
        posterNAME = intent.getStringExtra("posterName");
        responderID = intent.getStringExtra("responderId");
        responderNAME = intent.getStringExtra("responderName");

        DatabaseReference responderRef = FirebaseDatabase.getInstance().getReference().child("Users/"+responderID);
        responderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String name = "About " + user.getFirstName() + " " + user.getLastName();
                String bio = user.getBio();
                String year = user.getYear();
                String gender = user.getGender();

                TextView aboutUsrView = (TextView) findViewById(R.id.detailAboutResponderName);
                aboutUsrView.setText(name);
                TextView usrBioView = (TextView) findViewById(R.id.detailResponderBio);
                usrBioView.setText(bio);
                TextView usrYearView = (TextView) findViewById(R.id.detailResponderYear);
                usrYearView.setText(year);
                TextView usrGenderView = (TextView) findViewById(R.id.detailResponderGender);
                usrGenderView.setText(gender);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        TextView responderName =  (TextView) findViewById(R.id.detailResponderName);
        responderName.setText(name);

        TextView responderMsg =  (TextView)findViewById(R.id.detailResponderMessage);
        responderMsg.setText(msg);

        TextView backButton = (TextView)findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClickBack);

        TextView rejectBtn = (TextView) findViewById(R.id.rejectButton);
        rejectBtn.setOnClickListener(this::onClickReject);

        TextView acceptBtn = (TextView) findViewById(R.id.acceptButton);
        acceptBtn.setOnClickListener(this::onClickAccept);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot numSpotRef = snapshot.child("Listings").child(listingID).child("numSpotsAvail");
                numSpotsAvail = numSpotRef.getValue(Integer.class);

                DataSnapshot userEmailRef = snapshot.child("Users").child(responderID).child("email");
                responderEmail = userEmailRef.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void onClickAccept(View view) {
        // add user to confirmed matched
        reference.child("Listings").child(listingID).child("confirmedMatches").push().setValue(responderID);

        //send matched notification to responder with poster's info
        String posterEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        DatabaseReference responderNotifRef = reference.child("Users").child(responderID).child("notifications");
        MatchedNotif responderMN = new MatchedNotif(posterNAME, posterEmail);
        String responderMatchedNotifKey = "matched" + UUID.randomUUID().toString();
        responderNotifRef.child(responderMatchedNotifKey).setValue(responderMN);

        //create matched notif for responder with responder's info
        DatabaseReference posterNotifRef = reference.child("Users").child(posterID).child("notifications");
        MatchedNotif posterMN = new MatchedNotif(responderNAME, responderEmail);
        String posterMatchedNotifKey = "matched" + UUID.randomUUID().toString();
        posterNotifRef.child(posterMatchedNotifKey).setValue(posterMN);

        // remove listings and pending responses for the responder
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot listingRef = snapshot.child("Listings");
                //loop through all listings
                for (DataSnapshot listing: listingRef.getChildren()){
                    //remove listing where the posterid for the listing == responderid
                    if (Objects.equals(listing.child("posterId").getValue(String.class), responderID)){
                        listing.getRef().removeValue();
                        break;
                    }
                    //remove response where the responderid for the listing == responderid
                    DataSnapshot responseRef = listing.child("responses");
                    for (DataSnapshot response: responseRef.getChildren()) {
                        if (Objects.equals(response.child("responderId").getValue(String.class), responderID)){
                            response.getRef().removeValue();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //check decremented num spots availible
        numSpotsAvail -= 1;
        if (numSpotsAvail > 0){
            reference.child("Listings").child(listingID).child("numSpotsAvail").setValue(numSpotsAvail);
        }else{
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot listingRef = snapshot.child("Listings");
                    //loop through all listings
                    for (DataSnapshot listing: listingRef.getChildren()){
                        //remove listing where the posterid for the listing == posterid
                        if (Objects.equals(listing.child("posterId").getValue(String.class), posterID)){
                            listing.getRef().removeValue();
                            break;
                        }
                        //remove response where the responderid for the listing == posterid
                        DataSnapshot responseRef = listing.child("responses");
                        for (DataSnapshot response: responseRef.getChildren()) {
                            if (Objects.equals(response.child("responderId").getValue(String.class), posterID)){
                                response.getRef().removeValue();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        finish();
    }

    private void onClickReject(View view) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.child("Listings").child(listingID).child("responses").child(responseID).getRef().removeValue();
                /*DataSnapshot notifs = snapshot.child("Users").child(posterID).child("notifications");
                for (DataSnapshot n : notifs.getChildren()) {
                    if (Objects.equals(n.child("responseKey").getValue(String.class), responseID)){
                        n.getRef().removeValue();
                    }
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        finish();
    }

    private void onClickBack(View view) {
        finish();
        return;
    }


}