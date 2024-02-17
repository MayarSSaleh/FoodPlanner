package com.example.foodplanner.data.firebase;

import android.util.Log;

import com.example.foodplanner.data.model.MealCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.annotations.NonNull;

public class UpdateFirebase {

    String test = "test";
    static DatabaseReference favoRef;

    public static void addMealToFirebase(MealCard meal) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Log.i("test", "currentUser" + currentUser);

            String userId = currentUser.getUid();
            Log.i("test", "userId" + userId);

            String mealId = meal.getMealId();

            favoRef = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(userId)
                    .child("favorites")
                    .child(mealId);

            // Map the meal object directly to the database
            favoRef.setValue(meal)
                    .addOnSuccessListener(aVoid -> {
                        Log.i("test", "Meal added to favorites");
//                        Toast.makeText(getContext(), "Added To Favourite", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Log.i("test", "Meal added to faiulrer");
//                        Toast.makeText(getContext(), "Failed to add to Favourite", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    public static void removeMealFromFirebase(MealCard meal) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            Log.i ("test", "userId in remove"+ userId);
            FirebaseDatabase.getInstance().getReference("users")
                    .child(userId)
                    .child("favorites")
                    .child(meal.getMealId())
                    .removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.i("test", "Meal removed from Firebase successfully");
                            } else {
                                Log.i("test", "Meal removed from Firebase fauiler");
                            }
                        }
                    });
        }


//        public void getFav(Context context, FirebaseUser user) {
//
//            DatabaseReference rootFav = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(user.getUid()).child("Favorites");
//            rootFav.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Meal meal = dataSnapshot.getValue(Meal.class);
//                        Repository repo = Repository.getInstance(ApiClient.getInstance(), ConcreteLocalSource.getInstance(context, "0"), context);
//                        repo.insert(meal);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Log.i("test", error.getMessage());
//                }
//            });
//        }
    }
}
