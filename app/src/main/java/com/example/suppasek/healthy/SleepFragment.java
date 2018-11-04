package com.example.suppasek.healthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        Bundle bundle = getArguments();

        if (bundle != null) {
            Log.wtf("sleep", "toast");
            Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();
            bundle.clear();
        }

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
        sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SleepFormFragment sleepFormFragment = new SleepFormFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("sleep", sleeps.get(position));
                bundle.putString("sleepId", sleeps.get(position).getId());
                Log.wtf("sleep","size + " + sleeps.size());
                sleepFormFragment.setArguments(bundle);
                setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, sleepFormFragment).addToBackStack(null).commit();
            }
        });
        sleepList.setAdapter(sleepAdapter);

    }

}
