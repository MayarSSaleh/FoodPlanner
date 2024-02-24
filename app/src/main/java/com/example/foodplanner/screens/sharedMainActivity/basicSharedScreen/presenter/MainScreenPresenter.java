package com.example.foodplanner.screens.sharedMainActivity.basicSharedScreen.presenter;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface MainScreenPresenter {

    boolean isGuest();

    boolean logOut(GoogleSignInClient googleSignInClient);
}
