package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Responses extends AppCompatActivity implements ResponseAdapter.OnResponseListener {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ResponseAdapter responseAdapter;
    ArrayList<Response> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responses);

        recyclerView = findViewById(R.id.responseList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Responses");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        responseAdapter = new ResponseAdapter(this, list,  this);
        recyclerView.setAdapter(responseAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot ds: snapshot.getChildren() ){
                    Response r = ds.getValue(Response.class);
                    list.add(r);
                }
                responseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onResponseClick(int position) {
        Response r = list.get(position);
        Intent intent = new Intent(this, ResponseDetails.class);
        intent.putExtra("name", r.getName());
        intent.putExtra("grade", r.getGrade());
        intent.putExtra("gender", r.getGender());
        intent.putExtra("postTitle", r.getPostTitle());
        startActivity(intent);
    }
}