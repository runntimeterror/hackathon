package com.example.hackathon.repository.debt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hackathon.repository.aggriculture.FertilizerEntity;
import com.example.hackathon.repository.aggriculture.ValueAddEntity;

import java.util.List;

@Dao
public interface DebtServiceDao {
    @Query("SELECT * FROM fertilizer WHERE year BETWEEN :startYear AND :endYear")
    List<DebtServiceEntity> findDebtService(int startYear, int endYear);

    @Insert
    void insertDebtService(DebtServiceEntity valueAddEntity);
}
