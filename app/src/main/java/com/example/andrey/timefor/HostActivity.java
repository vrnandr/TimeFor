package com.example.andrey.timefor;

//todo добавить примерное время в пути и добавление его к времени работ
//todo считать среднее за месяц
//todo поменять пакет и залить на гитхаб
//todo изменить вывод даты в листью к виду гггг.мм.дд

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class HostActivity extends AppCompatActivity implements
        SelectServiceFragment.OnServiceClickListener,
        AddWorkFragment.OnAddWorkButtonClickListener,
        MainFragment.OnMainFragmentEventListener,
        SettingsFragment.OnSettingsFragmentEventListener,
        LoaderManager.LoaderCallbacks<Cursor>{

    private MenuItem menuTextViewTime;
    private MenuItem menuNeedTimeItem;
    private MenuItem menuSettings;
    private MenuItem menuAverageTime;
    private SQLiteDatabase database;
    OnHostActivityEventListener hostActivityEventListener;

    private Integer sumTime;
    boolean enableColoring = true;
    private int time_norm = 0;
    private int time_percentage = 0;
    boolean enableNotification = true;
    private boolean enableSettingButton = true;

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        enableNotification = sharedPref.getBoolean("enable_notification",true);

        if (enableNotification){
            Intent intent = new Intent(this,HostActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.baseline_access_time_24)
                    .setContentTitle("408")
                    .setContentText("Время за сегодня: "+sumTime)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager!=null) manager.notify(1,builder.build());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
        if (enableNotification){
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager!=null) manager.cancel(1);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState==null){
            MainFragment mainFragment = new MainFragment();
            hostActivityEventListener = mainFragment;
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_place,mainFragment,MainFragment.class.toString())
                    .addToBackStack(MainFragment.class.toString())
                    .commit();
        } else
            hostActivityEventListener = (OnHostActivityEventListener) getSupportFragmentManager().findFragmentByTag(MainFragment.class.toString());


        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
        menuTextViewTime =  menu.findItem(R.id.item_time);
        menuNeedTimeItem = menu.findItem(R.id.item_time_norm);
        menuSettings = menu.findItem(R.id.item_menu_settings);
        menuAverageTime = menu.findItem(R.id.item_average);
        //hostActivityEventListener.onMenuCreated();
        getSupportLoaderManager().initLoader(MyCursorLoader.MENU_TIME,null, this);
        getSupportLoaderManager().initLoader(MyCursorLoader.AVERAGE_WORK_TIME_IN_MONTH,null, this);
        //getLoaderManager().initLoader(MyCursorLoader.MENU_TIME,null, this);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*menuTextViewTime =  menu.findItem(R.id.item_time);
        menuNeedTimeItem = menu.findItem(R.id.item_time_norm);*/
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        enableColoring = sharedPref.getBoolean("enable_coloring",true);
        time_norm = Integer.parseInt(sharedPref.getString("time_norm","480"));
        time_percentage = Integer.parseInt(sharedPref.getString("need_time_percentage","85"));

        menuSettings.setEnabled(enableSettingButton);

        getSupportLoaderManager().getLoader(MyCursorLoader.MENU_TIME).forceLoad();
        getSupportLoaderManager().getLoader(MyCursorLoader.AVERAGE_WORK_TIME_IN_MONTH).forceLoad();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.item_average:
                Toast.makeText(this, R.string.hint_time_average, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_time:
                Toast.makeText(this, R.string.hint_time_thisday, Toast.LENGTH_SHORT).show();
                break;
            case  R.id.item_time_norm:
                Toast.makeText(this, R.string.hint_time_need, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_menu_settings:

                enableSettingButton = false;
                invalidateOptionsMenu();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place,new SettingsFragment(),SettingsFragment.class.toString())
                        .addToBackStack(MainFragment.class.toString())
                        .commit();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount()>1)
            fragmentManager.popBackStack();
        else
            finish();
    }

    @Override
    public void onServiceClick(String service) {
        Bundle bundle = new Bundle();
        bundle.putString(AddWorkFragment.KEY,service);
        AddWorkFragment workFragment = new AddWorkFragment();
        workFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place, workFragment)
                .addToBackStack(AddWorkFragment.class.toString())
                .commit();
    }

    @Override
    public void onAddWorkButtonClick(Set<Integer> idWorksToAdd) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());// HH:mm:ss.SSS");
        String dateString = format.format(date);
        database.beginTransaction();
        try{
            for (Integer id:idWorksToAdd){
                String sql="INSERT INTO Works (Date, WorkID) VALUES ('"+dateString+"', '"+id+"')";
                database.execSQL(sql);
            }
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Toast.makeText(this, this.getString(R.string.error_on_add_work)+" "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            database.endTransaction();
        }

        hostActivityEventListener.onUpdateData();
        getSupportFragmentManager().popBackStack(MainFragment.class.toString(),0);
        invalidateOptionsMenu();
    }

    // когда во фрагменте добавления работ выбирается работа вызывается этот колбек для обновления времени в меню
    @Override
    public void onChangeTimeInMenu(Integer time) {
        setTextOnMenuItem(sumTime+time);
    }

    @Override
    public void onFABClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place, new SelectServiceFragment())
                .addToBackStack(SelectServiceFragment.class.toString())
                .commit();
    }

    //колбек с майн фрагмента для удаления работы и последующим обновлением данных во фрагменте.
    @Override
    public void onChildItemClick(int id) {
        database.execSQL("DELETE FROM Works WHERE _id="+id);
        hostActivityEventListener.onUpdateData();
        getSupportLoaderManager().getLoader(MyCursorLoader.MENU_TIME).forceLoad();
        getSupportLoaderManager().getLoader(MyCursorLoader.AVERAGE_WORK_TIME_IN_MONTH).forceLoad();

    }

    private void setTextOnMenuItem(Integer time){
        //menuTextViewTime.setTitle(""+time);

        TextView menuTime = findViewById(menuTextViewTime.getItemId());
        TextView menuNeedTime = findViewById(menuNeedTimeItem.getItemId());
        if (menuTime==null||menuNeedTime==null) return;

        menuTime.setText(String.valueOf(time));
        int need_time = time_norm*time_percentage/100;
        if (enableColoring) {
            if (time >= 0 && time < need_time) menuTime.setTextColor(Color.RED);
            else if (time >= need_time && time <= time_norm) menuTime.setTextColor(Color.GREEN);
            else menuTime.setTextColor(Color.BLACK);
        } else menuTime.setTextColor(Color.WHITE);

        if  (time<=need_time)
            menuNeedTime.setText(String.valueOf(need_time-time));
        else
            menuNeedTime.setText("0");

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, id, args);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId()==MyCursorLoader.MENU_TIME && data.moveToFirst()){
            Integer time = data.getInt(2);
            sumTime = time;
            setTextOnMenuItem(time);
        }

        if (loader.getId()==MyCursorLoader.AVERAGE_WORK_TIME_IN_MONTH && data.moveToFirst()){
            //menuAverageTime.setTitle(String.valueOf(data.getInt(0)));
            TextView tvmenuAverageTime = findViewById(menuAverageTime.getItemId());
            if (tvmenuAverageTime!=null)
                tvmenuAverageTime.setText(String.valueOf(data.getInt(0)));
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onStopSettingsFragment() {
        enableSettingButton = true;
        invalidateOptionsMenu();
    }
}
