package com.example.suppasek.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class WeightFormFragment extends Fragment {

    FirebaseFirestore mdb;
    FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Weight");

        mdb = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        initSaveBtn();

    }

    void initSaveBtn() {
        final String uid = auth.getUid();
        Button addbtn = getView().findViewById(R.id.weight_form_add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText weight =  getView().findViewById(R.id.weight_form_weight);
                EditText date =  getView().findViewById(R.id.weight_form_date);
                int weightInt = Integer.parseInt(weight.getText().toString());
                String dateStr = date.getText().toString();
                dateStr = dateStr.replace("/", "-");
                Weight weightObj = new Weight(dateStr, weightInt);
                mdb.collection("myfitness").document(uid).collection("weight").document(dateStr).set(weightObj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,new WeightFragment()).addToBackStack(null).commit();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }

}
