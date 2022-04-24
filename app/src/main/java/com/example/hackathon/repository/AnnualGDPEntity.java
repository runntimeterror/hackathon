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
    private Long indiaGDP;

    @ColumnInfo(name="china")
    private Long chinaGDP;

    @ColumnInfo(name="usa")
    private Long usaGDP;

    public Integer getYear() {
        return year;
    }

    public Long getIndiaGDP() {
        return indiaGDP;
    }

    public Long getChinaGDP() {
        return chinaGDP;
    }

    public Long getUsaGDP() {
        return usaGDP;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setIndiaGDP(Long indiaGDP) {
        this.indiaGDP = indiaGDP;
    }

    public void setChinaGDP(Long chinaGDP) {
        this.chinaGDP = chinaGDP;
    }

    public void setUsaGDP(Long usaGDP) {
        this.usaGDP = usaGDP;
    }
}
