package com.example.andrey.timefor;

//TODO по логике WorkID это id работы а по факту это время работы

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Cursor c;
    private final String TAG = "My";
    private DBHelper dbHelper;
    Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectServiceActivity.class);
                String cnt = mMenu.findItem(R.id.count).toString();
                intent.putExtra(AddWorkActivity.COUNT,cnt);
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


        dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu =menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.count: Toast.makeText(this, "Минут сегодня", Toast.LENGTH_SHORT).show();
                break;
            case R.id.need: Toast.makeText(this, "Еще надо", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (final Menu menu){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = format.format(date);
        //TODO курсоры теже что и в onResume
        Cursor cursor = dbHelper.getMyDB().rawQuery("SELECT * FROM "+DBHelper.TABLE_WORKS+" WHERE Date='"+dateString+"'", null);
        Cursor cursorIDs = dbHelper.getMyDB().rawQuery("SELECT "+DBHelper.ID+", "+DBHelper.TIMENORM+" FROM "+DBHelper.TABLE_SERVICECATALOG, null);
        int sum = 0;
        if (cursor.moveToFirst())
            do{
                Integer idWork = cursor.getInt(cursor.getColumnIndex("WorkID"));
                if (cursorIDs.moveToFirst())
                    do {
                        if (cursorIDs.getInt(cursorIDs.getColumnIndex(DBHelper.ID))==idWork){
                            sum+=cursorIDs.getInt(cursorIDs.getColumnIndex(DBHelper.TIMENORM));
                            break;
                        }
                    } while (cursorIDs.moveToNext());
            } while (cursor.moveToNext());
        cursor.close();
        cursorIDs.close();

        Log.d(TAG, "onPrepareOptionsMenu: hz");

        final Integer sumTr= new Integer(sum);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                View mCount = findViewById(R.id.count);
                MenuItem it = menu.findItem(R.id.count);
                if (mCount != null && mCount instanceof TextView){
                    if (sumTr>=0 && sumTr <408)
                        ((TextView) mCount).setTextColor( Color.RED);
                    else if (sumTr>=408 && sumTr<=480)
                        ((TextView) mCount).setTextColor( Color.GREEN);
                    else  if (sumTr>480)
                        ((TextView) mCount).setTextColor( Color.BLACK);
                }
            }
        });



        //Log.d(TAG, "onPrepareOptionsMenu: "+ ((TextView) mCount).getCurrentTextColor());

        menu.findItem(R.id.count).setTitle(Integer.toString(sum));

        String ns = getResources().getString(R.string.time85);
        Integer needTime = Integer.parseInt(ns)-sum;
        if (needTime>0)
            menu.findItem(R.id.need).setTitle(needTime.toString());
        else
            menu.findItem(R.id.need).setTitle("0");


        return true;
    }

    @Override
    public void onResume (){
        super.onResume();

        Cursor cursor = dbHelper.getMyDB().rawQuery("SELECT * FROM "+DBHelper.TABLE_WORKS, null);
        Cursor cursorIDs = dbHelper.getMyDB().rawQuery("SELECT "+DBHelper.ID+", "+DBHelper.TIMENORM+" FROM "+DBHelper.TABLE_SERVICECATALOG, null);
        //Map<Integer, Integer> idMap =

        Map<String, Integer>  hashMap = new HashMap<>();
        Set<String> dateSet = new HashSet<>();

        // составление набора дат
        if (cursor.moveToFirst())
            do
                dateSet.add(cursor.getString(cursor.getColumnIndex("Date")));
            while (cursor.moveToNext());

        //добавление суммы времени каждой даты
        for (String date:dateSet){
            int sum = 0;
            if (cursor.moveToFirst())
                do{
                    if (cursor.getString(cursor.getColumnIndex("Date")).equals(date)){
                        Integer idWork = cursor.getInt(cursor.getColumnIndex("WorkID"));
                        if (cursorIDs.moveToFirst())
                            do {
                                if (cursorIDs.getInt(cursorIDs.getColumnIndex(DBHelper.ID))==idWork){
                                    sum+=cursorIDs.getInt(cursorIDs.getColumnIndex(DBHelper.TIMENORM));
                                    break;
                                }
                            } while (cursorIDs.moveToNext());
                    }
                } while (cursor.moveToNext());
            hashMap.put(date,sum);
        }
        cursorIDs.close();
        cursor.close();

        ListView lv = findViewById(R.id.lv);
        LvAdapter lvAdapter= new LvAdapter(hashMap);
        lv.setAdapter(lvAdapter);
        invalidateOptionsMenu();

    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

}
