package com.example.hackathon.repository.aggriculture;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hackathon.repository.AnnualGDPEntity;

import java.util.List;

@Dao
public interface ValueAddDao {
    @Query("SELECT * FROM valueadd WHERE year BETWEEN :startYear AND :endYear")
    List<ValueAddEntity> findValueAdd(int startYear, int endYear);

    @Insert
    void insertValueAdd(ValueAddEntity valueAddEntity);
}
