package com.example.hackathon.repository.debt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hackathon.repository.aggriculture.FertilizerEntity;
import com.example.hackathon.repository.aggriculture.ValueAddEntity;

import java.util.List;

@Dao
public interface TotalReservesDao {
    @Query("SELECT * FROM fertilizer WHERE year BETWEEN :startYear AND :endYear")
    List<DebtServiceEntity> findTotalReserves(int startYear, int endYear);

    @Insert
    void insertTotalReserves(TotalReservesEntity totalReservesEntity);
}
