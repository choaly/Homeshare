package com.example.homeshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView submitBtn = (TextView) findViewById(R.id.signUpButton);
        submitBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String fName = ((EditText) findViewById(R.id.firstName)).getText().toString();
                        String lName = ((EditText) findViewById(R.id.lastName)).getText().toString();
                        String email = ((EditText) findViewById(R.id.signUpEmail)).getText().toString();
                        String pass = ((EditText) findViewById(R.id.signUpPassword)).getText().toString();
                        String confPass = ((EditText) findViewById(R.id.confPassword)).getText().toString();
                        TextView err = (TextView) findViewById(R.id.signUpErrMsg);
                        if (fName.equals("") || lName.equals("") || email.equals("") || pass.equals("") || confPass.equals("")) {
                            err.setText("Error: Please fill out all fields!");
                        } else if (!pass.equals(confPass)) {
                            err.setText("Error: Passwords don't match!");
                        } else {
                            err.setText("");
                        }
                    }
                });
    }
}