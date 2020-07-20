package com.example.loginapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginapp.R;

import java.util.HashMap;


public class SignupFragment extends Fragment {

    private EditText mEditTextUserName, mEditTextPassword;
    private Button mButtonSignUp;
    private View view;
    private Intent mIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getActivity().getIntent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_signup, container, false);
        findAllViews();
        setAllListeners();
        mEditTextUserName.setText(mIntent.getStringExtra(LoginFragment.EXTRA_USER_NAME));
        mEditTextPassword.setText(mIntent.getStringExtra(LoginFragment.EXTRA_PASSWORD));
        return view;
    }

    private void findAllViews() {
        mEditTextUserName = view.findViewById(R.id.editText_signup_username);
        mEditTextPassword = view.findViewById(R.id.editText_signup_password);
        mButtonSignUp = view.findViewById(R.id.button_signup_signup_layout);
    }

    private void setAllListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToLogin();
            }
        });
    }

    private void backToLogin() {
        Intent intent = new Intent();
        intent.putExtra(LoginFragment.EXTRA_USER_NAME, mIntent.getStringExtra(LoginFragment.EXTRA_USER_NAME));
        intent.putExtra(LoginFragment.EXTRA_PASSWORD, mIntent.getStringExtra(LoginFragment.EXTRA_PASSWORD));
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }
}