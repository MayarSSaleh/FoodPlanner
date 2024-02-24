package com.example.foodplanner;

import static com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter.MainScreenPresenterImp.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.screens.logIn.view.loginActivity;
import com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.view.MainScreenActivity;

public class SplashScreenActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String check;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        lottieAnimationView = findViewById(R.id.lottie);
        check = sharedPreferences.getString("email", "");

        lottieAnimationView.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((check.equals("true"))) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainScreenActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }
}
