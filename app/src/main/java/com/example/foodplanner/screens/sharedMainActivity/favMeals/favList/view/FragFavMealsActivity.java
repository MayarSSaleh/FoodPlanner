package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter.FavMealsPresnterImp;

import java.util.ArrayList;
import java.util.List;

public class FragFavMealsActivity extends Fragment implements FavMealsView {
    private static final String TAG = "team";
    FavMiniMealsCardAdaptor miniMealsCardAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;

    List<FavMeals> favMealsList;
    FavMealsPresnterImp presenterImp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_fav_meals, container, false);
        recyclerView = rootView.findViewById(R.id.fav_recycle);
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        linearLayoutManager = new LinearLayoutManager(getContext());
// can not as we are in fragment not activity
//        linearLayoutManager = new LinearLayoutManager(this);
        favMealsList = new ArrayList<>();
        miniMealsCardAdaptor = new FavMiniMealsCardAdaptor(getContext(), favMealsList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(miniMealsCardAdaptor);
        Log.i(TAG, "on creation of fav");
//        Since FragFavMealsActivity implements FavMealsView, you can simply pass this as the first argument to the constructor, as the fragment itself serves as the view:
        presenterImp = new FavMealsPresnterImp(this, repository);

//1. Wrap your data in a LiveData
        APPDataBase db = APPDataBase.getInstance(getContext());
        FavMealsDAO dao = db.getFavMealsDAO();
        LiveData<List<FavMeals>> Favourits = dao.getAllFavProducts();
//        3. Attach the Observer object to the LiveData object using observe()
//        By using getViewLifecycleOwner(), you ensure that the observer is removed when the Fragment's view is destroyed, preventing any potential memory leaks.
//
//Additionally, ensure that your FavMealsPresnterImp class properly handles the lifecycle of the view interface (FavMealsView)
// to avoid potential memory leaks. Typically, this involves clearing any references to the view interface when the Fragment's onDestroyView()
// method is called.
        Favourits.observe(getViewLifecycleOwner(), new Observer<List<FavMeals>>() {//                2. Create an Observer object that defines the onChanged().
            @Override
            public void onChanged(List<FavMeals> products) {
                Log.i(TAG, "onChanged: in fav activity");
                miniMealsCardAdaptor.setList(products);
            }
        });
        return rootView;
    }
//
//    @Override
//    public void removeFromFav(FavMeals favMeal) {
//        presenterImp.delete(favMeal);
//    }
}

