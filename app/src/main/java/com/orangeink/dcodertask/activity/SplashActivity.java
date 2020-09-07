package com.orangeink.dcodertask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.orangeink.dcodertask.databinding.ActivitySplashBinding;
import com.orangeink.dcodertask.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        decideNextActivity();
    }

    public void decideNextActivity(){
        splashViewModel.decideNextActivity();
        splashViewModel.isUserLoggedInLiveData.observe(this, isUserLoggedIn -> {
            if(isUserLoggedIn)
                launchActivity(new Intent(this, MainActivity.class));
            else
                launchActivity(new Intent(this, LoginActivity.class));
        });
    }

    private void launchActivity(Intent intent) {
        startActivity(intent);
        finish();
    }
}