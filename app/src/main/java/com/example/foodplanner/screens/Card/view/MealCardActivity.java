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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class MealCardActivity extends AppCompatActivity implements OnAddORrmoveFavClickListener {
    //    String videoUrl = "https://www.example.com/your_video.mp4";

    MealCard currentMeal;
    String TAG = "TAG";
    private ImageView mealImage;
    private ImageView favStatus;
    private TextView mealName;
    private TextView country;
    private TextView allIngredients;
    private TextView alling_amount;
    private TextView allSteps;
    private Button addToPlanButton;
    VideoView videoView;
    RecyclerView ingS_recyclerView;
    private boolean isFav;
    MealCardPresenterImp mealCardPresenterImp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcard);
        UI();
        showMealDetalis(currentMeal);
        handlingSetonAction();
    }

    public void UI() {
        mealImage = findViewById(R.id.img_meal);
        mealName = findViewById(R.id.tv_mealName);
        country = findViewById(R.id.tv_country);
       ingS_recyclerView=findViewById(R.id.recyclerView_ing);
        allSteps = findViewById(R.id.steps);
        favStatus = findViewById(R.id.img_fav);
        addToPlanButton = findViewById(R.id.btn_add_to_plan);
        videoView = findViewById(R.id.videoView);
    }

    public void showMealDetalis(MealCard mealCard) {
        mealName.setText(mealCard.getName());
        country.setText(mealCard.getCountry());

        allSteps.setText(mealCard.getSteps());
        Glide.with(MealCardActivity.this).load(mealCard.getPhotourl()).into(mealImage);
        playVideo(mealCard.getVideoUrl());
        updatefavImageAccordingActullayStatus(mealCard.getName());

    }

    public void handlingSetonAction() {
        addToPlanButton.setOnClickListener(view -> {
            // btn add to plan choose the day then toast done


        });
        favStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = favStatus.getDrawable();
                if (drawable.getColorFilter() == null) {
                    addToFav(currentMeal);

                } else {
                    removeFromFav(currentMeal);
                }
            }
        });
    }

    public void updatefavImageAccordingActullayStatus(String mealId) {
        APPDataBase db = APPDataBase.getInstance(this);
        FavMealsDAO dao = db.getFavMealsDAO();
        LiveData<List<FavMeals>> favourits = dao.getAllFavProducts();
        favourits.observe(this, new Observer<List<FavMeals>>() {
            @Override
            public void onChanged(List<FavMeals> favMealsList) {
                for (FavMeals favMeal : favMealsList) {
                    if (favMeal.getName().equals(mealId)) {
                        Log.d(TAG, "Found a FavMeals object with the desired name: " + favMeal.getName());
                        Drawable drawable = favStatus.getDrawable();
                        drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                        favStatus.setImageDrawable(drawable);
                        isFav = true;
                    } else {
                        Drawable drawable = favStatus.getDrawable();
// Remove the color filter (tint)
                        drawable.setColorFilter(null);
                        favStatus.setImageDrawable(drawable);
                        isFav = false;
                    }
                }
            }
        });
    }

    public void playVideo(String videoUrl) {
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public void addToFav(MealCard favMeal) {
        mealCardPresenterImp.addtoDataBaseFavMeal(favMeal);
    }

    @Override
    public void removeFromFav(MealCard favMeal) {
        mealCardPresenterImp.removeFeomDBfavMeal(favMeal);
    }
}