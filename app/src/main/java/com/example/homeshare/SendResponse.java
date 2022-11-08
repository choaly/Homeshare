package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendResponse extends AppCompatActivity {

    DatabaseReference userReference;
    DatabaseReference responseReference;
    DatabaseReference notificationsReference;
    FirebaseAuth auth;

    String listingKey, posterId, responderId, posterName, responderName, message;

    ArrayList<Response> responses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_response);


        //get info to make send response
        Intent intent = getIntent();
        listingKey = intent.getStringExtra("listingKey");
        posterId = intent.getStringExtra("posterId");
        posterName = intent.getStringExtra("posterName");
        TextView sendResponsePosterName =  (TextView) findViewById(R.id.sendResponsePosterName);
        sendResponsePosterName.setText(posterName);

        responderId = auth.getInstance().getCurrentUser().getUid();
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(responderId);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                responderName = firstName + " " + lastName;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        /*
        responses = new ArrayList<Response>();
        responseReference = FirebaseDatabase.getInstance().getReference().child("Listings").child(listingKey).child("responses");
        responseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren() ){
                    Response r = ds.getValue(Response.class);
                    responses.add(r);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        //back button
        TextView listingDetailsBackBtn = (TextView) findViewById(R.id.sendResponseBackButton);
        listingDetailsBackBtn.setOnClickListener(this::onClickBack);
        //send response button
        TextView listingDetailsSendResponseBtn = (TextView) findViewById(R.id.sendResponseSendButton);
        listingDetailsSendResponseBtn.setOnClickListener(this::onClickSend);

    }

    private void onClickSend(View view) {
        // get typed message
        message = ((EditText) findViewById(R.id.message)).getText().toString();

        // write response to db
        //responses = new ArrayList<Response>();
        responseReference = FirebaseDatabase.getInstance().getReference().child("Listings").child(listingKey).child("responses");
        Response r = new Response(posterId, responderId, posterName, responderName, message, false);
        //responses.add(r);
        responseReference.push().setValue(r);

        // write notif to db
        notificationsReference = FirebaseDatabase.getInstance().getReference().child("Notifications");
        ResponseNotif rn = new ResponseNotif();
    }

    private void onClickBack(View view) {
        finish();

    }
}