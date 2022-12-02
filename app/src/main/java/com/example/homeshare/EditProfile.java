package com.example.homeshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    FirebaseDatabase root;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        auth = FirebaseAuth.getInstance();

        // Get prefills
        root = FirebaseDatabase.getInstance();
        DatabaseReference userRef = root.getReference().child("Users/" + auth.getCurrentUser().getUid());
        EditText fName = findViewById(R.id.editFirstName);
        EditText lName = findViewById(R.id.editLastName);
        RadioGroup gender = findViewById(R.id.editGenderRadioGroup);
        EditText year = findViewById(R.id.editYearInSchool);
        EditText bio = findViewById(R.id.editUserBio);
        // Set prefills
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                fName.setText(user.getFirstName());
                lName.setText(user.getLastName());
                if (user.getGender().equals("Female")) {
                    gender.check(R.id.editFemaleRadioButton);
                } else if (user.getGender().equals("Male")) {
                    gender.check(R.id.editMaleRadioBtn);
                } else {
                    gender.check(R.id.editNBRadioButton);
                }
                year.setText(user.getYear());
                bio.setText(user.getBio());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextView submitBtn = (TextView) findViewById(R.id.submitEditButton);
        submitBtn.setOnClickListener(this::handleEditProfile);
    }

    public static Boolean allFieldsFilledOut(String fName, String lName, String gender, String year, String bio) {
        return fName.length() > 0 && lName.length() > 0 && gender.length() > 0 && year.length() > 0 && bio.length() > 0;
    }

    public void handleEditProfile(View view) {
        String fName = ((EditText) findViewById(R.id.editFirstName)).getText().toString();
        String lName = ((EditText) findViewById(R.id.editLastName)).getText().toString();
        int selectedGenderId = ((RadioGroup) findViewById(R.id.editGenderRadioGroup)).getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            gender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();
        }
        String year = ((EditText) findViewById(R.id.editYearInSchool)).getText().toString();
        String bio = ((TextView) findViewById(R.id.editUserBio)).getText().toString();

        if (allFieldsFilledOut(fName, lName, gender, year, bio)) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            // add to user db
            root = FirebaseDatabase.getInstance();
            User user = new User(fName, lName, email, gender, year, bio);
            root.getReference().child("Users/"+uid).setValue(user);

            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            return;
        } else {
            TextView errMsg = findViewById(R.id.editErrMsg);
            errMsg.setText("Please fill in all fields!");
        }
    }
}