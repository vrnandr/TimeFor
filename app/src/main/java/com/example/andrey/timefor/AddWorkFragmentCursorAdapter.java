package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by vrnandr on 28.08.18.
 */

public class AddWorkFragmentCursorAdapter extends SimpleCursorAdapter {

    private HashMap<Integer,Integer> checkedButton = new HashMap<>();

    public interface OnCheckBoxClickListener{
        void onCheckBoxClick(HashMap<Integer,Integer> data);
    }
    private OnCheckBoxClickListener onCheckBoxClickListener;



    private class MyTag{
        private Integer id;
        private Integer time;

        MyTag (Integer id, Integer time){
            this.id = id;
            this.time = time;
        }

        public Integer getId() {
            return id;
        }

        public Integer getTime() {
            return time;
        }
    }

    AddWorkFragmentCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to,
                                 OnCheckBoxClickListener onCheckBoxClickListener) {
        super(context, layout, c, from, to, 0);
        this.onCheckBoxClickListener = onCheckBoxClickListener;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvDescription = view.findViewById(R.id.add_work_fragment_listview_item_description);
        TextView tvTime = view.findViewById(R.id.add_work_fragment_listview_item_time);

        tvDescription.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        tvTime.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));
        CheckBox checkBox = view.findViewById(R.id.add_work_fragment_listview_item_checkbox);
        checkBox.setTag(new MyTag(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)),
                                    cursor.getInt(cursor.getColumnIndex(DBHelper.TIMENORM))));
        if (checkedButton.containsKey(cursor.getInt(cursor.getColumnIndex(DBHelper.ID))))
            checkBox.setChecked(true);
        else  checkBox.setChecked(false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = super.newView(context,cursor,parent);
        final CheckBox checkBox = v.findViewById(R.id.add_work_fragment_listview_item_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer id = ((MyTag) checkBox.getTag()).getId();
                Integer time = ((MyTag) checkBox.getTag()).getTime();
                if (isChecked)
                    checkedButton.put(id,time);
                else
                    checkedButton.remove(id);
                onCheckBoxClickListener.onCheckBoxClick(checkedButton);
            }
        });
        return v;
    }
}
