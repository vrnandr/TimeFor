package com.example.andrey.timefor;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class AddWorkActivity extends AppCompatActivity {

    //final  static String SERVICE = "Service";
    Cursor cursor;
    HashSet<Integer> arrayList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        Intent intent = getIntent();
        String service = intent.getStringExtra(DBHelper.SERVICE);

        arrayList = new HashSet<>();

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

        Log.d("My", "onCreate: "+service);
        cursor = dbHelper.getMyDB().rawQuery("SELECT * FROM "+DBHelper.TABLE_SERVICECATALOG+" WHERE "+DBHelper.SERVICE+" = '"+service+"'", null);

        Log.d("My", "onCreate: "+cursor.getCount());

        ListView lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{DBHelper.SHORTDESC, DBHelper.TIMENORM},
                new int[]{android.R.id.text1,android.R.id.text2},
                0 );
        lv.setAdapter(simpleCursorAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.GREEN);
                Cursor cursor1 = (Cursor) adapterView.getItemAtPosition(i);
                int id = cursor1.getInt(cursor1.getColumnIndex(DBHelper.TIMENORM));
                arrayList.add(id);
                Log.d("My", "onItemClick: "+id);

            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
    }

    public void  onClick (View view){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = format.format(date);
        for (Integer timeNorm:arrayList){
            String sql="INSERT INTO Works (Date, WorkID) VALUES ('"+dateString+"', '"+timeNorm+"')";
            dbHelper.getMyDB().execSQL(sql);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
