package com.example.foodplanner.screens.logIn.view;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.BasicSharedScreen.view.MainScreenActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginEnterUser extends AppCompatActivity {

    EditText tv_email;
    EditText tv_pass;
    Button enter;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(loginEnterUser.this, MainScreenActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        tv_email = findViewById(R.id.ed_user_email);
        tv_pass = findViewById(R.id.ed_user_pass);
        enter = findViewById(R.id.btn_startCooking_atSignUp);
        progressBar = findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = tv_email.getText().toString().trim();
                password = tv_pass.getText().toString().trim();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Chef,Enter your Email and Password", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
//                                    SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = preferences.edit();
//                                    editor.putString("userEmail", email);
//                                    editor.apply();

                                    Intent intent = new Intent(loginEnterUser.this, MainScreenActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Exception exception = task.getException();
                                    if (exception != null) {
                                        String errorMessage = exception.getMessage();
                                        Toast.makeText(getApplicationContext(), "Authentication failed as " + errorMessage,
                                                Toast.LENGTH_SHORT).show();
                                        Log.d("TAG", "Authentication failed: " + errorMessage);
                                    } else {  // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "Authentication failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
