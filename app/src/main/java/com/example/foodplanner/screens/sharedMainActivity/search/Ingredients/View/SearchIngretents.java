package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;

public class SearchIngretents extends AppCompatActivity {


    ProgressBar progressBar;
    public SearchIngretents() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_ingre);
        progressBar=findViewById(R.id.progressBar_at_searchIng);




    }
}