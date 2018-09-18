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
import android.widget.TextView;
import android.widget.Toast;

public class BMIFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("BMI");

        initCalBMI();

    }

    void initCalBMI(){
        Button btn_bmi_cal =  getView().findViewById(R.id.bmi_cal);
        btn_bmi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText cm =  getView().findViewById(R.id.bmi_cm);
                EditText kg =  getView().findViewById(R.id.bmi_kg);

                String cm_str = cm.getText().toString();
                Float cm_flo = Float.parseFloat(cm_str);
                String kg_str = kg.getText().toString();
                Float kg_flo = Float.parseFloat(kg_str);

                if (cm_str.isEmpty() || kg_str.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("BMI", "FIELD NAME IS EMPTY");
                }
                else {
                    Log.d("BMI", "BMI IS VALUE");
                    cm_flo = (cm_flo/100)*(cm_flo/100);
                    Float bmi = kg_flo / cm_flo;
                    //bmi = bmi.shortValue();

                    TextView you_bmi =  getView().findViewById(R.id.bmi_yourBMI);
                    you_bmi.setVisibility(View.VISIBLE);


                    TextView you_bmi_cal =  getView().findViewById(R.id.bmi_bmi);
                    you_bmi_cal.setVisibility(View.VISIBLE);
                    you_bmi_cal.append(bmi.toString());

                }
            }
        });
    }
}
