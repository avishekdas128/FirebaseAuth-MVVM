package com.orangeink.dcodertask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.orangeink.dcodertask.repository.MainRepository;

public class MainViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository();
    }

    public void logout() {
        mainRepository.logout();
    }
}
