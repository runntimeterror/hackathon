package com.example.hackathon.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MacroEconomicsDao {
    @Query("SELECT * FROM macroeconomicstable WHERE year BETWEEN :startYear AND :endYear")
    List<MacroEconomicsEntity> findAnnualGDP(int startYear, int endYear);

    @Insert
    void insertAnnualGDP(MacroEconomicsEntity macroEconomicsEntity);
}
