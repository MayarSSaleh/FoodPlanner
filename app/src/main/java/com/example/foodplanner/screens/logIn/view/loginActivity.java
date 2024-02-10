package com.example.foodplanner.screens.logIn.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.SplashScreenActivity;
import com.example.foodplanner.screens.sharedMainActivity.MainScreenActivity;
import com.example.foodplanner.screens.signUp.view.signUPActivity;

public class loginActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnSignUp;
    Button btnGoogle;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnGoogle = findViewById(R.id.btn_google);
        btnSignUp = findViewById(R.id.btn_signUp);
        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, loginEnterUser.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, signUPActivity.class);
                startActivity(intent);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
