package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;

import com.example.foodplanner.data.model.Category;

import java.util.ArrayList;

public interface CategoryView {
    public void showData (ArrayList<Category> categories);
    public void showErrMsg(String error);
}
