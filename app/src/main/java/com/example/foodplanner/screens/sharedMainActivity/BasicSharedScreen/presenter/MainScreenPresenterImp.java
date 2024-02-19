package com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter;

import static android.content.Context.MODE_PRIVATE;

import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealsRepository;
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
import android.util.Log;
import android.widget.Toast;

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


    public MainScreenPresenterImp(Context context, FirebaseUser user, GoogleSignInAccount account,LifecycleOwner lifeCycleOwner) {
        this.context = context;
        this.user = user;
        this.account = account;
       this.lifeCycleOwner=lifeCycleOwner;
        this.repository = MealsRepositoryImpl.getInstance(
                new ProductRemoteDataSourceImpl(),
                new FaviourtLocalDataSource(context),
                new PlannedLocalDataSourceImpl(context)
        );
    }

    @Override
    public boolean isguest() {
        if (user == null && account == null)
            isGuest = true;
        else {
            Log.d("keep", "inside is gurst fun  is guest = fales");
            isGuest = false;
            saveAcount();
            logInSoGetTheData(context);
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


    void logInSoGetTheData(Context context) {
//        Log.d("keep", "inside is login so get the data fun " + user.getUid() + "  "+ user.getEmail());
//        Toast.makeText(context, "get user data", Toast.LENGTH_SHORT).show();
        repository.getUserData(context );
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
        repository.deleteAllFav()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
//                            Toast.makeText(context, "Removed Favouirt DATA SUCCF", Toast.LENGTH_SHORT).show();
                        },
                        error -> {
//                            Toast.makeText(context, " FAUILT IN Removed Favouirt DATA", Toast.LENGTH_SHORT).show();

                        }
                );
        repository.addPlannedMealToFirebaseRepo( lifeCycleOwner,context);
//        Toast.makeText(context, " after add plan and before remove", Toast.LENGTH_SHORT).show();

        repository.removeAllPlannedMeals();
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, 0);
        editor = sharedPreferences.edit();
        editor.putString("email", "");
        editor.apply();
    }
}
