package com.example.foodplanner.screens.sharedMainActivity.search.mainSearchScreen.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.View.AreaActivity;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryListActivity;
import com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View.SearchIngretents;

public class FragSearchView extends Fragment {
    EditText etSearchByMeal;
    ImageView searchByAre, searchByCateg, searchByIng;

    public FragSearchView() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);
        etSearchByMeal = view.findViewById(R.id.et_searchByMeal);
        searchByIng = view.findViewById(R.id.img_ingr);
        searchByCateg = view.findViewById(R.id.img_Categories);
        searchByAre = view.findViewById(R.id.img_countery);
        hanlingSetOnClick();
        return view;
    }

    private void hanlingSetOnClick() {
        searchByIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchIngretents.class);
                getContext().startActivity(intent);
            }
        });
        searchByCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryListActivity.class);
                getContext().startActivity(intent);
            }
        });

        searchByAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AreaActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}