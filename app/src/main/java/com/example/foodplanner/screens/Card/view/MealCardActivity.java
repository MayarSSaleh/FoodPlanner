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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcard);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        UI();
        ingredientsAdapter = new IngredientsAdapter(this);
        /////to get from fav
        Intent intent = getIntent();
        Log.i(TAG, "intent" + intent.hasExtra(FAV_OBJECT));
        FavMeals current = (FavMeals) intent.getSerializableExtra(FAV_OBJECT);
        Log.i(TAG, "on card" + current);
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);

        mealCardPresenterImp = new MealCardPresenterImp(this, mealsRepository, getBaseContext());
        if (current != null) {
            mealCardPresenterImp.showThisFavMeal(current);
        }

// get from planned
        PlannedMeals plannedMeal = (PlannedMeals) intent.getSerializableExtra(PLANNED_MEAL);
        if (plannedMeal != null) {
            mealCardPresenterImp.showThisPlannedMeal(plannedMeal);
        }


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //to get object from daily insper
        Intent intent1 = getIntent();
        MealCard inspMeal = (MealCard) intent1.getSerializableExtra("object");
        if (inspMeal != null) {
            setThisMealAtCard(inspMeal);
        }

        Intent comeFromList = getIntent();
        MealCard mealComeFromList = (MealCard) intent1.getSerializableExtra("mealComeFromList");
        if (mealComeFromList != null) {
            mealCardPresenterImp.getMealDetailsofThisMeal(mealComeFromList.getName());
            Toast.makeText(this, "Getting your meals", Toast.LENGTH_SHORT).show();
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
        Log.d(TAG, "First: " + mealCard.getName());
        currentMeal = mealCard;
        Log.d(TAG, "second: " + currentMeal.getName());
        mealName.setText(mealCard.getName());
        country.setText(mealCard.getCountry());
        ingS_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (!mealCard.isFav()) {
            Log.d(TAG, "getIngr1: " + mealCard.getIngr1() + currentMeal);
            Log.d(TAG, "mealCard: " + mealCard.getIngr1() + mealCard);

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
        updatefavImageAccordingActullayStatus(mealCard.getMealId());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(mealCard.getVideoUrl());
    }

    public void handlingSetonAction() {
        addToPlanButton.setOnClickListener(view -> {
            if (user == null && account == null) {
                Toast.makeText(MealCardActivity.this, "Login to add to plan ", Toast.LENGTH_SHORT).show();
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


    public void updatefavImageAccordingActullayStatus(String mealId) {
        APPDataBase db = APPDataBase.getInstance(this);
        FavMealsDAO dao = db.getFavMealsDAO();
        Flowable<List<FavMeals>> favourits = dao.getAllFavProducts();

        favourits.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favMealsList -> {
                    for (FavMeals favMeal : favMealsList) {
                        Drawable drawable = favStatus.getDrawable();
                        if (user == null && account == null) {
                            Toast.makeText(MealCardActivity.this, "Login to add to favourite ", Toast.LENGTH_SHORT).show();
                        } else {

                            if (favMeal.getMealId().equals(mealId)) {
                                drawable.setColorFilter(ContextCompat.getColor(MealCardActivity.this, com.google.android.material.R.color.design_dark_default_color_error), PorterDuff.Mode.SRC_IN);
                                favStatus.setImageDrawable(drawable);
                                isFav = true;
                            } else {
// Remove the color filter (tint)
                                drawable.setColorFilter(null);
                                favStatus.setImageDrawable(drawable);
                                isFav = false;
//                        Log.d("hhhhh", "else " + mealId + "         " + favMeal.getMealId());
                            }
                        }
                    }
                });
    }

    @Override
    public void addToFav(MealCard currentMeal) {
        mealCardPresenterImp.addtoDataBaseFavMeal(currentMeal);
    }

    @Override
    public void removeFromFav(MealCard currentMeal) {
        mealCardPresenterImp.removeFeomDBfavMeal(currentMeal);
    }

}