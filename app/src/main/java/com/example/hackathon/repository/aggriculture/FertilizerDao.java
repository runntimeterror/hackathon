package com.example.hackathon.repository.aggriculture;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FertilizerDao {
    @Query("SELECT * FROM fertilizer WHERE year BETWEEN :startYear AND :endYear")
    List<FertilizerEntity> findFertilizer(int startYear, int endYear);

    @Insert
    void insertFertilizer(FertilizerEntity valueAddEntity);
}
