package com.example.foodplanner;

import static com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter.MainScreenPresenterImp.SHARED_PREFS;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.screens.logIn.view.loginActivity;
import com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.view.MainScreenActivity;

public class SplashScreenActivity extends AppCompatActivity {


//    GoogleSignInOptions googleSignInOptions;
//    GoogleSignInClient googleSignInClient;
//    GoogleSignInAccount account;
//
//    FirebaseAuth auth;
//    FirebaseUser user;

    private static final long SPLASH_DELAY = 3000; // 3 seconds delay
    SharedPreferences sharedPreferences ;
    String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

         sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
         check = sharedPreferences.getString("email", "");
        Log.d("keep","inside spalsh itis uerer");
        if ((check.equals("true"))) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreenActivity.this, MainScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DELAY);
        }else { new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DELAY);

        }


//        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
//        account = GoogleSignIn.getLastSignedInAccount(this);
//
//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
////
////        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
////        String receivedMessageFromSP = preferences.getString("userEmail", "null");
//
//
////        if (receivedMessageFromSP == null ) {
//
//            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(SplashScreenActivity.this, loginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }, SPLASH_DELAY);
////        } else {
////            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    Intent intent = new Intent(SplashScreenActivity.this, MainScreenActivity.class);
////                    startActivity(intent);
////                    finish();
////                }
////            }, SPLASH_DELAY);
//        }
        }
    }
