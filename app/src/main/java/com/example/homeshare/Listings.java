package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Listings extends AppCompatActivity implements ListingAdapter.OnListingListener{

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ListingAdapter listingAdapter;
    ArrayList<Listing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        recyclerView = findViewById(R.id.listingList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Listings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        listingAdapter = new ListingAdapter(this, list,  this);
        recyclerView.setAdapter(listingAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot ds: snapshot.getChildren() ){
                    Listing l = ds.getValue(Listing.class);
                    list.add(l);
                }
                listingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onListingClick(int position) {
        Listing l = list.get(position);
        Intent intent = new Intent(this, ListingDetails.class);
        intent.putExtra("title", l.getTitle());
        startActivity(intent);
    }
}