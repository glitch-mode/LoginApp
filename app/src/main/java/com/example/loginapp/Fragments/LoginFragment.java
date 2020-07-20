package com.example.loginapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginapp.Controller.LoginActivity;
import com.example.loginapp.Controller.SignupActivity;
import com.example.loginapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class LoginFragment extends Fragment {
    private EditText mEditTextUserName, mEditTextPassword;
    private Button mButtonLogin, mButtonSignUp;
    private HashMap<String, String> mUsers = new HashMap<>();
    public static final String EXTRA_USER_NAME = "username";
    public static final String EXTRA_PASSWORD = "password";
    public static final int LOGIN_REQUEST_CODE = 1;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mUsers = (HashMap<String, String>) savedInstanceState.getSerializable("HashMap");
            mEditTextPassword.setText(savedInstanceState.getString("Password"));
            mEditTextUserName.setText(savedInstanceState.getString("Username"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("HashMap", mUsers);
        outState.putString("Username", mEditTextUserName.getText().toString());
        outState.putString("Password", mEditTextPassword.getText().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        findAllViews();
        setAllListeners();
        return view;
    }

    private void findAllViews() {
        mEditTextUserName = view.findViewById(R.id.editText_login_username);
        mEditTextPassword = view.findViewById(R.id.editText_login_password);
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonSignUp = view.findViewById(R.id.button_signup);
    }

    private void setAllListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                intent.putExtra(EXTRA_USER_NAME, mEditTextUserName.getText().toString());
                intent.putExtra(EXTRA_PASSWORD, mEditTextPassword.getText().toString());
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mUsers.get(mEditTextUserName.getText().toString())
                            .equals(mEditTextPassword.getText().toString())) {
                        Snackbar.make(view, "Login was successful!", Snackbar.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException n) {
                    Snackbar.make(view, "You need to sign up first", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) return;
        if (requestCode == LOGIN_REQUEST_CODE) {
            mUsers.put(data.getStringExtra(EXTRA_USER_NAME), data.getStringExtra(EXTRA_PASSWORD));
        }
    }
}