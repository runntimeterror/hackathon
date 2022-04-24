package com.example.hackathon.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Field;

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

    public Long getValue(String countryCode, String graphCode) {
        String key = graphCode + countryCode;
        for (Field field : ((Object) this).getClass().getDeclaredFields()) {
            String annotation = field.getAnnotation(ColumnInfo.class).toString();
            if (annotation == key) {
                try {
                    return (Long) field.get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return 100L;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getIndiaGrowthRate() {
        return indiaGrowthRate;
    }

    public MacroEconomicsEntity(Integer year, Float indiaGrowthRate, Float chinaGrowthRate, Float usaGrowthRate, Long indiaGDP, Long chinaGDP, Long usaGDP, Float indiaBalancePercentOfGDP, Float chinaBalancePercentOfGDP, Float usaBalancePercentOfGDP, Long indiaForeignDirectInvestment, Long chinaForeignDirectInvestment, Long usaForeignDirectInvestment, Float indiaForeignDirectInvestmentNetInflows, Float chinaForeignDirectInvestmentNetInflows, Float usaForeignDirectInvestmentNetInflows, Float indiaNetOutflows, Float chinaNetOutflows, Float usaNetOutflows) {
        this.year = year;
        this.indiaGrowthRate = indiaGrowthRate;
        this.chinaGrowthRate = chinaGrowthRate;
        this.usaGrowthRate = usaGrowthRate;
        this.indiaGDP = indiaGDP;
        this.chinaGDP = chinaGDP;
        this.usaGDP = usaGDP;
        this.indiaBalancePercentOfGDP = indiaBalancePercentOfGDP;
        this.chinaBalancePercentOfGDP = chinaBalancePercentOfGDP;
        this.usaBalancePercentOfGDP = usaBalancePercentOfGDP;
        this.indiaForeignDirectInvestment = indiaForeignDirectInvestment;
        this.chinaForeignDirectInvestment = chinaForeignDirectInvestment;
        this.usaForeignDirectInvestment = usaForeignDirectInvestment;
        this.indiaForeignDirectInvestmentNetInflows = indiaForeignDirectInvestmentNetInflows;
        this.chinaForeignDirectInvestmentNetInflows = chinaForeignDirectInvestmentNetInflows;
        this.usaForeignDirectInvestmentNetInflows = usaForeignDirectInvestmentNetInflows;
        this.indiaNetOutflows = indiaNetOutflows;
        this.chinaNetOutflows = chinaNetOutflows;
        this.usaNetOutflows = usaNetOutflows;
    }

    public void setIndiaGrowthRate(Float indiaGrowthRate) {
        this.indiaGrowthRate = indiaGrowthRate;
    }

    public Float getChinaGrowthRate() {
        return chinaGrowthRate;
    }

    public void setChinaGrowthRate(Float chinaGrowthRate) {
        this.chinaGrowthRate = chinaGrowthRate;
    }

    public Float getUsaGrowthRate() {
        return usaGrowthRate;
    }

    public void setUsaGrowthRate(Float usaGrowthRate) {
        this.usaGrowthRate = usaGrowthRate;
    }

    public Long getIndiaGDP() {
        return indiaGDP;
    }

    public void setIndiaGDP(Long indiaGDP) {
        this.indiaGDP = indiaGDP;
    }

    public Long getChinaGDP() {
        return chinaGDP;
    }

    public void setChinaGDP(Long chinaGDP) {
        this.chinaGDP = chinaGDP;
    }

    public Long getUsaGDP() {
        return usaGDP;
    }

    public void setUsaGDP(Long usaGDP) {
        this.usaGDP = usaGDP;
    }

    public Float getIndiaBalancePercentOfGDP() {
        return indiaBalancePercentOfGDP;
    }

    public void setIndiaBalancePercentOfGDP(Float indiaBalancePercentOfGDP) {
        this.indiaBalancePercentOfGDP = indiaBalancePercentOfGDP;
    }

    public Float getChinaBalancePercentOfGDP() {
        return chinaBalancePercentOfGDP;
    }

    public void setChinaBalancePercentOfGDP(Float chinaBalancePercentOfGDP) {
        this.chinaBalancePercentOfGDP = chinaBalancePercentOfGDP;
    }

    public Float getUsaBalancePercentOfGDP() {
        return usaBalancePercentOfGDP;
    }

    public void setUsaBalancePercentOfGDP(Float usaBalancePercentOfGDP) {
        this.usaBalancePercentOfGDP = usaBalancePercentOfGDP;
    }

    public Long getIndiaForeignDirectInvestment() {
        return indiaForeignDirectInvestment;
    }

    public void setIndiaForeignDirectInvestment(Long indiaForeignDirectInvestment) {
        this.indiaForeignDirectInvestment = indiaForeignDirectInvestment;
    }

    public Long getChinaForeignDirectInvestment() {
        return chinaForeignDirectInvestment;
    }

    public void setChinaForeignDirectInvestment(Long chinaForeignDirectInvestment) {
        this.chinaForeignDirectInvestment = chinaForeignDirectInvestment;
    }

    public Long getUsaForeignDirectInvestment() {
        return usaForeignDirectInvestment;
    }

    public void setUsaForeignDirectInvestment(Long usaForeignDirectInvestment) {
        this.usaForeignDirectInvestment = usaForeignDirectInvestment;
    }

    public Float getIndiaForeignDirectInvestmentNetInflows() {
        return indiaForeignDirectInvestmentNetInflows;
    }

    public void setIndiaForeignDirectInvestmentNetInflows(Float indiaForeignDirectInvestmentNetInflows) {
        this.indiaForeignDirectInvestmentNetInflows = indiaForeignDirectInvestmentNetInflows;
    }

    public Float getChinaForeignDirectInvestmentNetInflows() {
        return chinaForeignDirectInvestmentNetInflows;
    }

    public void setChinaForeignDirectInvestmentNetInflows(Float chinaForeignDirectInvestmentNetInflows) {
        this.chinaForeignDirectInvestmentNetInflows = chinaForeignDirectInvestmentNetInflows;
    }

    public Float getUsaForeignDirectInvestmentNetInflows() {
        return usaForeignDirectInvestmentNetInflows;
    }

    public void setUsaForeignDirectInvestmentNetInflows(Float usaForeignDirectInvestmentNetInflows) {
        this.usaForeignDirectInvestmentNetInflows = usaForeignDirectInvestmentNetInflows;
    }

    public Float getIndiaNetOutflows() {
        return indiaNetOutflows;
    }

    public void setIndiaNetOutflows(Float indiaNetOutflows) {
        this.indiaNetOutflows = indiaNetOutflows;
    }

    public Float getChinaNetOutflows() {
        return chinaNetOutflows;
    }

    public void setChinaNetOutflows(Float chinaNetOutflows) {
        this.chinaNetOutflows = chinaNetOutflows;
    }

    public Float getUsaNetOutflows() {
        return usaNetOutflows;
    }

    public void setUsaNetOutflows(Float usaNetOutflows) {
        this.usaNetOutflows = usaNetOutflows;
    }
}
