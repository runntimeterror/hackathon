package com.example.hackathon.repository.aggriculture;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fertilizer")
public class FertilizerEntity {
    @PrimaryKey
    @ColumnInfo(name="year")
    private Integer year;

    @ColumnInfo(name="india")
    private Float indiaGDP;

    @ColumnInfo(name="china")
    private Float chinaGDP;

    @ColumnInfo(name="usa")
    private Float usaGDP;

    public FertilizerEntity(Integer year, Float indiaGDP, Float chinaGDP, Float usaGDP) {
        this.year = year;
        this.indiaGDP = indiaGDP;
        this.chinaGDP = chinaGDP;
        this.usaGDP = usaGDP;
    }

    public Integer getYear() {
        return year;
    }

    public Float getIndiaGDP() {
        return indiaGDP;
    }

    public Float getChinaGDP() {
        return chinaGDP;
    }

    public Float getUsaGDP() {
        return usaGDP;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setIndiaGDP(Float indiaGDP) {
        this.indiaGDP = indiaGDP;
    }

    public void setChinaGDP(Float chinaGDP) {
        this.chinaGDP = chinaGDP;
    }

    public void setUsaGDP(Float usaGDP) {
        this.usaGDP = usaGDP;
    }
}
