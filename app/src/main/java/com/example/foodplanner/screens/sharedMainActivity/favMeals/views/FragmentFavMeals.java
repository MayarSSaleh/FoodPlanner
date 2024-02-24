package com.example.foodplanner.screens.sharedMainActivity.favMeals.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.presenter.FaveMealsPresenterImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FragmentFavMeals extends Fragment implements FraFavMeals {
    FavMiniMealsCardAdaptor miniMealsCardAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl repository;
    List<Meal> favMealsList;
    FaveMealsPresenterImp presenterImp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_meals, container, false);
        recyclerView = rootView.findViewById(R.id.fav_recycle);
        repository = MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(), new FavouriteLocalDataSource(requireContext()), new PlannedLocalDataSourceImpl(requireContext()));
        linearLayoutManager = new LinearLayoutManager(getContext());
        favMealsList = new ArrayList<>();
        miniMealsCardAdaptor = new FavMiniMealsCardAdaptor(getContext(), favMealsList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(miniMealsCardAdaptor);
        presenterImp = new FaveMealsPresenterImp(repository);
        showFavProdcuts();
        return rootView;
    }

    @Override
    public void showFavProdcuts() {
        Flowable<List<Meal>> storedFvProduct = presenterImp.getStoredFvProduct();
        storedFvProduct.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if (!meals.isEmpty()) {
                        miniMealsCardAdaptor.setList(meals);
                        miniMealsCardAdaptor.notifyDataSetChanged();
                    }
                });
    }
}


