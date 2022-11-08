package com.example.homeshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResponsesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResponsesFragment extends Fragment implements ResponseAdapter.OnResponseListener {

    RecyclerView recyclerView;
    DatabaseReference userReference;
    DatabaseReference databaseReference;
    ResponseAdapter responseAdapter;
    ArrayList<Response> list;
    String currentUserId;
    FirebaseAuth auth;

    ArrayList<String> activeListingsList;

    public ResponsesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResponsesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResponsesFragment newInstance(String param1, String param2) {
        ResponsesFragment fragment = new ResponsesFragment();
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
        View view = inflater.inflate(R.layout.fragment_responses, container, false);

        recyclerView = view.findViewById(R.id.responseList);
        // get the user
        // get their active listings
        // get the responses for each active listing

        /*
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("activeListings");
        activeListingsList = new ArrayList<String>();
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren() ){
                    String s = ds.getValue(String.class);
                    activeListingsList.add(s);
                    System.out.println(s);
                }
                System.out.println(activeListingsList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        responseAdapter = new ResponseAdapter(getActivity(), list, this);
        recyclerView.setAdapter(responseAdapter);
        currentUserId = auth.getInstance().getCurrentUser().getUid();
        System.out.println("currentuser : " + currentUserId);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Listings");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    System.out.println( "posterid: " + ds.child("posterId").getValue(String.class) );

                    if (ds.child("posterId").getValue(String.class) == currentUserId){

                        for (DataSnapshot rs : ds.child("responses").getChildren() ) {
                            Response r = rs.getValue(Response.class);
                            list.add(r);
                            System.out.println(r);
                        }
                    }
                    System.out.println(list);
                    responseAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }

    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //System.out.println(activeListingsList);
        for(int i=0; i<activeListingsList.size(); i++) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Listings").child(activeListingsList.get(i)).child("responses");
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
    }*/

    @Override
    public void onResponseClick(int position) {
        Response r = list.get(position);
        Intent intent = new Intent(getActivity(), ResponseDetails.class);
        intent.putExtra("name", r.getResponderName());
        intent.putExtra("message", r.getMessage());
        startActivity(intent);
    }
}