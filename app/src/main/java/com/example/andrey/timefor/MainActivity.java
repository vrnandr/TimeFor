package com.example.andrey.timefor;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Cursor c;

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
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });


        DBHelper dbHelper = new DBHelper(this);
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
        Cursor cursor = dbHelper.getMyDB().rawQuery("SELECT * FROM "+DBHelper.TABLE_WORKS, null);

        HashMap<String, Integer>  hashMap = new HashMap<>();
        HashSet<String> dateSet = new HashSet<>();
//TODO ОЛОЛЛОЛОЛОЛОЛО
        // составление набора дат
        while (cursor.moveToNext())
            dateSet.add(cursor.getString(cursor.getColumnIndex("Date")));

        //добавление суммы времени каждой даты
        for (String date:dateSet){
            cursor.moveToFirst();
            int sum = 0;
            while (cursor.moveToNext())
                if (cursor.getString(cursor.getColumnIndex("Date")).equals(date))
                    sum+=cursor.getInt(cursor.getColumnIndex("WorkID"));
            hashMap.put(date,sum);

        }

        ListView lv = findViewById(R.id.lv);
        LvAdapter lvAdapter= new LvAdapter(hashMap);
        lv.setAdapter(lvAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
