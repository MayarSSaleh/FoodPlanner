package com.example.foodplanner.screens.sharedMainActivity.search.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.foodplanner.R;

public class FragSearchView extends Fragment {
    EditText etSearchByMeal;
    ImageView searchByAre, searchByCateg, searchByIng;
    FrameLayout frameLayout;

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
        frameLayout=view.findViewById(R.id.fragmentAboveSearch);


        hanlingSetOnClick();
        return view;
    }

    private void hanlingSetOnClick() {
        searchByIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentAboveSearch, new FragSearchAndListCateg()).commit();

//                navController.navigate(R.id.view_pager);
//                NavController navController = Navigation.findNavController(requireActivity(), R.id.view_pager);
            }
        });
        searchByCateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentAboveSearch, new FragSearchAndListCateg()).commit();

//                Intent intent = new Intent(getContext(),FragSearchAndListCateg.class);
//                getContext().startActivity(intent);

            }
        });

        searchByAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentAboveSearch, new FragSearchAndListCateg()).commit();

                //                Intent intent = new Intent(getContext(),SearchAndListArea.class);
//                getContext().startActivity(intent);
            }
        });
    }
}