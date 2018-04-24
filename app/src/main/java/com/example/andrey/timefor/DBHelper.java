package com.example.andrey.timefor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {

    private String DB_PATH = null;
    static final String DB_NAME = "works.db";
    private static final int DB_VERSION = 2;

    protected SQLiteDatabase getMyDB() {
        return myDB;
    }

    private SQLiteDatabase myDB;
    private final Context myContext;

    final static String SERVICE = "Service";
    final static String SHORTDESC = "ShortDesc";
    final static String LONGDESC = "LongDesc";
    final static String TIMENORM = "TimeNorm";
    final static String ID ="_id";
    final static String TABLE_SERVICECATALOG = "ServiceCatalog";
    final static String TABLE_WORKS = "Works";


    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
        //DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        Log.e("Path 1", DB_PATH);
    }


    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if(dbExist){
            this.getWritableDatabase();
        }else{
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }


    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase(){
        //setDatabaseVersion();
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //база еще не существует
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     * */
    private void copyDataBase() throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
        setDatabaseVersion();
    }

    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        Log.d("My", "openDataBase: "+myDB.getVersion()+" "+myDB.getPath());
    }

    @Override
    public synchronized void close() {
        if(myDB != null)
            myDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("My", "onUpgrade: ");
        if (oldVersion==1 && newVersion==2){
            db.beginTransaction();
            try{
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Выполнение ППР на ЭТСО по графику\",\"планирование, организация и выполнение технического обслуживания оборудования ЭТСО в соответствии с согласованным с Заказчиком графиком\",180)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Консультация по работе ЭТСО\",\"консультации внутренних пользователей Заказчика по работе с оборудованием ЭТСО\",20)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Крупно-узловая замена компонентов ЭТСО\",\"восстановление работоспособности ЭТСО путем крупно-узловой поблочной замены неисправных узлов на месте установки\",70)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Отключение ЭТСО\",\"Отключение ЭТСО\",15)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Передача на восстановление ЭТСО подрядчику\",\"передача заявки внешнему поставщику, контроль выполнения обязательств внешнего поставщика по генеральным соглашениям и договорам Заказчика (эскалация запросов по гарантийному и не гарантийному ремонту техники)\",60)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Подготовка технического заключения \",\"выдача технических заключений о состоянии оборудования по запросу Заказчика\",35)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Подключение, настройка ЭТСО\",\"подключение и первичная настройка ЭТСО\",60)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Восстановление работы СПО на ЭТСО\",\"\",40)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Устранение сбоев на ЭТСО\",\"диагностика неисправностей и устранение сбоев в работе ЭТСО (без проведения ремонтных работ)\",60)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Обновление, настройка СПО на ЭТСО\",\"обновление и настройка системного ПО на ЭТСО\",120)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)\",\"Установка, удаление, настройка СПО на ЭТСО\",\"установка/удаление и настройка системного ПО на ЭТСО\",240)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Крупно-узловая замена компонентов ЭТСО\",\"восстановление работоспособности ЭТСО путем крупно-узловой поблочной замены неисправных узлов на месте установки\",70)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Отключение ЭТСО\",\"Отключение ЭТСО\",15)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Передача на восстановление ЭТСО подрядчику\",\"передача заявки внешнему поставщику, контроль выполнения обязательств внешнего поставщика по генеральным соглашениям и договорам Заказчика (эскалация запросов по гарантийному и не гарантийному ремонту техники)\",60)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Подготовка технического заключения \",\"выдача технических заключений о состоянии оборудования по запросу Заказчика\",35)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Подключение, настройка ЭТСО\",\"подключение и первичная настройка ЭТСО\",60)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Диагностика ЭТСО\",\"проведение первичной диагностики ЭТСО\",40)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Установка, удаление, настройка СПО на ЭТСО\",\"установка/удаление и настройка системного ПО на ЭТСО\",240)");
                db.execSQL("INSERT INTO ServiceCatalog (Service, ShortDesc, LongDesc, TimeNorm) VALUES (\"ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)\",\"Ремонт компонентов ЭТСО\",\"диагностика и ремонт неисправных блоков на месте установки ЭТСО, рабочем месте РП или в ЦР\",240)");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }
    }


    private void setDatabaseVersion() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH+DB_NAME, null,SQLiteDatabase.OPEN_READWRITE);
            db.execSQL("PRAGMA user_version = " + 1);
        } catch (SQLiteException e ) {
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDB.query(TABLE_SERVICECATALOG, null, null, null, null, null, null);
    }

    public Cursor queryServices() {
        return myDB.rawQuery("SELECT * FROM "+TABLE_SERVICECATALOG+" GROUP BY "+SERVICE, null);
    }



}

