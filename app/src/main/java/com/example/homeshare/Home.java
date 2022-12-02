package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.homeshare.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    ActivityHomeBinding binding;
    String backBtnFrom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ListingsFragment());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            backBtnFrom = extras.getString("backBtnFrom");
            //The key argument here must match that used in the other activity
        }

        switch (backBtnFrom) {
            case "responseDetailsPage":
                replaceFragment(new ResponsesFragment());
                break;
            case "listingDetailsPage":
                replaceFragment(new ListingsFragment());
                break;
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.listings:
                    replaceFragment(new ListingsFragment());
                    break;
                case R.id.responses:
                    replaceFragment(new ResponsesFragment());
                    break;
                case R.id.create_listings:
                    replaceFragment(new CreateListingFragment());
                    break;
                case R.id.notifications:
                    replaceFragment(new NotificationsFragment());
                     break;
                case R.id.my_account:
                    replaceFragment(new AccountFragment());
                    break;
            }

            return true;

        });

        FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fbdb.getReference();
        FirebaseAuth user = FirebaseAuth.getInstance();
        String userId = user.getCurrentUser().getUid();
        ref.child("Users/" + userId + "/notifications")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterable<DataSnapshot> children = snapshot.getChildren();
                        for (DataSnapshot child : children) {
                            Toast.makeText(Home.this, "New match or response!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


}