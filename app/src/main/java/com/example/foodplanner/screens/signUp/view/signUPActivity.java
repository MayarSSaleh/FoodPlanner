package com.example.foodplanner.screens.signUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.MainScreenActivity;

public class signUPActivity extends AppCompatActivity {

    EditText email;
    EditText pass, confPass;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        email = findViewById(R.id.ed_user_email);
        pass = findViewById(R.id.ed_user_pass);
        confPass = findViewById(R.id.ed_conf_pass);
        next = findViewById(R.id.btn_loginandNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (signUPSucceful()) {
                if (true) {
                    Intent intent = new Intent(signUPActivity.this, MainScreenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


