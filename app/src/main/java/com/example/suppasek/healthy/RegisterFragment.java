package com.example.suppasek.healthy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment{

    FirebaseAuth fbAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Register");

        fbAuth = FirebaseAuth.getInstance();

        Button regisBtn = getView().findViewById(R.id.register_regisbtn);
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

    }

    void registerNewUser() {
        EditText userId = getView().findViewById(R.id.register_email);
        EditText password = getView().findViewById(R.id.register_password);
        EditText repassword = getView().findViewById(R.id.register_repassword);
        String userIdStr = userId.getText().toString();
        String passwordStr = password.getText().toString();
        String repasswordStr = repassword.getText().toString();

        if (passwordStr.length() < 6 ) {
            Toast.makeText(getActivity(), "Please fill password at least 6 letters", Toast.LENGTH_SHORT).show();
        }
        else if (!passwordStr.equals(repasswordStr)) {
            Toast.makeText(getActivity(), "Please fill your password again", Toast.LENGTH_SHORT).show();
        }
        else {
            fbAuth.createUserWithEmailAndPassword(userIdStr, passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    sentVerifiedEmail(authResult.getUser());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new LoginFragment()).commit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sentVerifiedEmail(FirebaseUser user) {
        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new LoginFragment()).commit();
                Toast.makeText(getActivity(), "Register Complete", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
