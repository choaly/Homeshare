package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;

import com.example.homeshare.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;

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
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


}