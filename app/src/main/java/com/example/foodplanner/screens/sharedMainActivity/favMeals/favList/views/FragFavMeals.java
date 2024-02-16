package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter.FavMealsPresnterImp;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FragFavMeals extends Fragment implements FraFavMeals {
    private static final String TAG = "team";
    FavMiniMealsCardAdaptor miniMealsCardAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    List<FavMeals> favMealsList;
    FavMealsPresnterImp presenterImp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_meals, container, false);
        recyclerView = rootView.findViewById(R.id.fav_recycle);

        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(requireContext());
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(requireContext());
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        linearLayoutManager = new LinearLayoutManager(getContext());
        favMealsList = new ArrayList<>();
        miniMealsCardAdaptor = new FavMiniMealsCardAdaptor(getContext(), favMealsList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(miniMealsCardAdaptor);
        Log.i(TAG, "on creation of fav");
        presenterImp = new FavMealsPresnterImp(repository);
        showFavProdcuts();
        return rootView;
    }

    @Override
    public void showFavProdcuts(){
        Flowable<List<FavMeals>> theFav = presenterImp.getStoredFvProduct();
        theFav.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals ->{
                    miniMealsCardAdaptor.setList(meals);
                });
    }
}


