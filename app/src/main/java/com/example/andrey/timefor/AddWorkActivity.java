package com.example.andrey.timefor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class AddWorkActivity extends AppCompatActivity {

    final static String COUNT = "count";
    Cursor cursor;
    ArrayList<Integer> arrayList;
    DBHelper dbHelper;
    Menu mMenu;
    String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        Intent intent = getIntent();
        String service = intent.getStringExtra(DBHelper.SERVICE);
        count = intent.getStringExtra(AddWorkActivity.COUNT);

        arrayList = new ArrayList<>();

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

        /*SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.add_work_item,
                cursor,
                new String[]{DBHelper.SHORTDESC, DBHelper.TIMENORM},
                new int[]{R.id.desc,R.id.time},
                0 );

        lv.setAdapter(simpleCursorAdapter);*/

        HashMap<Long, Boolean> isChkd = new HashMap<>();

        /*for (Long i=0L; i<cursor.getCount();i++){
            isChkd.put(i,false);
        }*/

        MyCursorAdapter cursorAdapter = new MyCursorAdapter(this, cursor, isChkd);
        lv.setAdapter(cursorAdapter);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(Color.GREEN);
                Cursor cursor1 = (Cursor) adapterView.getItemAtPosition(i);
                int id = cursor1.getInt(cursor1.getColumnIndex(DBHelper.TIMENORM));
                arrayList.add(id);
                //cursor1.close();
                Log.d("My", "onItemClick: "+id);

            }
        });*/
    }

    @Override
    public void onDestroy(){
        cursor.close();
        super.onDestroy();
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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.count) {
            Toast.makeText(this, "Минут сегодня", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        menu.findItem(R.id.count).setTitle(count);
        return true;
    }
}
