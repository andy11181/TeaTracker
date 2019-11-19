package com.tea.teatracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;


public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position % 2 == 1)
            view.setBackgroundResource(R.color.list1);
        else
            view.setBackgroundResource(R.color.list2);

        return view;
    }
}