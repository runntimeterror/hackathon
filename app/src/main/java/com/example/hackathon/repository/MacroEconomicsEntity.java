package com.example.hackathon.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="macroeconomicstable")
public class MacroEconomicsEntity {
    @PrimaryKey
    @ColumnInfo(name="year")
    private Integer year;

    @ColumnInfo(name="MEG1INDIA")
    private Float indiaGrowthRate;

    @ColumnInfo(name="MEG1CHINA")
    private Float chinaGrowthRate;

    @ColumnInfo(name="MEG1USA")
    private Float usaGrowthRate;

    @ColumnInfo(name="MEG2INDIA")
    private Long indiaGDP;

    @ColumnInfo(name="MEG2CHINA")
    private Long chinaGDP;

    @ColumnInfo(name="MEG2USA")
    private Long usaGDP;

    @ColumnInfo(name="MEG3INDIA")
    private Float indiaBalancePercentOfGDP;

    @ColumnInfo(name="MEG3CHINA")
    private Float chinaBalancePercentOfGDP;

    @ColumnInfo(name="MEG3USA")
    private Float usaBalancePercentOfGDP;

    @ColumnInfo(name="MEG4INDIA")
    private Long indiaForeignDirectInvestment;

    @ColumnInfo(name="MEG4CHINA")
    private Long chinaForeignDirectInvestment;

    @ColumnInfo(name="MEG4USA")
    private Long usaForeignDirectInvestment;

    @ColumnInfo(name="MEG5INDIA")
    private Float indiaForeignDirectInvestmentNetInflows;

    @ColumnInfo(name="MEG5CHINA")
    private Float chinaForeignDirectInvestmentNetInflows;

    @ColumnInfo(name="MEG5USA")
    private Float usaForeignDirectInvestmentNetInflows;

    @ColumnInfo(name="MEG6INDIA")
    private Float indiaNetOutflows;

    @ColumnInfo(name="MEG6CHINA")
    private Float chinaNetOutflows;

    @ColumnInfo(name="MEG6USA")
    private Float usaNetOutflows;

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

    public Float getIndiaGrowthRate() {
        return indiaGrowthRate;
    }

    public Float getChinaGrowthRate() {
        return chinaGrowthRate;
    }

    public Float getUsaGrowthRate() {
        return usaGrowthRate;
    }

    public Float getIndiaBalancePercentOfGDP() {
        return indiaBalancePercentOfGDP;
    }

    public Float getChinaBalancePercentOfGDP() {
        return chinaBalancePercentOfGDP;
    }

    public Float getUsaBalancePercentOfGDP() {
        return usaBalancePercentOfGDP;
    }

    public Long getIndiaForeignDirectInvestment() {
        return indiaForeignDirectInvestment;
    }

    public Long getChinaForeignDirectInvestment() {
        return chinaForeignDirectInvestment;
    }

    public Long getUsaForeignDirectInvestment() {
        return usaForeignDirectInvestment;
    }

    public Float getIndiaForeignDirectInvestmentNetInflows() {
        return indiaForeignDirectInvestmentNetInflows;
    }

    public Float getChinaForeignDirectInvestmentNetInflows() {
        return chinaForeignDirectInvestmentNetInflows;
    }

    public Float getUsaForeignDirectInvestmentNetInflows() {
        return usaForeignDirectInvestmentNetInflows;
    }

    public Float getIndiaNetOutflows() {
        return indiaNetOutflows;
    }

    public Float getChinaNetOutflows() {
        return chinaNetOutflows;
    }

    public Float getUsaNetOutflows() {
        return usaNetOutflows;
    }

    public void setIndiaGrowthRate(Float indiaGrowthRate) {
        this.indiaGrowthRate = indiaGrowthRate;
    }

    public void setChinaGrowthRate(Float chinaGrowthRate) {
        this.chinaGrowthRate = chinaGrowthRate;
    }

    public void setUsaGrowthRate(Float usaGrowthRate) {
        this.usaGrowthRate = usaGrowthRate;
    }

    public void setIndiaBalancePercentOfGDP(Float indiaBalancePercentOfGDP) {
        this.indiaBalancePercentOfGDP = indiaBalancePercentOfGDP;
    }

    public void setChinaBalancePercentOfGDP(Float chinaBalancePercentOfGDP) {
        this.chinaBalancePercentOfGDP = chinaBalancePercentOfGDP;
    }

    public void setUsaBalancePercentOfGDP(Float usaBalancePercentOfGDP) {
        this.usaBalancePercentOfGDP = usaBalancePercentOfGDP;
    }

    public void setIndiaForeignDirectInvestment(Long indiaForeignDirectInvestment) {
        this.indiaForeignDirectInvestment = indiaForeignDirectInvestment;
    }

    public void setChinaForeignDirectInvestment(Long chinaForeignDirectInvestment) {
        this.chinaForeignDirectInvestment = chinaForeignDirectInvestment;
    }

    public void setUsaForeignDirectInvestment(Long usaForeignDirectInvestment) {
        this.usaForeignDirectInvestment = usaForeignDirectInvestment;
    }

    public void setIndiaForeignDirectInvestmentNetInflows(Float indiaForeignDirectInvestmentNetInflows) {
        this.indiaForeignDirectInvestmentNetInflows = indiaForeignDirectInvestmentNetInflows;
    }

    public void setChinaForeignDirectInvestmentNetInflows(Float chinaForeignDirectInvestmentNetInflows) {
        this.chinaForeignDirectInvestmentNetInflows = chinaForeignDirectInvestmentNetInflows;
    }

    public void setUsaForeignDirectInvestmentNetInflows(Float usaForeignDirectInvestmentNetInflows) {
        this.usaForeignDirectInvestmentNetInflows = usaForeignDirectInvestmentNetInflows;
    }

    public void setIndiaNetOutflows(Float indiaNetOutflows) {
        this.indiaNetOutflows = indiaNetOutflows;
    }

    public void setChinaNetOutflows(Float chinaNetOutflows) {
        this.chinaNetOutflows = chinaNetOutflows;
    }

    public void setUsaNetOutflows(Float usaNetOutflows) {
        this.usaNetOutflows = usaNetOutflows;
    }
}
