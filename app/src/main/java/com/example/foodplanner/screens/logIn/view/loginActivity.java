package com.example.foodplanner.screens.logIn.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.basicSharedScreen.view.MainScreenActivity;
import com.example.foodplanner.screens.signUp.view.signUPActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class loginActivity extends AppCompatActivity {

    Button btnSignUp;
    Button btnGoogle;
    TextView tvLogin, tv_cont_as_guest;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acount_screen);
        btnGoogle = findViewById(R.id.btn_google);
        btnSignUp = findViewById(R.id.btn_signUp);
        tvLogin = findViewById(R.id.tv_login);
        tv_cont_as_guest = findViewById(R.id.tv_cont_as_guest);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(loginActivity.this, googleSignInOptions);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(this);


        tv_cont_as_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "as GUEST you can not add to favourite or make a plan",  Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(loginActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, loginEnterUser.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, signUPActivity.class);
                startActivity(intent);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInTntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInTntent, 1000);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent intent = new Intent(loginActivity.this, MainScreenActivity.class);
                startActivity(intent);
                finish();
            } catch (ApiException e) {
//                e.printStackTrace();
                Toast.makeText(loginActivity.this, "Sorry can not sign in, there are something wrong", Toast.LENGTH_SHORT);
            }
        }
    }
}
