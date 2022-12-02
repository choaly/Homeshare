package com.example.homeshare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ListingsFragment extends Fragment implements ListingAdapter.OnListingListener {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ListingAdapter listingAdapter;
    ArrayList<Listing> list;
    Map<String, Listing> hm = new HashMap<String, Listing>();

    Button priceAscendingBtn;
    Button priceDescendingBtn;
    Button distanceBtn;
    Button numBedsBtn;

    public ListingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListingsFragment newInstance(String param1, String param2) {
        ListingsFragment fragment = new ListingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listings, container, false);

        recyclerView = view.findViewById(R.id.listingList);
        FirebaseDatabase root = FirebaseDatabase.getInstance();
//        databaseReference = root.getReference("Listings");
        databaseReference = root.getReference();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );

        list = new ArrayList<>();
        listingAdapter = new ListingAdapter(getActivity(), list,  this);
        recyclerView.setAdapter(listingAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ds: snapshot.child("Listings").getChildren() ){ //each listing in Listings
                    Listing l = ds.getValue(Listing.class);
                    String key = ds.getKey(); //get the listing key
                    String listingId = ds.child("id").getValue(String.class);

                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid(); //get current user
                    snapshot.child("Users").child(uid).child("hiddenListings");

                    boolean listContainsHiddenListing = false;
                    for ( DataSnapshot datasnap : snapshot.child("Users").child(uid).child("hiddenListings").getChildren() ) {
                        System.out.println(datasnap.getKey());
                        if (Objects.equals(datasnap.getKey(), listingId)) { //if the hidden listing key matches current listing key, add it to the list
                            listContainsHiddenListing = true;
                            break;
                        }
                    }
                    if (!listContainsHiddenListing) {
                        hm.put(key, l);
                        list.add(l);
                    }

                }
                listingAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Functionality for filter buttons
        priceAscendingBtn = view.findViewById(R.id.sortByPriceAscending);
        priceAscendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new SortByPrice());
                listingAdapter.notifyDataSetChanged();
            }
        });

        priceDescendingBtn = view.findViewById(R.id.sortByPriceDescending);
        priceDescendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort( list,
                        Collections.reverseOrder( new SortByPrice() ) );
                listingAdapter.notifyDataSetChanged();
            }
        });

        distanceBtn = view.findViewById(R.id.sortByDistance);
        distanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new SortByDistance());
                listingAdapter.notifyDataSetChanged();
            }
        });

        numBedsBtn = view.findViewById(R.id.sortByNumBeds);
        numBedsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort( list,
                        Collections.reverseOrder( new SortByNumBeds() ) );
                listingAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onListingClick(int position) {
        Listing l = list.get(position);
        Intent intent = new Intent(getActivity(), ListingDetails.class);

        String key = "";

        for (Map.Entry<String, Listing> set : hm.entrySet()) {
            if (set.getValue() == l){
                key = set.getKey();
            }
        }
        System.out.println(key);
        intent.putExtra("key", key);
        intent.putExtra("id", l.getId());
        intent.putExtra("title", l.getTitle());
        intent.putExtra("address", l.getAddress());
        intent.putExtra("leaseStart", l.getLeaseStart());
        intent.putExtra("leaseEnd", l.getLeaseEnd());
        intent.putExtra("descrip", l.getDescription());
        intent.putExtra("prefGen", l.getPrefGender());
        intent.putExtra("poster", l.getPosterName());
        intent.putExtra("price", l.getPrice());
        intent.putExtra("numSpots", l.getNumSpotsAvail());
        intent.putExtra("posterId", l.getPosterId());

        startActivity(intent);
    }



}