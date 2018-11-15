package com.example.andrey.timefor;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vrnandr on 27.08.18.
 */


public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 12;
    //10 версия коневертирование 30.10.2010 в 2010.10.30
    //11 версия конвертирование 2010.10.30 в 2010-10-30 формат даты sqlite
    //12 версия добавление услуги 26-01


    private static final String DB_NAME = "works.db";
    public static final String ID ="_id";
    public static final String SERVICE = "Service";
    public static final String SHORTDESC = "ShortDesc";
    public static final String TIMENORM = "TimeNorm";
    public static final String DATE = "Date";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("CREATE TABLE ServiceCatalog (_id INTEGER PRIMARY KEY AUTOINCREMENT, Service TEXT, ShortDesc TEXT, TimeNorm  INTEGER)");
            db.execSQL("CREATE TABLE Works (_id INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, WorkID INTEGER, Distance REAL)");

            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-02-01 ПЕРЕГОВОРНЫЕ','Демонстрация аудио/видео контента',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-02-01 ПЕРЕГОВОРНЫЕ','Консультация по проведению мероприятий',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-02-01 ПЕРЕГОВОРНЫЕ','Подготовка, настройка оборудования для конференций',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-02-01 ПЕРЕГОВОРНЫЕ','Техническое обслуживание оборудования',70)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-02-01 ПЕРЕГОВОРНЫЕ','Техническое сопровождение мероприятия',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Выполнение ППР на БКО',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Консультация по работе БКО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Модернизация БКО',75)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Отключение БКО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Подготовка технического заключения по работе БКО',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Подключение БКО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Ремонт БКО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-04-01 БКО_БТО-1','Устранение сбоев в работе БКО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-06-01 РМП_ВП','Обновление клиентского ПО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-06-01 РМП_ВП','Перенос данных клиентского ПО на другое РМ',80)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-06-01 РМП_ВП','Удаление клиентского ПО',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-06-01 РМП_ВП','Установка, настройка клиентского ПО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-06-01 РМП_ВП','Устранение сбоев клиентского ПО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Диагностика ЭТСО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Консультация по работе ЭТСО',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Крупно-узловая замена компонентов ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Отключение ЭТСО',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Передача на восстановление ЭТСО подрядчику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Подключение, настройка ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-07-01 ЭТСО_БТО-1','Устранение сбоев на ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Восстановление работы СПО на ЭТСО',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Выполнение ППР на ЭТСО по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Диагностика ЭТСО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Консультация по работе ЭТСО',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Крупно-узловая замена компонентов ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Обновление, настройка СПО на ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Отключение ЭТСО',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Передача на восстановление ЭТСО подрядчику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Подключение, настройка ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Установка, удаление, настройка СПО на ЭТСО',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА ЭТСО (КТО-1)','Устранение сбоев на ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Диагностика ЭТСО',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Крупно-узловая замена компонентов ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Отключение ЭТСО',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Передача на восстановление ЭТСО подрядчику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Подключение, настройка ЭТСО',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-08-02 РЕМОНТ ЭТСО (КТО-1)','Ремонт компонентов ЭТСО',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Восстановление работоспособности оборудования крупно-узловой заменой',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Выполнение ППР на СПД по графику',80)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Коммутация СКС',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Монтаж  демонтаж оборудования СПД',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Первичная настройка оборудования СПД',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Проверка каналов связи: основной. резервный, GSM, WIFI',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-09-01 ЛВС-СПД','Устранение сбоев оборудования СПД',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Диагностика, восстановление работы СПО/ППО на МРМ',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Замена оборудования МРМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Консультация по работе МРМ',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Передача на восстановление МРМ подрядчику',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Подключение МРМ к WiFi, GSM',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Подключение, настройка внешних устройств к МРМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Установка/удаление, обновление, настройка СПО/ППО. специального ПО на МРМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-10-01 МРМ_БТО-1','Устранение сбоев МРМ',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Выполнение ППР на серверах по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Диагностика сбоев на серверах',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Замена серверного оборудования из состава оперативного фонда',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Модернизация серверов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Установка, удаление, обновление, настройка СПО/ППО',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-12-01 ССО','Устранение сбоев на серверах',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Восстановление линии порт - патч-панель',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Диагностика компонентов СКС',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Документирование работ на СКС',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Инвентаризация ЗИП. кабельной системы ЛВС',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Перенос розеток без модернизации СКС',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-13-01 СКС','Ремонт/замена элементов СКС',20)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Установка, обновление, настройка. удаление СПО/ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (БТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА (БТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-14-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА (БТО-1)','Установка, настройка оборудования нового РМ. подключение и настройка ПУ и СПД. Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Замена неисправных элементов',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Установка, обновление, настройка. удаление СПО/ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА (КТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА (КТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА (КТО-1)','Установка, настройка оборудования нового РМ, подключение и настройка ПУ и СПД. Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-03 РЕМОНТ РАБОЧЕГО МЕСТА (КТО-1)','Диагностика оборудования РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-03 РЕМОНТ РАБОЧЕГО МЕСТА (КТО-1)','Замена неисправных элементов оборудования РМ',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-03 РЕМОНТ РАБОЧЕГО МЕСТА (КТО-1)','Крупно-узловая поблочная замена компонентов РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-03 РЕМОНТ РАБОЧЕГО МЕСТА (КТО-1)','Подготовка технического заключения',35)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-15-03 РЕМОНТ РАБОЧЕГО МЕСТА (КТО-1)','Прием и диагностика оборудования РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Установка, обновление, настройка, удаление СПО/ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (БТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА VIP (БТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-16-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА VIP (БТО-1)','Установка, настройка оборудования нового РМ. подключение и настройка ПУ и СПД. Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Замена неисправных элементов',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Установка, обновление, настройка, удаление СПО/ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА VIP (КТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА VIP (КТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА VIP (КТО-1)','Установка, настройка оборудования нового РМ. подключение и настройка ПУ и СПД, Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-03 РЕМОНТ РАБОЧЕГО МЕСТА VIP (КТО-1)','Диагностика оборудования РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-03 РЕМОНТ РАБОЧЕГО МЕСТА VIP (КТО-1)','Замена неисправных элементов оборудования РМ',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-03 РЕМОНТ РАБОЧЕГО МЕСТА VIP (КТО-1)','Крупно-узловая поблочная замена компонентов РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-03 РЕМОНТ РАБОЧЕГО МЕСТА VIP (КТО-1)','Подготовка технического заключения',35)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-17-03 РЕМОНТ РАБОЧЕГО МЕСТА VIP (КТО-1)','Прием и диагностика оборудования РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Установка, обновление, настройка. удаление СПО ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (БТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА CIP (БТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-18-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА CIP (БТО-1)','Установка, настройка оборудования нового РМ. подключение и настройка ПУ и СПД. Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Восстановление работоспособности оборудования поблочной заменой неисправных узлов',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Выполнение ППР на РМ по графику',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Диагностика неполадок оборудования и ПО',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Замена компонентов РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Замена неисправных элементов',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Консультация по работе РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Модернизация РМ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Отключение РМ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Подключение и настройка внешних устройств на РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Подключение, настройка РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Установка, обновление, настройка. удаление СПО/ППО на РМ',55)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-01 ТЕХНИЧЕСКАЯ ПОДДЕРЖКА РАБОЧЕГО МЕСТА CIP (КТО-1)','Устранение сбоев на РМ',50)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА CIP (КТО-1)','Установка и настройка СПО/ППО на РМ',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-02 ОРГАНИЗАЦИЯ НОВОГО РАБОЧЕГО МЕСТА CIP (КТО-1)','Установка, настройка оборудования нового РМ. подключение и настройка ПУ и СПД. Установка и настройка СПО/ППО на РМ. Перенос данных',90)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-03 РЕМОНТ РАБОЧЕГО МЕСТА CIP (КТО-1)','Диагностика оборудования РМ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-03 РЕМОНТ РАБОЧЕГО МЕСТА CIP (КТО-1)','Замена неисправных элементов оборудования РМ',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-03 РЕМОНТ РАБОЧЕГО МЕСТА CIP (КТО-1)','Крупно-узловая поблочная замена компонентов РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-03 РЕМОНТ РАБОЧЕГО МЕСТА CIP (КТО-1)','Подготовка технического заключения',35)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-19-03 РЕМОНТ РАБОЧЕГО МЕСТА CIP (КТО-1)','Прием и диагностика оборудования РМ',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Выполнение крупно-узлового ремонта',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Выполнение ППР на ПУ по графику',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Диагностика ПУ и КМТ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Замена ПУ из оперативного фонда Заказчика',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Замена расходных материалов (предоставляет Заказчик)',10)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Консультация по работе с ПУ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Модернизация ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Отключение ПУ',10)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Подготовка технического заключения',35)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Подключение, настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-20-01 ПЕРИФЕРИЯ БТО-1','Устранение сбоев ПУ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Выполнение крупно-узлового ремонта',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Выполнение ППР на ПУ по графику',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Диагностика ПУ и КМТ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Замена ПУ из оперативного фонда Заказчика',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Замена расходных материалов (включены в стоимость услуги)',10)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Консультация по работе ПУ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Модернизация ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Отключение ПУ',10)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Подключение, настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-21-01 ПЕРИФЕРИЯ БТО-2','Устранение сбоев ПУ',40)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Выполнение крупно-узлового ремонта',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Выполнение ППР на ПУ по графику',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Диагностика ПУ и КМТ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Замена расходных материалов (включены в стоимость услуги)',10)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Консультация по работе с ПУ',15)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Модернизация ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Отключение ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-01 СОПРОВОЖДЕНИЕ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Подключение, настройка ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-02 РЕМОНТ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Диагностика ПУ и КМТ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-02 РЕМОНТ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Замена неисправных элементов ПУ',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-02 РЕМОНТ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Крупно-узловая поблочная замена компонентов ПУ',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-02 РЕМОНТ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Подготовка технического заключения',30)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-23-02 РЕМОНТ ПЕРИФЕРИЙНЫХ УСТРОЙСТВ (КТО-2)','Прием и диагностика ПУ в СЦ ОСК',45)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','1 час - Выполнение работ по поручению руководителя',60)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','2 часа - Выполнение работ по поручению руководителя',120)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','3 часа - Выполнение работ по поручению руководителя',180)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','4 часа - Выполнение работ по поручению руководителя',240)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','5 часов - Выполнение работ по поручению руководителя',300)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','6 часов - Выполнение работ по поручению руководителя',360)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','7 часов - Выполнение работ по поручению руководителя',420)");
            db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','8 часов - Выполнение работ по поручению руководителя',480)");

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 10 && newVersion == 10)
            db.execSQL("UPDATE Works SET Date = substr(Date, 7, 4) || '.' || substr(Date, 4,2) || '.' || substr(Date, 1,2)");
        if (oldVersion == 10 && newVersion == 11)
            db.execSQL("UPDATE Works SET Date = substr(Date, 1, 4) || '-' || substr(Date, 6,2) || '-' || substr(Date, 9,2)");
        if (newVersion==12){
            db.beginTransaction();{
                try{
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','1 час - Выполнение работ по поручению руководителя',60)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','2 часа - Выполнение работ по поручению руководителя',120)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','3 часа - Выполнение работ по поручению руководителя',180)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','4 часа - Выполнение работ по поручению руководителя',240)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','5 часов - Выполнение работ по поручению руководителя',300)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','6 часов - Выполнение работ по поручению руководителя',360)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','7 часов - Выполнение работ по поручению руководителя',420)");
                    db.execSQL("INSERT INTO ServiceCatalog(Service,ShortDesc,TimeNorm) VALUES ('ОСК-26-01 ВНУТРЕННИЕ РАБОТЫ','8 часов - Выполнение работ по поручению руководителя',480)");
                    db.setTransactionSuccessful();
                } finally {
                  db.endTransaction();
                }
            }
       }


    }
}

