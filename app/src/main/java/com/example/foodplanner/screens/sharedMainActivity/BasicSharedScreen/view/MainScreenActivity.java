package com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    boolean isGuestInMainScreenActivty;


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
        isGuestInMainScreenActivty = mainScreenPresenterImp.isguest();
        ui();
        if (!isGuestInMainScreenActivty){
            if(user!= null){tv_userEmail.setText(user.getEmail()); }
            else  tv_userEmail.setText(account.getDisplayName());
        }
        handleFragmentChoose();
        setOnAction();
    }

    private void ui() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPageAdapter = new MyViewPageAdapter(this, isGuestInMainScreenActivty, MainScreenActivity.this);
        viewPager2.setAdapter(myViewPageAdapter);
        profile = findViewById(R.id.profile);
        tv_userEmail = findViewById(R.id.tv_userEmail);
        setTabsIcons();
    }

    private void setTabsIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_manage_search_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.plan);
        tabLayout.getTabAt(3).setIcon(R.drawable.fav_icon);
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

    void setOnAction(){
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
    void youWantlogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreenActivity.this);
        builder.setTitle("Hi chef")
                .setMessage("Are you sure you want log out ?!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mainScreenPresenterImp.logOut(googleSignInClient);
                        Intent i = new Intent(getApplicationContext(), loginActivity.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void youWantlogIn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainScreenActivity.this);
        builder.setTitle("Hi Chef")
                .setMessage("          let's Login to start cooking")
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