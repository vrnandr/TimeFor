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

public class MyCursorAdapter extends CursorAdapter implements View.OnClickListener {
    private LayoutInflater cursorInflater;
    private Long id;

    private    static  class   ViewHolder  {
        Long    id;
        boolean isCheked;
        CheckBox checkBox;
        TextView desc;
        TextView time;
    }

    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = cursorInflater.inflate(R.layout.add_work_item,null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.checkBox = view.findViewById(R.id.checkBox);
        //viewHolder.checkBox.setOnClickListener(this);
        viewHolder.checkBox.setOnCheckedChangeListener(myCheckChangeList);
        viewHolder.desc = view.findViewById(R.id.desc);
        viewHolder.time = view.findViewById(R.id.time);


        view.setTag(viewHolder);

        return view;


        /*id = cursor.getLong(cursor.getColumnIndex("_ID"));

        return cursorInflater.inflate(R.layout.add_work_item, viewGroup, false);*/
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.desc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        viewHolder.time.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));
        //viewHolder.id = cursor.getLong(cursor.getColumnIndex("_ID"));
        viewHolder.checkBox.setTag(cursor.getLong(cursor.getColumnIndex(DBHelper.TIMENORM)));
        //viewHolder.checkBox.setChecked(viewHolder.isCheked);



        /*TextView tvDesc = view.findViewById(R.id.desc);
        TextView tvTime = view.findViewById(R.id.time);
        tvDesc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        tvTime.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));

        CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setTag(cu);
        checkBox.setOnCheckedChangeListener(myCheckChangeList);*/

    }

    @Override
    public void onClick (View view){
        boolean isCh = ((CheckBox) view).isChecked();



    }


    private OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.d("My", "onCheckedChanged: "+compoundButton.getTag()+" "+b);
        }
    };
}
