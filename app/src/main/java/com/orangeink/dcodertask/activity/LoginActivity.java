package com.orangeink.dcodertask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.orangeink.dcodertask.R;
import com.orangeink.dcodertask.databinding.ActivityLoginBinding;
import com.orangeink.dcodertask.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toggleText.setOnClickListener(v -> toggleLoginSignUp());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.loginButton.setOnClickListener(v -> loginSignUp());
        binding.forgotPassword.setOnClickListener(v -> forgotPassword());
    }

    private void forgotPassword() {
        binding.loginProgress.setVisibility(View.VISIBLE);
        hideKeyboard(this);
        if(binding.emailEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the email and click on forgot password to receive reset password mail!", Toast.LENGTH_SHORT).show();
            binding.loginProgress.setVisibility(View.GONE);
            return;
        }
        loginViewModel.forgotPassword(binding.emailEditText.getText().toString());
        loginViewModel.isResetPasswordMailSent.observe(this, isSent -> {
            binding.loginProgress.setVisibility(View.GONE);
            if(isSent)
                Toast.makeText(this, "Reset password mail sent!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loginSignUp() {
        hideKeyboard(this);
        if (binding.loginButton.getText().toString().equals(getString(R.string.sign_in))) {
            if (binding.emailEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Email field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.passwordEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            binding.loginProgress.setVisibility(View.VISIBLE);
            loginViewModel.loginSignUp(binding.emailEditText.getText().toString(), binding.passwordEditText.getText().toString(), true);
        } else {
            if (binding.emailEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Email field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.passwordEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.confirmPasswordEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Confirm Password field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (binding.nameEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Name field cannot be empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!binding.confirmPasswordEditText.getText().toString().equals(binding.passwordEditText.getText().toString())){
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                return;
            }
            binding.loginProgress.setVisibility(View.VISIBLE);
            loginViewModel.loginSignUp(binding.emailEditText.getText().toString(), binding.passwordEditText.getText().toString(), false);
        }
        loginViewModel.firebaseUserLiveData.observe(this, this::gotoNextActivity);
    }

    private void gotoNextActivity(FirebaseUser user) {
        binding.loginProgress.setVisibility(View.GONE);
        if(user != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else
            Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
    }

    private void toggleLoginSignUp() {
        if (binding.toggleText.getText().toString().equals(getString(R.string.don_t_have_an_account_signup))) {
            binding.nameEditText.setVisibility(View.VISIBLE);
            binding.confirmPasswordEditText.setVisibility(View.VISIBLE);
            binding.forgotPassword.setVisibility(View.GONE);
            binding.loginButton.setText(R.string.sign_up);
            binding.toggleText.setText(R.string.already_have_an_account_signin);
            binding.welcomeText.setText(R.string.signup_welcome);
        } else {
            binding.nameEditText.setVisibility(View.GONE);
            binding.confirmPasswordEditText.setVisibility(View.GONE);
            binding.forgotPassword.setVisibility(View.VISIBLE);
            binding.loginButton.setText(R.string.sign_in);
            binding.toggleText.setText(R.string.don_t_have_an_account_signup);
            binding.welcomeText.setText(R.string.let_s_start_with_login);
        }
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null)
            view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}