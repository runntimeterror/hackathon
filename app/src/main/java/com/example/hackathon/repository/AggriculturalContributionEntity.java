package com.example.hackathon.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="aggriculturalcontribution")
public class AggriculturalContributionEntity {
    @PrimaryKey
    @ColumnInfo(name="year")
    private Integer year;

    @ColumnInfo(name="india")
    private Float india;

    @ColumnInfo(name="china")
    private Float china;

    @ColumnInfo(name="usa")
    private Float usa;

    public AggriculturalContributionEntity(Integer year, Float indiaGDP, Float chinaGDP, Float usaGDP) {
        this.year = year;
        this.india = indiaGDP;
        this.china = chinaGDP;
        this.usa = usaGDP;
    }

    public Integer getYear() {
        return year;
    }

    public Float getIndiaGDP() {
        return india;
    }

    public Float getChinaGDP() {
        return china;
    }

    public Float getUsaGDP() {
        return usa;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setIndiaGDP(Float indiaGDP) {
        this.india = indiaGDP;
    }

    public void setChinaGDP(Float chinaGDP) {
        this.china = chinaGDP;
    }

    public void setUsaGDP(Float usaGDP) {
        this.usa = usaGDP;
    }
}
