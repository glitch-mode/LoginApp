package com.example.loginapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.loginapp.Fragments.LoginFragment;
import com.example.loginapp.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager
                    .beginTransaction()
                    .add(R.id.login_container_fragment, new LoginFragment())
                    .commit();

    }
}