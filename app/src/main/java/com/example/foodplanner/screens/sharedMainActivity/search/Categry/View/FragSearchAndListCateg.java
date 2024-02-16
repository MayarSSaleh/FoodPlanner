//package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;
//
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.foodplanner.R;
//import com.example.foodplanner.data.model.Category;
//import java.util.ArrayList;
//import java.util.List;
//
//import io.reactivex.rxjava3.core.Observable;
//
//
//public class FragSearchAndListCateg extends Fragment implements CategoryView {
//
//    CategoryAdapter categoryAdapter;
//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    TextView categoryName;
//
//    public FragSearchAndListCateg() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_frag_search_and_list_categ, container, false);
//        recyclerView = rootView.findViewById(R.id.recycle_categ);
//        categoryName= rootView.findViewById(R.id.et_searchbyCategory);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        return rootView;
//    }
//
//    @Override
//    public void showData(ArrayList<Category> categories) {
//        categoryAdapter = new CategoryAdapter(getContext(), categories, this,categoryMealsView);
//        recyclerView.setAdapter(categoryAdapter);
//
//        Observable<ArrayList<Category> > observable = Observable.fromArray(categories);
//
//        observable.subscribe(list -> {
//            categoryName.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
////                    categoryAdapter.filterCategoriesList(s.toString());
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                }
//            });
//        });
//
//
//    }
//
//    @Override
//    public void showErrMsg(String error) {
//        Toast.makeText(getContext(), "Sorry,we can not load for you this page as: "+ error, Toast.LENGTH_SHORT).show();
//
//    }
//}