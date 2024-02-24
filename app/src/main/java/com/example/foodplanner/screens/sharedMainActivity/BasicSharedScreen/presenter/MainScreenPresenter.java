package com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface MainScreenPresenter {

    boolean isguest();

    void saveAcount();

    boolean logOut(GoogleSignInClient googleSignInClient);
}
