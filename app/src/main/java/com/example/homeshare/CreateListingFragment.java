package com.example.homeshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateListingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateListingFragment extends Fragment implements View.OnClickListener {

    DatabaseReference listingsReference;
    DatabaseReference userReference;
    FirebaseAuth auth;
    String fullName, userId;
    String listingTitle, address, leaseStartString, leaseEndString, description, pricePerMonthString, numSpotsAvailableString, numBedsString, numBathsString, distToCampusString;
    int selectedGenderId;
    String preferredGender = "";
    Button postBtn;

    public CreateListingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreateListingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateListingFragment newInstance(String param1, String param2) {
        CreateListingFragment fragment = new CreateListingFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_listing, container, false);
        postBtn = (Button)view.findViewById(R.id.postListingButton);
        postBtn.setOnClickListener((View.OnClickListener) this);

        //get current user id and current user name
        userId = auth.getInstance().getCurrentUser().getUid();
        System.out.println(userId);
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String firstName = user.getFirstName();
                System.out.println(firstName);
                String lastName = user.getLastName();
                System.out.println(lastName);
                fullName = firstName + " " + lastName;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }

    public static boolean inputsNotEmpty(String listingTitle, String address, String leaseStartString, String leaseEndString, String description, String pricePerMonthString, String numSpotsAvailableString, String preferredGender, String responseDeadlineString, String numBedsString, String numBathsString, String distToCampusString){
        if (listingTitle.length() > 0 && address.length() > 0 && leaseStartString.length() > 0
                && leaseEndString.length() > 0 && description.length() > 0 && pricePerMonthString.length() > 0
                && numSpotsAvailableString.length() > 0 && preferredGender.length() > 0 && responseDeadlineString.length() > 0
                && numBedsString.length() > 0 && numBathsString.length() > 0 && distToCampusString.length() > 0){
            return true;
        }
        return false;
    }

    public static String setErrorMsg(boolean dateError){
        String message ="";
        if (dateError) {
            return message += "Date error";
        }
        return message += "Please fill in all fields";
    }


    @Override
    public void onClick(View view) {
        listingTitle = ((TextView) getView().findViewById(R.id.CLlistingTitle)).getText().toString();
        address = ((TextView) getView().findViewById(R.id.CLlistingAddr)).getText().toString();
        leaseStartString = ((TextView) getView().findViewById(R.id.CLleaseStart)).getText().toString();
        leaseEndString = ((TextView) getView().findViewById(R.id.CLleaseEnd)).getText().toString();
        description = ((TextView) getView().findViewById(R.id.CLlistingDescription)).getText().toString();
        pricePerMonthString = ((TextView) getView().findViewById(R.id.CLpricePerMonth)).getText().toString();
        numSpotsAvailableString = ((TextView) getView().findViewById(R.id.CLnumSpotsAvailable)).getText().toString();
        selectedGenderId = ((RadioGroup) getView().findViewById(R.id.CLprefGenderRadioGroup)).getCheckedRadioButtonId();
        numBedsString = ((TextView) getView().findViewById(R.id.CLnumBeds)).getText().toString();
        numBathsString = ((TextView) getView().findViewById(R.id.CLnumBaths)).getText().toString();
        distToCampusString = ((TextView) getView().findViewById(R.id.CLdistToCampus)).getText().toString();

        if (selectedGenderId != -1) {
            preferredGender = ((RadioButton) getView().findViewById(selectedGenderId)).getText().toString();
        }
        String responseDeadlineString = ((TextView) getView().findViewById(R.id.CLresponseDeadline)).getText().toString();
        Date responseDeadline = null;
        try {
            responseDeadline = new SimpleDateFormat("dd/MM/yyyy").parse(leaseEndString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
        }

        boolean dateErr = false;
        Date leaseStart = null;
        try {
            leaseStart = new SimpleDateFormat("dd/MM/yyyy").parse(leaseStartString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
            dateErr = true;
        }
        Date leaseEnd = null;
        try {
            leaseEnd = new SimpleDateFormat("dd/MM/yyyy").parse(leaseEndString);
        } catch (java.text.ParseException err) {
            err.printStackTrace();
            dateErr = true;
        }

        boolean inputsNotEmp = inputsNotEmpty(listingTitle, address, leaseStartString,leaseEndString, description,  pricePerMonthString, numSpotsAvailableString, preferredGender, responseDeadlineString, numBathsString, numBedsString, distToCampusString);

        if (!dateErr && inputsNotEmp )
        {
            double pricePerMonth = Double.parseDouble(pricePerMonthString);
            int numSpotsAvailable = Integer.parseInt(numSpotsAvailableString);
            int numBeds = Integer.parseInt(numBedsString);
            int numBaths = Integer.parseInt(numBedsString);
            double distToCampus = Double.parseDouble(distToCampusString);

            // Add all to db
            listingsReference = FirebaseDatabase.getInstance().getReference().child("Listings");
            String listingId = UUID.randomUUID().toString();
            Listing l = new Listing(listingId, listingTitle, description, address, leaseStart.toString(), leaseEnd.toString(), preferredGender, fullName, userId, responseDeadlineString, pricePerMonth, numSpotsAvailable, numBeds, numBaths, distToCampus);
            listingsReference.push().setValue(l);
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users/"+uid+"/activeListings/"+listingId);
            userRef.setValue("true");

            listingsReference.getKey();

            replaceFragment(new ListingsFragment());
            return;

        } else {
            String msg = setErrorMsg(dateErr);
            TextView errMsg = getView().findViewById(R.id.createListingErrMsg);
            errMsg.setText(msg);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

}