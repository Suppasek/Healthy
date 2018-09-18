package com.example.suppasek.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    FirebaseAuth userAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        initLoginBtn();
        initRegisterBtn();

    }

    void initLoginBtn() {
        userAuth = FirebaseAuth.getInstance();
        Button loginbtn = getView().findViewById(R.id.login_loginbn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userId =  getView().findViewById(R.id.login_userid);
                EditText password =  getView().findViewById(R.id.login_password);
                String userIdStr = userId.getText().toString();
                String passwordStr = password.getText().toString();
                if (userIdStr.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาระบุ user or password", Toast.LENGTH_SHORT).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                } else {

                    userAuth.signInWithEmailAndPassword(userIdStr, passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (userAuth.getCurrentUser().isEmailVerified()) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                            }
                            else {
                                Toast.makeText(getActivity(), "Please verify your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Please enter correct Email or Password.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }

    void initRegisterBtn() {
        TextView regisbtn = getView().findViewById(R.id.register);
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new RegisterFragment()).addToBackStack(null).commit();
                Log.d("USER", "GO TO REGISTER");
            }
        });
    }

}
