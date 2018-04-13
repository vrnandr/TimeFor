package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * Created by Andrey on 09.04.2018.
 */

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
    final String TAG ="My";

    public MySimpleCursorAdapter(Context context,Cursor c) {
        super(context, R.layout.add_work_item, c, null, null, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);





        return view;
    }
}
