package com.example.hackathon.repository.aggriculture;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface FertilizerDao {
    @Query("SELECT * FROM fertilizer WHERE year BETWEEN :startYear AND :endYear")
    List<ValueAddEntity> findFertilizer(int startYear, int endYear);

    @Insert
    void insertFertilizer(ValueAddEntity valueAddEntity);
}
