package com.example.foodplanner.screens.Card.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodplanner.R;

public class fraChooseTheDayActivity extends Fragment {


    public fraChooseTheDayActivity() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setonaction{
//            Toast.makeText(getContext(), "The meal added to the plan", Toast.LENGTH_LONG).show();
// send to interface to add in database
//
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fra_choose_the_day, container, false);
    }
}