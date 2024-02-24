package com.example.foodplanner.screens.sharedMainActivity.basicSharedScreen.presenter;

import static android.content.Context.MODE_PRIVATE;

import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainScreenPresenterImp implements MainScreenPresenter {

    public static final String SHARED_PREFS = "sharedPreferences";
    Context context;
    FirebaseUser user;
    GoogleSignInAccount account;
    boolean isGuest;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MealsRepositoryImpl repository;
    LifecycleOwner lifeCycleOwner;


    public MainScreenPresenterImp(Context context, FirebaseUser user, GoogleSignInAccount account, LifecycleOwner lifeCycleOwner) {
        this.context = context;
        this.user = user;
        this.account = account;
        this.lifeCycleOwner = lifeCycleOwner;
        this.repository = MealsRepositoryImpl.getInstance(
                new ProductRemoteDataSourceImpl(),
                new FavouriteLocalDataSource(context),
                new PlannedLocalDataSourceImpl(context)
        );
    }

    @Override
    public boolean isGuest() {
        if (user == null && account == null)
            isGuest = true;
        else {
            isGuest = false;
            saveAcount();
            logInSoGetTheData(context);
        }
        return isGuest;
    }

    public void saveAcount() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "true");
        editor.apply();
    }

    public void logInSoGetTheData(Context context) {
        repository.getUserData(context);
    }
    @Override
    public boolean logOut(GoogleSignInClient googleSignInClient) {
        if (isNetworkConnected()) {
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

            repository.deleteAllFav()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                            },
                            error -> {
                            }
                    );
            repository.deletePlannedMeals();
            removeTheUser();
            return true;
        }
        return false;
    }

    void removeTheUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, 0);
        editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.apply();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}