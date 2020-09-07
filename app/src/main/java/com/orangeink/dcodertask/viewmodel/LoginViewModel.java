package com.orangeink.dcodertask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.orangeink.dcodertask.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    public LiveData<FirebaseUser> firebaseUserLiveData;
    public LiveData<Boolean> isResetPasswordMailSent;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
    }

    public void loginSignUp(String email, String password, boolean isLogin) {
        if(isLogin)
            firebaseUserLiveData = loginRepository.login(email, password);
        else
            firebaseUserLiveData = loginRepository.signUp(email, password);
    }

    public void forgotPassword(String email) {
        isResetPasswordMailSent = loginRepository.forgotPassword(email);
    }
}
