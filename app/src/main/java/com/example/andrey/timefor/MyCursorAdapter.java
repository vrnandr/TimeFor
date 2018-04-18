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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrnandr on 10.04.18.
 */

public class MyCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    private List<Integer> posChkd;
    private List<Integer> idChkd;
    private String TAG ="My";
    private OnMyLVItemClickListener lst;

    class MyTag{
        Integer position;
        Integer id;
        Integer time;
        MyTag (Integer position, Integer id,Integer time){
            this.position = position;
            this.id = id;
            this.time = time;
        }
    }

    public MyCursorAdapter(Context context, Cursor c, List<Integer> idChkd, OnMyLVItemClickListener lst) {
        super(context, c, 0);
        this.idChkd= idChkd;
        this.lst = lst;
        posChkd = new ArrayList<>();
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = cursorInflater.inflate(R.layout.add_work_item,null);
        final CheckBox checkBox = view.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Log.d(TAG, "onCheckedChanged: "+compoundButton.getTag()+" "+compoundButton.toString());
                MyTag myTag = (MyTag) checkBox.getTag();
                //lst.onMyLVItemClickListener(myTag.time);
                if (b){
                    if (!posChkd.contains(myTag.position)){
                        posChkd.add(myTag.position);
                        lst.onMyLVItemClickListener(myTag.time);
                    }

                    if (!idChkd.contains(myTag.id))
                        idChkd.add(myTag.id);
                } else{

                    if (posChkd.remove((Object) myTag.position))
                        lst.onMyLVItemClickListener(-myTag.time);
                    idChkd.remove((Object)myTag.id);
                }

            }
        });


        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvDesc = view.findViewById(R.id.desc);
        TextView tvTime = view.findViewById(R.id.time);
        tvDesc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        tvTime.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));

        CheckBox checkBox = view.findViewById(R.id.checkBox);
        checkBox.setTag(new MyTag(cursor.getPosition(),cursor.getInt(0),cursor.getInt(cursor.getColumnIndex(DBHelper.TIMENORM))));

        if (posChkd.contains(cursor.getPosition()))
            checkBox.setChecked(true);
        else
            checkBox.setChecked(false);


    }

}
