package com.example.andrey.timefor;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddWorkActivity extends AppCompatActivity implements OnMyLVItemClickListener {

    final static String COUNT = "count";
    Cursor cursor;
    ArrayList<Integer> arrayList;
    DBHelper dbHelper;
    Menu mMenu;
    String count;
    final static String TAG = "My";
    List<Integer> idChkd;

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

        idChkd = new ArrayList<>();

        MyCursorAdapter cursorAdapter = new MyCursorAdapter(this, cursor, idChkd, this);

        lv.setAdapter(cursorAdapter);
        invalidateOptionsMenu();

    }

    @Override
    public void onDestroy(){
        cursor.close();
        super.onDestroy();
    }

    public void  onClick (View view){

        //Log.d(TAG, "onClick: "+idChkd);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = format.format(date);
        for (Integer id:idChkd){
            String sql="INSERT INTO Works (Date, WorkID) VALUES ('"+dateString+"', '"+id+"')";
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
        switch (id){
            case R.id.count: Toast.makeText(this, "Минут сегодня", Toast.LENGTH_SHORT).show();
                             break;
            case R.id.need: Toast.makeText(this, "Еще надо", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        View mCount = findViewById(R.id.count);
        Log.d(TAG, "onPrepareOptionsMenu: "+mCount);
        if (mCount != null && mCount instanceof TextView){
            Integer cnt = Integer.parseInt(count);
            if (cnt>=0 && cnt <408)
                ((TextView) mCount).setTextColor( Color.RED);
            else if (cnt>=408 && cnt<=480)
                ((TextView) mCount).setTextColor( Color.GREEN);
            else  if (cnt>480)
                ((TextView) mCount).setTextColor( Color.BLACK);


        }
        menu.findItem(R.id.count).setTitle(count);

        String ns = getResources().getString(R.string.time85);
        Integer needTime = Integer.parseInt(ns)-Integer.parseInt(count);
        if (needTime>0)
            menu.findItem(R.id.need).setTitle(needTime.toString());
        else
            menu.findItem(R.id.need).setTitle("0");

        return true;
    }

    @Override
    public void onMyLVItemClickListener(Integer addTime) {
        Integer cnt = Integer.parseInt(count);
        cnt+=addTime;
        count= Integer.toString(cnt);
        invalidateOptionsMenu();
    }
}
