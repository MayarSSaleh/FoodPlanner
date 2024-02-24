package com.example.foodplanner.screens.sharedMainActivity.dailyInspirationMeals.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.view.MealCardActivity;
import com.example.foodplanner.screens.sharedMainActivity.dailyInspirationMeals.presenter.InsperMealsPresenterImp;

public class DailyInspFragment extends Fragment implements InsperMealsView {
    LinearLayoutManager linearLayoutManager;
    InsperMealsPresenterImp insperMealsPresenter;
    TextView mealName;
    ImageView mealImage;
    Meal dailyInsperationMeal;

    public DailyInspFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daily_insperation, container, false);
        mealName = rootView.findViewById(R.id.tv_meal_name_daily);
        mealImage = rootView.findViewById(R.id.img_mealDaily);
        insperMealsPresenter = new InsperMealsPresenterImp(MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(),
                new FavouriteLocalDataSource(getContext()), new PlannedLocalDataSourceImpl(getContext())), DailyInspFragment.this);
        insperMealsPresenter.getAllProducts();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MealCardActivity.class);
                intent.putExtra("object", dailyInsperationMeal);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(Meal mealCard) {
        if (getActivity() == null) return;
        mealName.setText(mealCard.getName());
        dailyInsperationMeal = mealCard;
        Glide.with(DailyInspFragment.this).load(mealCard.getPhotourl()).into(mealImage);
    }

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(requireContext(), "Please Reconnect With Internet", Toast.LENGTH_LONG).show();
    }
}
