package com.example.foodplanner.screens.logIn.view;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.MainScreenActivity;

public class loginEnterUser extends AppCompatActivity {

    EditText email;
    EditText pass;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        email = findViewById(R.id.ed_user_email);
        pass = findViewById(R.id.ed_user_pass);
        enter = findViewById(R.id.btn_loginandNext);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (loginis()){
                if (true) {
                    Intent intent = new Intent(loginEnterUser.this, MainScreenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry,login Failed,Try again", Toast.LENGTH_LONG).show();
                }
            }});
        }
    }
