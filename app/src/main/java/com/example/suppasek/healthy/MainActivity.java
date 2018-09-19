package com.example.suppasek.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore mdb;
    FirebaseFirestoreSettings settings;
    FirebaseAuth userAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAuth = FirebaseAuth.getInstance();
        user = userAuth.getCurrentUser();
//        settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        mdb = FirebaseFirestore.getInstance();

        if(savedInstanceState == null) {
            if(user == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment()).commit();
            }
            else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new MenuFragment()).commit();
            }
        }

    }
}
