package com.orangeink.dcodertask.repository;

import com.google.firebase.auth.FirebaseAuth;

public class MainRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public void logout() {
        firebaseAuth.signOut();
    }
}
