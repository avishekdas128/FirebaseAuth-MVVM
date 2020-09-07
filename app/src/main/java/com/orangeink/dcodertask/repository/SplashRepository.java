package com.orangeink.dcodertask.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public MutableLiveData<Boolean> decideNextActivity() {
        MutableLiveData<Boolean> isUserLoggedIn = new MutableLiveData<>();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user == null)
            isUserLoggedIn.setValue(false);
        else
            isUserLoggedIn.setValue(true);
        return isUserLoggedIn;
    }
}
