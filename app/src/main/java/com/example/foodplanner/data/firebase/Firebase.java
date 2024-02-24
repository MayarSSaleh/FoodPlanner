package com.example.foodplanner.data.firebase;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Meal;
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

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Firebase {

    static final String TAG = "TAG";
    static DatabaseReference planDB;
    static String currentUserId;


    public static void addFavMealToFirebase(Meal meal, Context context) {
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
            planDB = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(currentUserId)
                    .child("favorites")
                    .child(mealId);
            planDB.setValue(meal)
                    .addOnSuccessListener(aVoid -> {
                        Log.i(TAG, "Meal added to favorites");
                    })
                    .addOnFailureListener(e -> {
                    });
        }
    }

    public static void addPlannedMealToFirebase(PlannedMeals meal, Context context) {
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
            planDB = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(currentUserId)
                    .child("plan")
                    .child(mealId);

            planDB.setValue(meal)
                    .addOnSuccessListener(aVoid -> {
                    })
                    .addOnFailureListener(e -> {
                    });
        }
    }

    public static void updatePlannedMealFridayInFirebase(String mealId, String day, boolean sign, Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            DatabaseReference planDB = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(currentUserId)
                    .child("plan")
                    .child(mealId);
            Map<String, Object> mealUpdates = new HashMap<>();
            mealUpdates.put(day, sign);

            planDB.updateChildren(mealUpdates)
                    .addOnSuccessListener(aVoid -> {
                    })
                    .addOnFailureListener(e -> {
                    });
        }
    }

    public static void removePlannedMealFromFirebase(PlannedMeals meal, Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            FirebaseDatabase.getInstance().getReference("users")
                    .child(currentUserId)
                    .child("plan")
                    .child(meal.getMealId())
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            } else {
                            }
                        }
                    });
        }
    }

    public static void removeFavMealFromFirebase(Meal meal, Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            FirebaseDatabase.getInstance().getReference("users")
                    .child(currentUserId)
                    .child("favorites")
                    .child(meal.getMealId())
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            } else {
                            }
                        }
                    });
        }
    }

    public static void getAllUserDataFromFirebase(Context context) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            currentUserId = account.getId();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            currentUserId = user.getUid();
        }
        if (currentUserId != null) {
            MealsRepositoryImpl mealsRepository = MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(),
                    new FavouriteLocalDataSource(context), new PlannedLocalDataSourceImpl(context));
            getFavouriteMeals(mealsRepository, currentUserId);
            getPlannedMeals(mealsRepository, currentUserId);
        }
    }

    static void getFavouriteMeals(MealsRepositoryImpl mealsRepository, String currentUserId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users").
                child(currentUserId).
                child("favorites");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Meal meal = dataSnapshot.getValue(Meal.class);
                    if (meal != null) {
                        mealsRepository.insertFromFirbaseToLocalFavTable(meal)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> {
                                        },
                                        error -> {
                                        }
                                );
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
           }
        });


    }

    static void getPlannedMeals(MealsRepositoryImpl mealsRepository, String currentUserId) {
        DatabaseReference planDataBaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users").
                child(currentUserId).
                child("plan");
        planDataBaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    PlannedMeals meal = dataSnapshot.getValue(PlannedMeals.class);
                    Log.d("keep", " on add: " + meal.getMealId());

                    if (meal != null) {
                        Log.d("keep", " on add: " + meal.getPlannedMeal().getName());
                        mealsRepository.insertFromFirebaseToLocalPlanTable(meal);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

