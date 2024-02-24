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
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Meal;
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

public class MealCardActivity extends AppCompatActivity implements MealCardView, OnAddORemoveFavClickListener {
    public static final String FAV_OBJECT = "FavObject";
    public static final String PLANNED_MEAL = "planned_meal";
    Meal currentMeal = new Meal();
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
        mealsRepository = MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(),
                new FavouriteLocalDataSource(this), new PlannedLocalDataSourceImpl(this));
        userInterface();
        ingredientsAdapter = new IngredientsAdapter(this);
        intent = getIntent();
        mealCardPresenterImp = new MealCardPresenterImp(this, mealsRepository, getBaseContext());
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        receiveIntentFromAnotherScreens();
        handlingSetonAction();
    }

    public void userInterface() {
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

    public void receiveIntentFromAnotherScreens() {
        /////to get from fav
        Meal current = (Meal) intent.getSerializableExtra(FAV_OBJECT);
        if (current != null) {
            setThisMealAtCard(current);
        }
// get from planned
        PlannedMeals plannedMeal = (PlannedMeals) intent.getSerializableExtra(PLANNED_MEAL);
        if (plannedMeal != null) {
            setThisMealAtCard(plannedMeal.getPlannedMeal());
        }
//to get object from daily insper
        Meal inspMeal = (Meal) intent.getSerializableExtra("object");
        if (inspMeal != null) {
            setThisMealAtCard(inspMeal);
        }
// come from list or search by name
        String mealComeFromList = intent.getStringExtra("mealName");
        if (mealComeFromList != null) {
            mealCardPresenterImp.getMealDetailsOfThisMeal(mealComeFromList);

        }
    }

    @Override
    public void setThisMealAtCard(Meal mealCard) {
        currentMeal = mealCard;
        mealName.setText(mealCard.getName());
        country.setText(mealCard.getCountry());
        ingS_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (!mealCard.isFav()) {
            currentMeal.setAllIngredient(mealCardPresenterImp.getIngredients(mealCard));
            ingredientsAdapter.setIngredientsList(mealCardPresenterImp.getIngredients(mealCard));
        } else {
            ingredientsAdapter.setIngredientsList(currentMeal.getAllIngredient());
        }
        ingredientsAdapter.notifyDataSetChanged();
        ingS_recyclerView.setAdapter(ingredientsAdapter);
        allSteps.setText(mealCard.getSteps());
        Glide.with(MealCardActivity.this).load(mealCard.getPhotourl()).into(mealImage);
        updateFavImageAccordingActuallyStatus(mealCard);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mealCard.getVideoUrl());
    }

    @Override
    public void notGetTheMealDetails(String error) {
        Toast.makeText(MealCardActivity.this, "Sorry, we can not get the meal,check your internet connection and try another meal ", Toast.LENGTH_LONG).show();
        finish();
    }

    public void handlingSetonAction() {
        addToPlanButton.setOnClickListener(view -> {
            if (user == null && account == null) {
                youWantTologin();
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
                    youWantTologin();
                    Toast.makeText(MealCardActivity.this, "Login to add to favourite ", Toast.LENGTH_SHORT).show();
                } else {
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
                        currentMeal.setAllIngredient(mealCardPresenterImp.getIngredients(currentMeal));
                        addToFav(currentMeal);
                    }
                }
            }
        });
    }

    public void updateFavImageAccordingActuallyStatus(Meal mealCard) {
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

    public void youWantTologin() {
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

    @Override
    public void addToFav(Meal currentMeal) {
        mealCardPresenterImp.addToDataBaseFavMeal(currentMeal);
    }

    @Override
    public void removeFromFav(Meal currentMeal) {
        mealCardPresenterImp.removeFromDBFavMeal(currentMeal);
    }

}