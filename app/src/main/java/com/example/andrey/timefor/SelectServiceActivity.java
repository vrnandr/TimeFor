package com.example.andrey.timefor;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.IOException;

public class SelectServiceActivity extends ListActivity {
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db;
        try {
            DBHelper dbHelper = new DBHelper(this);
            db = dbHelper.getReadableDatabase();
        } catch (SQLException e) {
            Toast.makeText(this, e.getLocalizedMessage() ,Toast.LENGTH_LONG).show();
            throw new Error("Unable to create database");
        }

        c = db.rawQuery("SELECT * FROM "+DBHelper.TABLE_SERVICECATALOG+" GROUP BY "+ DBHelper.SERVICE, null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, new String[]{DBHelper.SERVICE}, new int[]{android.R.id.text1},0 );

        setListAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor cursor = (Cursor)getListAdapter().getItem(position);
        String service = cursor.getString(cursor.getColumnIndex(DBHelper.SERVICE));
        Intent oldIntent = getIntent();
        String count = oldIntent.getStringExtra(AddWorkActivity.COUNT);
        Intent intent = new Intent(this, AddWorkActivity.class);
        intent.putExtra(DBHelper.SERVICE, service);
        intent.putExtra(AddWorkActivity.COUNT,count);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        c.close();
        super.onDestroy();
    }


}
