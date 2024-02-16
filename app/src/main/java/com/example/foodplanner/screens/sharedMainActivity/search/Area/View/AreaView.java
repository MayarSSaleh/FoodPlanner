package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;


import com.example.foodplanner.data.model.Area;

import java.util.ArrayList;

public interface AreaView {
    public void showData (ArrayList<Area> areaArrayList);
    public void showErrMsg(String error);
}
