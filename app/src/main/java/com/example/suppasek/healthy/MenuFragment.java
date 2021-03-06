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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.suppasek.healthy.Sleep.SleepFragment;
import com.example.suppasek.healthy.Weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ArrayList<String> menu;
    FirebaseAuth userAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");


        userAuth = FirebaseAuth.getInstance();

        menu = new ArrayList<>();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("Sleep");
        menu.add("Post");
        menu.add("Sign Out");
        ListView menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, menu);

        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Menu", "Select = " + menu.get(i));
                menuAdapter.notifyDataSetChanged();
                if (menu.get(i).equals("BMI")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Weight")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Sleep")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment())
                            .addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Post")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new PostFragment())
                            .addToBackStack(null).commit();
                }
                else if (menu.get(i).equals("Sign Out")) {
                    userAuth.signOut();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment())
                            .commit();
                }
            }
        });
    }
}
