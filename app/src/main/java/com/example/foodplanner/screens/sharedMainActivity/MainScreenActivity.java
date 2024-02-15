package com.example.foodplanner.screens.sharedMainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.logIn.view.loginEnterUser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainScreenActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPageAdapter myViewPageAdapter;
    ImageView profile;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView tv_userEmail;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ui();
        handleFragmentChoose();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
         account = GoogleSignIn.getLastSignedInAccount(this);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            tv_userEmail.setText(user.getEmail());
            Toast.makeText(MainScreenActivity.this, "Welcome back Chef", Toast.LENGTH_SHORT).show();
        }
        if (account != null) {
            tv_userEmail.setText(account.getDisplayName());
            Toast.makeText(MainScreenActivity.this, "Welcome back Chef", Toast.LENGTH_SHORT).show();
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null || account != null) {
                    youWantlogout();
                } else
                    Toast.makeText(MainScreenActivity.this, "You are a guest, Not have a profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ui() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPageAdapter = new MyViewPageAdapter(this, user == null, MainScreenActivity.this);
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
                        if (user != null) {
                            FirebaseAuth.getInstance().signOut();
                        }
                        if (account != null) {
                           googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                               }
                           });
                        }

                        Intent i = new Intent(getApplicationContext(), loginEnterUser.class);
                        startActivity(i);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}