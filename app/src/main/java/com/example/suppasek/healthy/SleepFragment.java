package com.example.suppasek.healthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class SleepFragment extends Fragment {

    ArrayList<Sleep> sleeps = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Sleep");

        getData();

        Button addBtn = getActivity().findViewById(R.id.sleep_addbtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFormFragment())
                        .addToBackStack(null).commit();
            }
        });

    }

    void getData() {
        DBHelper db = new DBHelper(getActivity());
        db.getReadableDatabase();
        sleeps = db.getSleepList();
        ListView sleepList = getView().findViewById(R.id.sleep_item_list);
        SleepAdapter sleepAdapter = new SleepAdapter(getActivity(), R.layout.sleep_item, sleeps);
        sleepList.setAdapter(sleepAdapter);

    }

}
