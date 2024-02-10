package com.example.foodplanner.screens.Card.view;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.model.MealCard;

import java.util.ArrayList;
import java.util.List;

public class MealCardActivity extends AppCompatActivity {
    //    String videoUrl = "https://www.example.com/your_video.mp4";

    MealCard currentMeal;
    String TAG = "TAG";
    private ImageView mealImage;
    private ImageView addToFav;
    private TextView mealName;
    private TextView country;
    private TextView allIngredients;
    private TextView allSteps;
    private Button addToPlanButton;
    VideoView videoView;
    private boolean isFav;

    public void favImage(String mealId) {
        APPDataBase db = APPDataBase.getInstance(this);
        FavMealsDAO dao = db.getFavMealsDAO();
        LiveData<List<FavMeals>> favourits = dao.getAllFavProducts();
        favourits.observe(this, new Observer<List<FavMeals>>() {
            @Override
            public void onChanged(List<FavMeals> favMealsList) {
                for (FavMeals favMeal : favMealsList) {
                    if (favMeal.getName().equals(currentMeal.getName())) {
                        Log.d(TAG, "Found a FavMeals object with the desired name: " + favMeal.getName());
                        Drawable drawable = addToFav.getDrawable();
                        drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                        addToFav.setImageDrawable(drawable);
                    } else {
                        Drawable drawable = addToFav.getDrawable();
// Remove the color filter (tint)
                        drawable.setColorFilter(null);
                        addToFav.setImageDrawable(drawable);
                    }
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcard);
        UI();
        addToPlanButton.setOnClickListener(view -> {
// btn add to plan choose the day then toast done
        });
        mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For example, you can start a new activity, show a message, etc.
            }
        });
        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void UI() {
        mealImage = findViewById(R.id.img_meal);
        mealName = findViewById(R.id.tv_mealName);
        country = findViewById(R.id.tv_country);
        allIngredients = findViewById(R.id.all_ingredients);
        allSteps = findViewById(R.id.steps);
        addToFav = findViewById(R.id.img_fav);
        addToPlanButton = findViewById(R.id.btn_add_to_plan);
        videoView = findViewById(R.id.videoView);
    }

    public void playVideo(String videoUrl) {
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}