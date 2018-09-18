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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WeightFragment extends Fragment {


    FirebaseFirestore mdb;
    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Weight");

        initDataCall();
        initAddBtn();


    }

    void initDataCall() {
        final ArrayList<Weight> weights = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();

        mdb = FirebaseFirestore.getInstance();

        mdb.collection("myfitness").document(uid).collection("weight")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Weight weight = document.toObject(Weight.class);
                                weights.add(weight);
                            }
                            Collections.reverse(weights);
                            for (int i = 0; i < weights.size() - 1; i++) {

                                if (weights.get(i).getWeight() > weights.get(i + 1).getWeight()) {
                                    weights.get(i).setStatus("UP");
                                } else if (weights.get(i).getWeight() < weights.get(i + 1).getWeight()) {
                                    weights.get(i).setStatus("DOWN");
                                }
                            }

                            if (!weights.isEmpty()) {
                                ListView _weightList = getView().findViewById(R.id.weight_menu_list);
                                WeightAdapter _weightAdapter = new WeightAdapter(
                                        getActivity(),
                                        R.layout.fragment_weight_item,
                                        weights
                                );

                                _weightList.setAdapter(_weightAdapter);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        mdb.collection("myfitness").document(auth.getUid()).collection("weight").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@javax.annotation.Nullable QuerySnapshot DocumentSnapshots,
//                                @javax.annotation.Nullable FirebaseFirestoreException e) {
//                for (QueryDocumentSnapshot doc : DocumentSnapshots) {
//                    weights.add(doc.toObject(Weight.class));
//                }
//
//                for (int i = 0; i < weights.size() - 1; i++) {
//
//                    if (weights.get(i).getWeight() > weights.get(i + 1).getWeight()) {
//                        weights.get(i).setStatus("UP");
//                    } else if (weights.get(i).getWeight() < weights.get(i + 1).getWeight()) {
//                        weights.get(i).setStatus("DOWN");
//                    }
//                }
//
//                if (!weights.isEmpty()) {
//                    ListView _weightList = getActivity().findViewById(R.id.weight_menu_list);
//                    WeightAdapter _weightAdapter = new WeightAdapter(
//                            getActivity(),
//                            R.layout.fragment_weight_item,
//                            weights
//                    );
//
//                    _weightList.setAdapter(_weightAdapter);
//                }
//
//
//            }
//        });

    }

    void initAddBtn() {
        Button addbtn = getView().findViewById(R.id.weight_addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFormFragment()).addToBackStack(null).commit();
                Log.d("USER", "GO TO REGISTER");
            }
        });
    }
}

