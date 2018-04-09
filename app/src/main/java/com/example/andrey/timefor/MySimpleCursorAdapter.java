package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;



/**
 * Created by Andrey on 09.04.2018.
 */

public class MySimpleCursorAdapter extends SimpleCursorAdapter {
    final String TAG ="My";

    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View result;

                if (convertView == null) {
                    result = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
                } else {
                    result = convertView;
                }

                CheckBox ch =(CheckBox) result.findViewById(R.id.checkBox);
                ch.setText("123");

                Log.d(TAG, "onClick: is chekbox "+position);
                if (view.getClass().equals(CheckBox.class)){
                    Log.d(TAG, "onClick: is chekbox"+position);
                }
            }
        });

        return v;

    }
}
