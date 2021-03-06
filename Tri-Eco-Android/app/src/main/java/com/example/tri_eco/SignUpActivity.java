package com.example.tri_eco;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG= "SignupActivity";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etCollege;
    private Button btnSignUp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnSignUp = findViewById(R.id.btnSignup);
        etCollege = findViewById(R.id.etCollege);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String college = etCollege.getText().toString();

                signUpUser(username,password, email, college);
            }
        });
    }




    private void signUpUser(String username, String password, String email, String college){
        // Create the ParseUser
        User user = new User();
        // Set core properties
        user.logOut();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCollegeName(college);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with sign up", e);
                   Toast.makeText(SignUpActivity.this, "Issue with sign up",Toast.LENGTH_SHORT).show();
                   return;
                } else {
                    goMainActivity();
                }
            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
