package com.example.foodplanner.screens.Card.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;
import com.example.foodplanner.screens.logIn.view.loginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MealCardActivity extends AppCompatActivity implements MealCardView, OnAddORrmoveFavClickListener {
    //    String videoUrl = "https://www.example.com/your_video.mp4";
    public static final String FAV_OBJECT = "FavObject";
    public static final String PLANNED_MEAL = "planned_meal";

    MealCard currentMeal = new MealCard();
    String TAG = "TAG";
    private ImageView mealImage;
    private ImageView favStatus;
    private TextView mealName;
    private TextView country;
    private TextView allSteps;
    private Button addToPlanButton;
    private WebView webView;

    RecyclerView ingS_recyclerView;
    IngredientsAdapter ingredientsAdapter;
    LinearLayoutManager linearLayoutManager;
    private boolean isFav;
    MealCardPresenterImp mealCardPresenterImp;
    Drawable drawable;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    FirebaseAuth auth;
    FirebaseUser user;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount account;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcard);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);

        UI();
        ingredientsAdapter = new IngredientsAdapter(this);
        intent = getIntent();
        mealCardPresenterImp = new MealCardPresenterImp(this, mealsRepository, getBaseContext());
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        /////to get from fav
//        Log.i(TAG, "intent" + intent.hasExtra(FAV_OBJECT));
        FavMeals current = (FavMeals) intent.getSerializableExtra(FAV_OBJECT);
//        Log.i(TAG, "on card" + current);
        if (current != null) {
            mealCardPresenterImp.showThisFavMeal(current);
        }

// get from planned
        PlannedMeals plannedMeal = (PlannedMeals) intent.getSerializableExtra(PLANNED_MEAL);
        if (plannedMeal != null) {
            mealCardPresenterImp.showThisPlannedMeal(plannedMeal);
        }
//to get object from daily insper
        MealCard inspMeal = (MealCard) intent.getSerializableExtra("object");
        if (inspMeal != null) {
            setThisMealAtCard(inspMeal);
        }
// come from list or search by name
        String mealComeFromList = intent.getStringExtra("mealName");
        if (mealComeFromList != null) {
            mealCardPresenterImp.getMealDetailsofThisMeal(mealComeFromList);
        }
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
//        Log.d(TAG, "First: " + mealCard.getName());
        currentMeal = mealCard;
//        Log.d(TAG, "second: " + currentMeal.getName());
        mealName.setText(mealCard.getName());
        country.setText(mealCard.getCountry());
        ingS_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (!mealCard.isFav()) {
//            Log.d(TAG, "getIngr1: " + mealCard.getIngr1() + currentMeal);
//            Log.d(TAG, "mealCard: " + mealCard.getIngr1() + mealCard);

            currentMeal.setAllingredient(mealCardPresenterImp.getIngredients(mealCard));
            ingredientsAdapter.setIngredientsList(mealCardPresenterImp.getIngredients(mealCard));
        } else {
            ingredientsAdapter.setIngredientsList(currentMeal.getAllingredient());
        }
        ingredientsAdapter.notifyDataSetChanged();
        ingS_recyclerView.setAdapter(ingredientsAdapter);
        allSteps.setText(mealCard.getSteps());
        Glide.with(MealCardActivity.this).load(mealCard.getPhotourl()).into(mealImage);
//        Log.d(TAG, "VIDEO URL: " + mealCard.getVideoUrl());
//        updatefavImageAccordingActullayStatus(mealCard.getMealId());
        updatefavImageAccordingActullayStatus(mealCard);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mealCard.getVideoUrl());
    }

    @Override
    public void notGetTheMealDetails(String error) {
        Toast.makeText(MealCardActivity.this, "Sorry, we can not get the meal,check your internet connection and try another meal " , Toast.LENGTH_LONG).show();
    finish();
    }

    public void handlingSetonAction() {
        addToPlanButton.setOnClickListener(view -> {
            if (user == null && account == null) {
                youWantTologIn();
                Toast.makeText(MealCardActivity.this, "Login to add to plan ", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MealCardActivity.this, ChossePlannedDay.class);
                intent.putExtra("myObject", currentMeal);
                startActivity(intent);
            }
        });
        favStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = favStatus.getDrawable();
                if (user == null && account == null) {
                    youWantTologIn();
                    Toast.makeText(MealCardActivity.this, "Login to add to favourite ", Toast.LENGTH_SHORT).show();
                } else {
                    if (isFav) {
                        isFav = false;
                        drawable.setColorFilter(null);
                        favStatus.setImageDrawable(drawable);
                        currentMeal.setFav(false);
                        removeFromFav(currentMeal);
//                    finish();
                    } else {
                        drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                        favStatus.setImageDrawable(drawable);
                        isFav = true;
                        currentMeal.setFav(true);

                        currentMeal.setAllingredient(mealCardPresenterImp.getIngredients(currentMeal));
                        addToFav(currentMeal);
                    }
                }
            }
        });
    }
//|| ! mealCard.isFav()

    public void updatefavImageAccordingActullayStatus(MealCard mealCard) {
        Drawable drawable = favStatus.getDrawable();
        if (user == null && account == null) {
            drawable.setColorFilter(null);
            favStatus.setImageDrawable(drawable);
            isFav = false;
        } else {
            if (mealCard.isFav()) {
                drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                favStatus.setImageDrawable(drawable);
                isFav = true;
            } else {
                drawable.setColorFilter(null);
                favStatus.setImageDrawable(drawable);
                isFav = false;
            }
        }
    }

    @Override
    public void addToFav(MealCard currentMeal) {
        mealCardPresenterImp.addtoDataBaseFavMeal(currentMeal);
    }

    @Override
    public void removeFromFav(MealCard currentMeal) {
        mealCardPresenterImp.removeFeomDBfavMeal(currentMeal);
    }
    void youWantTologIn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MealCardActivity.this);
        builder.setTitle("Hi Chef")
                .setMessage("            let's Login to start cooking?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(), loginActivity.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}