package com.example.foodplanner.data.network;

import com.example.foodplanner.data.model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealsService {
    // what i will make in the network ex:  get..post
    // there are response tall you the sucsseful or not.
//    @GET("products")
    // for implment any thing make call ...... get almment i named it

    @GET("randomselection")
    Call<MealsResponse> getProducts();

}
