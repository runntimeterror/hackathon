package com.example.hackathon.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="annualgdps")
public class AnnualGDPEntity {
    @PrimaryKey
    @ColumnInfo(name="year")
    private Integer year;

    @ColumnInfo(name="india")
    private Integer indiaGDP;

    @ColumnInfo(name="china")
    private Integer chinaGDP;

    @ColumnInfo(name="usa")
    private Integer usaGDP;

    public Integer getYear() {
        return year;
    }

    public Integer getIndiaGDP() {
        return indiaGDP;
    }

    public Integer getChinaGDP() {
        return chinaGDP;
    }

    public Integer getUsaGDP() {
        return usaGDP;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIndiaGDP(int indiaGDP) {
        this.indiaGDP = indiaGDP;
    }

    public void setChinaGDP(int chinaGDP) {
        this.chinaGDP = chinaGDP;
    }

    public void setUsaGDP(int usaGDP) {
        this.usaGDP = usaGDP;
    }
}
