package com.example.foodplanner.data.network;

import android.util.Log;

import com.example.foodplanner.data.model.MealsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRemoteDataSourceImpl {
    private static final String TAG = "team";
    private static final String BASE_URL = "www.themealdb.com/api/json/v1/1/";
    public ProductRemoteDataSourceImpl() {
    }

    public void makeNetworkCall(NetworkCallback networkCallback) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        MealsService pService = retrofit.create(MealsService.class);
        pService.getProducts().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, " on response + isSuccessful");
                    networkCallback.onSuccessResultForRandom(response.body().randomselection);
                }
            }
            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFauiler ");
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }
}

