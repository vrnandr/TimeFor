package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by vrnandr on 10.04.18.
 */

public class MyCursorAdapter extends CursorAdapter implements View.OnClickListener {
    private LayoutInflater cursorInflater;
    //private Long id;

    private static class ViewHolder  {
        int Number;
        boolean isCheked;
        CheckBox checkBox;
        TextView desc;
        TextView time;
    }

    HashMap<Long, Boolean> isChkd;

    boolean[] isC;

    public MyCursorAdapter(Context context, Cursor c, HashMap<Long, Boolean>isChkd) {
        super(context, c, 0);
        this.isChkd= isChkd;
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        isC = new boolean[c.getCount()];
        //isChkd = new HashMap<>();

       /* for (Long i=0L; i<c.getCount();i++){
            isChkd.put(i,false);
        }
        for (Long l:isChkd.keySet()
             ) {
            Log.d("My", "MyCursorAdapter: "+l+" "+isChkd.get(l));

        }

*/
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = cursorInflater.inflate(R.layout.add_work_item,null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.checkBox = view.findViewById(R.id.checkBox);

        long id = cursor.getLong(0);
        viewHolder.checkBox.setTag(id);

        viewHolder.checkBox.setOnClickListener(this);
        //viewHolder.checkBox.setOnCheckedChangeListener(myCheckChangeList);
        viewHolder.desc = view.findViewById(R.id.desc);
        viewHolder.time = view.findViewById(R.id.time);

        //viewHolder.Number = cursor.getPosition();

        isChkd.put(id,false);



        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        /*for (Long l:isChkd.keySet()
                ) {
            Log.d("My", "bin: "+l+" "+isChkd.get(l));

        }*/

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.desc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.SHORTDESC)));
        viewHolder.time.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TIMENORM)));

        //viewHolder.Number = cursor.getPosition();
        //Log.d("My", "bindView: "+viewHolder.Number);
        long id = cursor.getLong(0);

//        Log.d("My", "bindView: "+isChkd.size()+" "+id+" "+isChkd.get(id) );
        if (!isChkd.containsKey(id))
            isChkd.put(id,false);

        boolean bb= isChkd.get(id);
        Log.d("My", "isChk: "+id+" "+bb);
        viewHolder.checkBox.setChecked(bb);

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

        Log.d("My", "onCheckedChanged: "+view.getTag()+" "+((CheckBox) view).isChecked());
        isChkd.put((Long)view.getTag(),((CheckBox) view).isChecked());



    }


    private OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            ((ViewHolder) compoundButton.getParent()).isCheked = compoundButton.isChecked();

        }
    };
}
