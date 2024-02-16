package com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.logIn.view.loginActivity;
import com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.presenter.MainScreenPresenterImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainScreenActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView tv_userEmail;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPageAdapter myViewPageAdapter;
    ImageView profile;
    FirebaseAuth auth;
    FirebaseUser user;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount account;
    MainScreenPresenterImp mainScreenPresenterImp;
    boolean isGuestInActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mainScreenPresenterImp = new MainScreenPresenterImp(MainScreenActivity.this, user, account);
        isGuestInActivity = mainScreenPresenterImp.isguest();
        ui();
        handleFragmentChoose();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null || account != null) {
                    youWantlogout();
                } else {
                    youWantlogIn();
                }
            }
        });
    }

    private void ui() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
//        >>>>>>>>>>>>>>>>>>>>>>.... it not work correctly
        myViewPageAdapter = new MyViewPageAdapter(this, isGuestInActivity, MainScreenActivity.this);

        viewPager2.setAdapter(myViewPageAdapter);
        profile = findViewById(R.id.profile);
        tv_userEmail = findViewById(R.id.tv_userEmail);
    }

    private void handleFragmentChoose() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // to connect the tablayout in the body view with the taps
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    void youWantlogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreenActivity.this);
        builder.setTitle("Alert Dialog")
                .setMessage("Are you sure you want log out?!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mainScreenPresenterImp.logOut(googleSignInClient);
//                        if (user != null) {
//                            FirebaseAuth.getInstance().signOut();
//                        }
//                        if (account != null) {
//                            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                }
//                            });
//                        }
//
//                       sharedPreferences = getSharedPreferences(SHARED_PREFS, 0);
//                       editor = sharedPreferences.edit();
//                        editor.putString("email", "");
//                        editor.apply();
//
                        Intent i = new Intent(getApplicationContext(), loginActivity.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    void youWantlogIn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreenActivity.this);
        builder.setTitle("Alert Dialog")
                .setMessage(" NOW, you are a guest,Do you want to Login?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(), loginActivity.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}