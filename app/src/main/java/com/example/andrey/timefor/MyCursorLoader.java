package com.example.andrey.timefor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by vrnandr on 27.08.18.
 */

public class MyCursorLoader extends CursorLoader {
    private SQLiteDatabase database;
    private int id;
    private Bundle args;


    public static final int SERVICES = -2;
    public static final int WORKS = -3;
    public static final int DAYS = -1;
    public static final int MENU_TIME = -4;



    public MyCursorLoader(Context context,int id, Bundle args) {
        super(context);
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        this.id = id;
        this.args = args;

    }

    @Override
    public Cursor loadInBackground() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(HostActivity.PATTERN, Locale.getDefault());
        String dateString = format.format(date);
        switch (id){
            case DAYS: return database.rawQuery("select Works._id, Works.Date as date, sum(ServiceCatalog.TimeNorm) as sum from Works inner join ServiceCatalog on Works.WorkID=ServiceCatalog._id group by Date order by Date desc", null);
            case SERVICES: return database.rawQuery("SELECT * FROM ServiceCatalog GROUP BY Service", null);
            case WORKS: return database.rawQuery("SELECT * FROM ServiceCatalog WHERE Service = '"+args.getString(AddWorkFragment.KEY)+"'", null);
            case MENU_TIME: return database.rawQuery("select Works._id, Works.Date, sum(ServiceCatalog.TimeNorm) from Works inner join ServiceCatalog on ServiceCatalog._id=Works.WorkID where date = '"+dateString+"'", null);
        }
        if (id>=0)
            return database.rawQuery("select Works._id, ServiceCatalog.Service as work, ServiceCatalog.TimeNorm as time from ServiceCatalog inner join Works on ServiceCatalog._id=Works.WorkID where date = '"+args.getString(MainFragment.KEY)+"'", null);
        return null;
    }
}