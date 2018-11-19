package com.example.suppasek.healthy.Sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.suppasek.healthy.R;
import com.example.suppasek.healthy.Sleep.Sleep;

import java.util.ArrayList;
import java.util.List;

public class SleepAdapter extends ArrayAdapter<Sleep> {

    List<Sleep> sleeps = new ArrayList<>();
    Context context;

    public SleepAdapter(Context context, int resource, List<Sleep> objects) {
        super(context, resource, objects);
        this.sleeps = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View sleepItem = LayoutInflater.from(context).inflate(
                R.layout.sleep_item,
                parent,
                false);

        TextView _date = sleepItem.findViewById(R.id.sleep_item_date);
        TextView _time = sleepItem.findViewById(R.id.sleep_item_time);
        TextView _totalHour = sleepItem.findViewById(R.id.sleep_item_total_hour);

        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        _time.setText(_row.getSleepTime() + " - " + _row.getWakeTime());
        _totalHour.setText(_row.getTotalSleepTime());

        return sleepItem;
    }

    @Override
    public int getPosition(@Nullable Sleep item) {
        return super.getPosition(item);
    }
}

