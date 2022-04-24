package com.example.hackathon.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hackathon.repository.aggriculture.FertilizerDao;
import com.example.hackathon.repository.aggriculture.FertilizerEntity;
import com.example.hackathon.repository.aggriculture.ValueAddDao;
import com.example.hackathon.repository.aggriculture.ValueAddEntity;
import com.example.hackathon.repository.debt.DebtServiceDao;
import com.example.hackathon.repository.debt.DebtServiceEntity;
import com.example.hackathon.repository.debt.TotalReservesDao;
import com.example.hackathon.repository.debt.TotalReservesEntity;

import java.util.concurrent.Executors;

@Database(entities = {AnnualGDPEntity.class, CurrentAccountBalanceEntity.class, ValueAddEntity.class, FertilizerEntity.class, DebtServiceEntity.class, TotalReservesEntity.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    /** TODO: Put file into emulated local storage
     *      Get file from local storage
     *      If sqlite database is not present, initialize database
     *          Copy data from local storage into the database
     *      If database is present
     *          reference the database
     */

    public abstract AnnualGDPDao annualGDPDao();
    public abstract CurrentAccountBalanceDao currentAccountBalanceDao();
    public abstract ValueAddDao valueAddDao();
    public abstract FertilizerDao fertilizerDao();
    public abstract DebtServiceDao debtServiceDao();
    public abstract TotalReservesDao totalReservesDao();
    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    Builder dbBuilder = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class,
                            "app_database.db");

                    dbBuilder.setQueryCallback((query, bindArgs) -> {
                        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "SQL Query:" + query + "SQL Args" + bindArgs);
                    }, Executors.newSingleThreadExecutor());

                    Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "Returning Instance of DB");
                    return (AppRoomDatabase) dbBuilder.build();
                }

            }
        }

        return INSTANCE;
    }
}
