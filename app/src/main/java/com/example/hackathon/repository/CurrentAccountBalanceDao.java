package com.example.hackathon.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrentAccountBalanceDao {
    @Query("SELECT * FROM currentaccountbalance WHERE year BETWEEN :startYear AND :endYear")
    List<CurrentAccountBalanceEntity> findCurrentAccountBalance(int startYear, int endYear);

    @Insert
    void insertCurrentAccountBalance(CurrentAccountBalanceEntity currentAccountBalanceEntity);
}
