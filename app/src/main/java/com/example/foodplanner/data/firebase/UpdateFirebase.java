package com.example.foodplanner.data.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateFirebase {

    static final String TAG = "TAG";
    static DatabaseReference favoRef;
    static String currentUserId;


    public static void addMealToFirebase(MealCard meal, Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            String mealId = meal.getMealId();
            favoRef = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(currentUserId)
                    .child("favorites")
                    .child(mealId);

            favoRef.setValue(meal)
                    .addOnSuccessListener(aVoid -> {
//                        Log.i(TAG, "Meal added to favorites");
                    })
                    .addOnFailureListener(e -> {
//                        Log.i(TAG, "Meal added to faiulrer");
                    });
        }
    }

    public static void removeMealFromFirebase(MealCard meal, Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            Log.i(TAG, "currentUserId in remove" + currentUserId);
            FirebaseDatabase.getInstance().getReference("users")
                    .child(currentUserId)
                    .child("favorites")
                    .child(meal.getMealId())
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
//                                Log.i(TAG, "Meal removed from Firebase successfully");
                            } else {
//                                Log.i(TAG, "Meal removed from Firebase fauiler");
                            }
                        }
                    });
        }
    }

    public static void getFav(Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }

        ProductRemoteDataSourceImpl productRemoteDataSource = new ProductRemoteDataSourceImpl();
        FaviourtLocalDataSource prodcutsLocalDataSource = new FaviourtLocalDataSource(context);
        PlannedLocalDataSourceImpl plannedLocalDataSource = new PlannedLocalDataSourceImpl(context);
        MealsRepositoryImpl mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(currentUserId).child("favorites");
// to get all data once
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MealCard meal = dataSnapshot.getValue(MealCard.class);
                    if (meal != null) {
                        mealsRepository.insertFromFirbaseToLocal(mapMealCardToFavMeal(meal))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> {
                                            Toast.makeText(context, "The meal in your Favourites now", Toast.LENGTH_SHORT).show();
                                        },
                                        error -> {
                                            Toast.makeText(context, "Sorry, could not add it. Please try again later", Toast.LENGTH_SHORT).show();
                                        }
                                );
//                        Log.d("keep", "  in get fav : " + meal.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Database error: " + error.getMessage());
            }
        });
    }

    static private FavMeals mapMealCardToFavMeal(MealCard meal) {
        FavMeals newFavMeal = new FavMeals();
        newFavMeal.setName(meal.getName());
        newFavMeal.setMealId(meal.getMealId());
        newFavMeal.setCountry(meal.getCountry());
        newFavMeal.setPhotourl(meal.getPhotourl());
        newFavMeal.setVideoUrl(meal.getVideoUrl());
        newFavMeal.setAllingredient(meal.getAllingredient());
        newFavMeal.setFav(true);
        newFavMeal.setSteps(meal.getSteps());
        return newFavMeal;
    }
}

