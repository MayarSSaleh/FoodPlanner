package com.example.foodplanner.data.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepository;
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

import io.reactivex.rxjava3.annotations.NonNull;

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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users").child(currentUserId).child("favorites");
// to get all data once
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MealCard meal = dataSnapshot.getValue(MealCard.class);
                    if (meal != null) {
                        MealsRepository.insertAllFavMeals(meal, context);
                        Log.d("keep", "  in get fav : " + meal.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Database error: " + error.getMessage());
            }
        });
    }

}

