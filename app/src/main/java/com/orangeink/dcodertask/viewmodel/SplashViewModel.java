package com.orangeink.dcodertask.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.orangeink.dcodertask.repository.SplashRepository;

public class SplashViewModel extends AndroidViewModel {

    private SplashRepository splashRepository;
    public LiveData<Boolean> isUserLoggedInLiveData;

    public SplashViewModel(Application application) {
        super(application);
        splashRepository = new SplashRepository();
    }

    public void decideNextActivity() {
        isUserLoggedInLiveData = splashRepository.decideNextActivity();
    }
}
