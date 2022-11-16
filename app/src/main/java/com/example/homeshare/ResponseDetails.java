package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ResponseDetails extends AppCompatActivity {

    DatabaseReference reference;

    String listingID, responseID, posterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grade = intent.getStringExtra("message");
        listingID = intent.getStringExtra("listingId");
        responseID = intent.getStringExtra("responseId");
        posterID = intent.getStringExtra("posterId");

        TextView responderName =  (TextView) findViewById(R.id.detailResponderName);
        responderName.setText(name);

        TextView responderGrade =  (TextView) findViewById(R.id.detailResponderGrade);
        responderGrade.setText(grade);

        TextView rejectBtn = (TextView) findViewById(R.id.rejectButton);
        rejectBtn.setOnClickListener(this::onClickReject);

        TextView acceptBtn = (TextView) findViewById(R.id.acceptButton);
        acceptBtn.setOnClickListener(this::onClickAccept);

        TextView backButton = (TextView)findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClickBack);

    }

    private void onClickAccept(View view) {
        // get the responderId
        // "Listings" -> "confirmedMatches" ->

        // need to get num of spots availible

    }

    private void onClickReject(View view) {
        reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.child("Listings").child(listingID).child("responses").child(responseID).getRef().removeValue();
                DataSnapshot notifs = snapshot.child("Users").child(posterID).child("notifications");
                for (DataSnapshot n : notifs.getChildren()) {
                    if (Objects.equals(n.child("responseKey").getValue(String.class), responseID)){
                        n.getRef().removeValue();
                    }
                }
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