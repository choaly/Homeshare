package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        TextView submitBtn = (TextView) findViewById(R.id.submitWelcomeButton);
        submitBtn.setOnClickListener(this::handleSubmitWelcome);
    }

    public void handleSubmitWelcome(View view) {
        int selectedGenderId = ((RadioGroup) findViewById(R.id.welcomeGenderRadioGroup)).getCheckedRadioButtonId();
        String gender = "";
        if (selectedGenderId != -1) {
            gender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();
        }

        String bio = ((TextView) findViewById(R.id.welcomeUserBio)).getText().toString();

        if (gender.length() > 0 && bio.length() > 0) {
            // TODO: add to user db
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            return;
        } else {
            TextView errMsg = findViewById(R.id.welcomeErrMsg);
            errMsg.setText("Please fill in all fields!");
        }
    }
}