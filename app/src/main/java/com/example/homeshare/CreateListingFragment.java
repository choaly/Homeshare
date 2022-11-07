package com.example.homeshare;

import android.content.Intent;
import android.os.Bundle;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateListingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateListingFragment extends Fragment implements View.OnClickListener {

    FirebaseDatabase root;
    DatabaseReference reference;

    String listingTitle;
    String address;
    String leaseStartString;
    String leaseEndString;
    String description;
    String pricePerMonthString;
    String numSpotsAvailableString;
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

        return view;
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

        if (!dateErr && listingTitle.length() > 0 && address.length() > 0 && leaseStartString.length() > 0
                && leaseEndString.length() > 0 && description.length() > 0 && pricePerMonthString.length() > 0
                && numSpotsAvailableString.length() > 0 && preferredGender.length() > 0 && responseDeadlineString.length() > 0)
        {
            double pricePerMonth = Double.parseDouble(pricePerMonthString);
            int numSpotsAvailable = Integer.parseInt(numSpotsAvailableString);

            // Add all to db
            //root = FirebaseDatabase.getInstance();
            //reference = root.getReference("Listings");
            //Listing l = new Listing("111", listingTitle, description, address, leaseStart.toString(), leaseEnd.toString(), preferredGender, "emma", pricePerMonth, 3, 3, numSpotsAvailable);
            //reference.push().setValue("test");
            Log.i( "clicked", "This button was clicked!");
            //System.out.println("This button was clicked!");

            replaceFragment(new ListingsFragment());
            return;

        } else {
            String msg = "";
            if (dateErr) {
                msg += "Date error";
            }
            msg += " Please fill in all fields";
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

//        BottomNavigationView navigationView = (BottomNavigationView) getView().findViewById(R.id.bottomNavigationView);
//        navigationView.setBottomNavigationItemSelectedListener(this);
//        navigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) this);

        //set selected navigation bar icon to Listings Home

    }
}