package com.example.andrey.timefor;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity{

    private final String TAG = "My";
    private DBHelper dbHelper;
    Menu mMenu;

    SimpleCursorTreeAdapter simpleCursorTreeAdapter;
    ExpandableListView elv;

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
            Toast.makeText(this, ioe.getMessage(),Toast.LENGTH_SHORT).show();
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle) {
            Toast.makeText(this, sqle.getMessage(),Toast.LENGTH_SHORT).show();
            throw sqle;
        }



        elv = findViewById(R.id.expandableListView);

        Cursor cursor = dbHelper.getMyDB().rawQuery("select Works._id, Works.Date as date, sum(ServiceCatalog.TimeNorm) as sum from Works inner join ServiceCatalog on Works.WorkID=ServiceCatalog._id group by Date order by Date desc", null);

        simpleCursorTreeAdapter = new MySimpleCursorTreeAdapter(
                this,

                cursor,
                R.layout.exlitems,
                new String[] {"date","sum"},
                new int[] {R.id.date, R.id.timeSum},

                R.layout.exlsubitems,
                new String[]{"work", "time"},
                new int[] {R.id.service, R.id.time});

        elv.setAdapter(simpleCursorTreeAdapter);

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {


                dbHelper.getMyDB().execSQL("delete from Works Where _id ="+l);
                Cursor cursor = dbHelper.getMyDB().rawQuery("select Works._id, Works.Date as date, sum(ServiceCatalog.TimeNorm) as sum from Works inner join ServiceCatalog on Works.WorkID=ServiceCatalog._id group by Date order by Date desc", null);
                simpleCursorTreeAdapter.changeCursor(cursor);
                simpleCursorTreeAdapter.notifyDataSetChanged();
                invalidateOptionsMenu();
                //notifyDataSetChanged();
                return true;
            }
        });

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
            case R.id.mean: Toast.makeText(this, "Среднее", Toast.LENGTH_SHORT).show();
                break;
            case R.id.count: Toast.makeText(this, "Минут сегодня", Toast.LENGTH_SHORT).show();
            break;
            case R.id.need: Toast.makeText(this, "Еще надо", Toast.LENGTH_SHORT).show();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (final Menu menu){

        Cursor cursor = dbHelper.getMyDB().rawQuery("select (sum(ServiceCatalog.TimeNorm)) from ServiceCatalog inner join Works on ServiceCatalog._id = Works.WorkID", null);
        int sumAllDays = 0;
        if (cursor.moveToFirst())
            sumAllDays = cursor.getInt(0);
        cursor = dbHelper.getMyDB().rawQuery("SELECT count(DISTINCT Date) FROM Works", null);
        int countDays = 1;
        if (cursor.moveToFirst())
            countDays = cursor.getInt(0);

        if (countDays==0) {
            menu.findItem(R.id.mean).setTitle("");
        } else {
            menu.findItem(R.id.mean).setTitle(Integer.toString(sumAllDays/countDays));
        }




        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = format.format(date);

        cursor = dbHelper.getMyDB().rawQuery("select Works._id, Works.Date, sum(ServiceCatalog.TimeNorm) from Works inner join ServiceCatalog on ServiceCatalog._id=Works.WorkID where date = '"+dateString+"'", null);
        Integer sum=0;
        if (cursor.moveToFirst())
            sum = cursor.getInt(2);
        cursor.close();

        // сменить цвет в зависимости от суммы минут
        final Integer sumTr = sum;
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


        menu.findItem(R.id.count).setTitle(Integer.toString(sum));

        //расчитать остаток от минимума минут
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

        Cursor cursor = dbHelper.getMyDB().rawQuery("select Works._id, Works.Date as date, sum(ServiceCatalog.TimeNorm) as sum from Works inner join ServiceCatalog on Works.WorkID=ServiceCatalog._id group by Date order by Date desc", null);
        simpleCursorTreeAdapter.changeCursor(cursor);
        simpleCursorTreeAdapter.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    @Override
    public void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    class MySimpleCursorTreeAdapter extends SimpleCursorTreeAdapter {


        public MySimpleCursorTreeAdapter(Context context, Cursor cursor, int groupLayout, String[] groupFrom, int[] groupTo, int childLayout, String[] childFrom, int[] childTo) {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom, childTo);
        }


        @Override
        protected Cursor getChildrenCursor(Cursor cursor) {
            String date = cursor.getString(1);
            return  dbHelper.getMyDB().rawQuery("select Works._id, ServiceCatalog.Service as work, ServiceCatalog.TimeNorm as time from ServiceCatalog inner join Works on ServiceCatalog._id=Works.WorkID where date = '"+date+"'", null);
        }

    }

}
