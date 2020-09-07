package com.orangeink.dcodertask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.orangeink.dcodertask.databinding.ActivityMainBinding;
import com.orangeink.dcodertask.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.logoutButton.setOnClickListener(v -> logout());
    }

    private void logout() {
        mainViewModel.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}