package com.example.foodplanner.screens.Card.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;

import java.util.List;

public class MealCardActivity extends AppCompatActivity implements MealCardView, OnAddORrmoveFavClickListener {
    //    String videoUrl = "https://www.example.com/your_video.mp4";
    public static final String FAV_OBJECT = "FavObject";
    MealCard currentMeal = new MealCard();
    String TAG = "TAG";
    private ImageView mealImage;
    private ImageView favStatus;
    private TextView mealName;
    private TextView country;
    private TextView allIngredients;
    private TextView alling_amount;
    private TextView allSteps;
    private Button addToPlanButton;
    private WebView webView;
    VideoView videoView;
    RecyclerView ingS_recyclerView;
    IngredientsAdapter ingredientsAdapter;
    LinearLayoutManager linearLayoutManager;
    private boolean isFav;
    MealCardPresenterImp mealCardPresenterImp;
    Drawable drawable;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcard);
        UI();
        Intent intent = getIntent();
        Log.i(TAG, "intent" + intent.hasExtra(FAV_OBJECT));
        FavMeals current = (FavMeals) intent.getSerializableExtra(FAV_OBJECT);
        Log.i(TAG, "on card" + current);
        mealCardPresenterImp = new MealCardPresenterImp(this, mealsRepository);
        if (current != null) {
            mealCardPresenterImp.showThisFavMeal(current);
        }
        linearLayoutManager = new LinearLayoutManager(this);
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSourceImpl(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        handlingSetonAction();
    }

    public void UI() {
        webView = findViewById(R.id.web_view);
        mealImage = findViewById(R.id.img_meal);
        mealName = findViewById(R.id.tv_mealName);
        country = findViewById(R.id.tv_country);
        ingS_recyclerView = findViewById(R.id.recyclerView_ing);
        allSteps = findViewById(R.id.steps);
        favStatus = findViewById(R.id.img_fav);
        drawable = favStatus.getDrawable();
        addToPlanButton = findViewById(R.id.btn_add_to_plan);

    }

    @Override
    public void setThisMealAtCard(MealCard mealCard) {
        Log.d(TAG, "First: " + mealCard.getName());
        currentMeal = mealCard;
        Log.d(TAG, "second: " + currentMeal.getName());
        mealName.setText(mealCard.getName());
        country.setText(mealCard.getCountry());
        ingredientsAdapter = new IngredientsAdapter(MealCardActivity.this, mealCard.getAllingredient());
        ingS_recyclerView.setLayoutManager(linearLayoutManager);
        ingS_recyclerView.setAdapter(ingredientsAdapter);
        Log.d(TAG, "s" + mealCard.getSteps());

        allSteps.setText(mealCard.getSteps());
        Glide.with(MealCardActivity.this).load(mealCard.getPhotourl()).into(mealImage);
        Log.d(TAG, "VIDEO URL: " + mealCard.getVideoUrl());
        updatefavImageAccordingActullayStatus(mealCard.getName());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mealCard.getVideoUrl());
//        playVideo(mealCard.getVideoUrl());
    }

    public void handlingSetonAction() {
        addToPlanButton.setOnClickListener(view -> {
            Intent intent = new Intent(MealCardActivity.this, ChossePlannedDay.class);
            intent.putExtra("myObject", currentMeal);
            startActivity(intent);
        });
        favStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = favStatus.getDrawable();
//                if (drawable.getColorFilter() == null) {
                if (isFav) {
                    isFav = false;
                    drawable.setColorFilter(null);
                    favStatus.setImageDrawable(drawable);
                    currentMeal.setFav(false);
                    removeFromFav(currentMeal);
                } else {
                    drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                    favStatus.setImageDrawable(drawable);
                    isFav = true;
                    currentMeal.setFav(true);
                    addToFav(currentMeal);
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
                    Drawable drawable = favStatus.getDrawable();
                    if (favMeal.getName().equals(mealId)) {
                        Log.d(TAG, "Found a FavMeals object with the desired name: " + favMeal.getName());
                        drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                        favStatus.setImageDrawable(drawable);
                        isFav = true;
                    } else {
// Remove the color filter (tint)
                        drawable.setColorFilter(null);
                        favStatus.setImageDrawable(drawable);
                        isFav = false;
                    }
                }
            }
        });
    }

    //
//    public void playVideo(String videoUrl) {
//        Uri uri = Uri.parse(videoUrl);
//        videoView.setVideoURI(uri);
//        videoView.start();
//    }
//    public void playVideo(String videoUrl) {
//        // Extract the video ID from the YouTube video URL
//        String videoId = extractVideoId(videoUrl);
//        // Construct the embed URL with the video ID
//        String embedUrl = "https://www.youtube.com/embed/" + videoId;
//        // Enable JavaScript (optional, depends on the content of the web page)
//        webView.getSettings().setJavaScriptEnabled(true);
//        // Load the YouTube video using the embed URL
//        webView.loadUrl(embedUrl);
//    }
//    private String extractVideoId(String videoUrl) {
//        if (videoUrl == null) {
//            return null;
//        }
//        Uri uri = Uri.parse(videoUrl);
//        String videoId = uri.getQueryParameter("v");
//        return videoId;
//    }



    @Override
    public void addToFav(MealCard currentMeal) {
        mealCardPresenterImp.addtoDataBaseFavMeal(currentMeal);
    }

    @Override
    public void removeFromFav(MealCard currentMeal) {
        mealCardPresenterImp.removeFeomDBfavMeal(currentMeal);
    }

}