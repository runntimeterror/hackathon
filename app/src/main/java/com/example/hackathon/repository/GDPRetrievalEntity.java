package com.example.hackathon.repository;

import androidx.room.ColumnInfo;

public class GDPRetrievalEntity implements MacroEconInterface {
    @ColumnInfo(name="MEG2INDIA")
    private Long indiaGDP;

    @ColumnInfo(name="MEG2CHINA")
    private Long chinaGDP;

    @ColumnInfo(name="MEG2USA")
    private Long usaGDP;

    @Override
    public Long getIndia() {
        return indiaGDP;
    }

    @Override
    public Long getChina() {
        return chinaGDP;
    }

    @Override
    public Long getUSA() {
        return usaGDP;
    }
}
