package com.example.hackathon.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnnualGDPDao {
    @Query("SELECT * FROM annualgdps WHERE year BETWEEN :startYear AND :endYear")
    List<AnnualGDPEntity> findAnnualGDP(int startYear, int endYear);

    @Insert
    void insertAnnualGDP(AnnualGDPEntity annualGDPEntity);
}
