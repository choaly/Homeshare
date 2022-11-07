package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendResponse extends AppCompatActivity {

    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_response);

        TextView listingDetailsBackBtn = (TextView) findViewById(R.id.sendResponseBackButton);
        listingDetailsBackBtn.setOnClickListener(this::onClickBack);

        TextView listingDetailsSendResponseBtn = (TextView) findViewById(R.id.sendResponseSendButton);
        listingDetailsSendResponseBtn.setOnClickListener(this::onClickSend);

    }

    private void onClickSend(View view) {
        // read to database here
        /*
        root = FirebaseDatabase.getInstance();
        reference = root.getReference().child("");
        Response r = new Response();
        reference.push().setValue(r);

         */
    }

    private void onClickBack(View view) {
    }
}