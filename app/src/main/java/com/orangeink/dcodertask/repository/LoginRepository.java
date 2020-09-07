package com.orangeink.dcodertask.repository;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public MutableLiveData<FirebaseUser> login(String email, String password) {
        MutableLiveData<FirebaseUser> firebaseUser = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful())
                firebaseUser.setValue(firebaseAuth.getCurrentUser());
            else
                firebaseUser.setValue(null);
        });
        return firebaseUser;
    }

    public MutableLiveData<FirebaseUser> signUp(String email, String password) {
        MutableLiveData<FirebaseUser> firebaseUser = new MutableLiveData<>();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful())
                firebaseUser.setValue(firebaseAuth.getCurrentUser());
            else
                firebaseUser.setValue(null);
        });
        return firebaseUser;
    }

    public MutableLiveData<Boolean> forgotPassword(String email) {
        MutableLiveData<Boolean> isResetPasswordMailSent = new MutableLiveData<>();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> isResetPasswordMailSent.setValue(task.isSuccessful()));
        return isResetPasswordMailSent;
    }

}
