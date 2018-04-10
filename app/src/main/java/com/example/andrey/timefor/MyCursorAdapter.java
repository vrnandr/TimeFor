package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Created by vrnandr on 10.04.18.
 */

public class MyCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return cursorInflater.inflate(R.layout.add_work_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvDesc = view.findViewById(R.id.desc);
        TextView tvTime = view.findViewById(R.id.time);
        tvDesc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        tvTime.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));

        CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(myCheckChangeList);

    }

    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.d("My", "onCheckedChanged: "+compoundButton.getTag()+" "+b);
        }
    };
}
