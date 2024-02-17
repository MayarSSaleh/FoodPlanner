package com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter;

import static android.content.Context.MODE_PRIVATE;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;

public class MainScreenPresenterImp implements MainScreenPresenter {

    public static final String SHARED_PREFS = "sharedPreferences";
    Context context;
    FirebaseUser user;
    GoogleSignInAccount account;
    boolean isGuest;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String currentUser;


    public MainScreenPresenterImp(Context context, FirebaseUser user, GoogleSignInAccount account) {
        this.context = context;
        this.user = user;
        this.account = account;
    }

    @Override
    public boolean isguest() {
        if (user == null && account == null)
            isGuest = true;
        else {
            Log.d("keep", "inside is gurst fun  is guest = fales");
            isGuest = false;
            saveAcount();
        }
        return isGuest;
    }

    @Override
    public void saveAcount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "true");
        editor.apply();
    }

    @Override
    public void logOut(GoogleSignInClient googleSignInClient) {
        if (user != null) {
            FirebaseAuth.getInstance().signOut();
        }
        if (account != null) {
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                }
            });
        }

        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, 0);
        editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.apply();
    }
}
