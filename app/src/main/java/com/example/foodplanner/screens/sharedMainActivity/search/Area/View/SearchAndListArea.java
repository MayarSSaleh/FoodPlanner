//package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.foodplanner.R;
//import com.example.foodplanner.data.model.Area;
//import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryAdapter;
//
//import java.util.ArrayList;
//
//
//public class SearchAndListArea extends Fragment implements AreaView {
//    AreaAdapter areaAdapter;
//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    TextView areaName;
//
//    public SearchAndListArea() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_search_and_list_area, container, false);
//        recyclerView = rootView.findViewById(R.id.recycle_areas);
//        areaName = rootView.findViewById(R.id.ed_areaNameinSearch);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        return rootView;
//    }
//
//    @Override
//    public void showData(ArrayList<Area> areaArrayList) {
//        areaAdapter = new AreaAdapter(getContext(),areaArrayList);
//        recyclerView.setAdapter(areaAdapter);
//    }
//
//    @Override
//    public void showErrMsg(String error) {
//        Toast.makeText(getContext(), "Sorry,we can not load for you this page as: "+ error, Toast.LENGTH_SHORT).show();
//    }
//}