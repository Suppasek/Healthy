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
import android.widget.Toast;

import static java.lang.Math.abs;

public class  SleepFormFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Bundle bundle = getArguments();
        Log.wtf("form", "id + " + getArguments().getParcelable("sleep"));

        Button saveBtn = getActivity().findViewById(R.id.fragment_sleep_form_button_accept);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = getActivity().findViewById(R.id.fragment_sleep_form_date);
                EditText sleepHour = getActivity().findViewById(R.id.fragment_sleep_form_sleep_hour);
                EditText wakeHour = getActivity().findViewById(R.id.fragment_sleep_form_wake_hour);
                EditText sleepMinute = getActivity().findViewById(R.id.fragment_sleep_form_sleep_minute);
                EditText wakeMinute = getActivity().findViewById(R.id.fragment_sleep_form_wake_minute);
                String dateStr = date.getText().toString();
                String sleepStr = sleepHour.getText().toString() + ":" + sleepMinute.getText().toString();
                String wakeStr = wakeHour.getText().toString() + ":" + wakeMinute.getText().toString();
                Integer sleepHourInt = Integer.parseInt(sleepHour.getText().toString());
                Integer wakeHourInt = Integer.parseInt(wakeHour.getText().toString());
                if (wakeHourInt <= sleepHourInt) {
                    wakeHourInt += 24;
                }
                Integer totalSleepHour = abs(sleepHourInt - wakeHourInt);
                Integer totalSleepMinute = abs(Integer.parseInt(sleepMinute.getText().toString()) - Integer.parseInt(wakeMinute.getText().toString()));
                String totalSleep = totalSleepHour.toString() + " : " + totalSleepMinute.toString();
                Sleep sleep = new Sleep(dateStr, sleepStr, wakeStr, totalSleep);
                DBHelper dbHelper = new DBHelper(getActivity());

                if (bundle == null) {
                    dbHelper.addSleep(sleep);
                }
                else {
                    dbHelper.updateSleep(sleep, bundle.getString("sleepId"));
                }

                SleepFragment sleepFragment = new SleepFragment();
                Bundle bundle = new Bundle();
                bundle.putString("complete", "complete");
                sleepFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, sleepFragment)
                        .addToBackStack(null).commit();
            }
        });

    }
}
