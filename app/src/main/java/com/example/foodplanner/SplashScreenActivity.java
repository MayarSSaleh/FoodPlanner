package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodplanner.screens.logIn.view.loginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000; // 3 seconds delay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, loginActivity.class);
                startActivity(intent);
                finish(); // Finish the splash screen activity to prevent going back to it
            }
        }, SPLASH_DELAY);
    }
}
