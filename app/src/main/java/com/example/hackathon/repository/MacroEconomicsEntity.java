package com.example.hackathon.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import kotlin.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation {
    public String value();
}


@Entity(tableName="macroeconomicstable")
public class MacroEconomicsEntity {
    @PrimaryKey
    @ColumnInfo(name="year")
    private Integer year;

    @Annotation("MEG1INDIA")
    @ColumnInfo(name="MEG1INDIA")
    private Float indiaGrowthRate;

    @Annotation("MEG1CHINA")
    @ColumnInfo(name="MEG1CHINA")
    private Float chinaGrowthRate;

    @Annotation("MEG1USA")
    @ColumnInfo(name="MEG1USA")
    private Float usaGrowthRate;

    @Annotation("MEG2INDIA")
    @ColumnInfo(name="MEG2INDIA")
    private Long indiaGDP;

    @Annotation("MEG2CHINA")
    @ColumnInfo(name="MEG2CHINA")
    private Long chinaGDP;

    @Annotation("MEG2USA")
    @ColumnInfo(name="MEG2USA")
    private Long usaGDP;

    @Annotation("MEG3INDIA")
    @ColumnInfo(name="MEG3INDIA")
    private Float indiaBalancePercentOfGDP;

    @Annotation("MEG3CHINA")
    @ColumnInfo(name="MEG3CHINA")
    private Float chinaBalancePercentOfGDP;

    @Annotation("MEG3USA")
    @ColumnInfo(name="MEG3USA")
    private Float usaBalancePercentOfGDP;

    @Annotation("MEG4INDIA")
    @ColumnInfo(name="MEG4INDIA")
    private Long indiaForeignDirectInvestment;

    @Annotation("MEG4CHINA")
    @ColumnInfo(name="MEG4CHINA")
    private Long chinaForeignDirectInvestment;

    @Annotation("MEG4USA")
    @ColumnInfo(name="MEG4USA")
    private Long usaForeignDirectInvestment;

    @Annotation("MEG5INDIA")
    @ColumnInfo(name="MEG5INDIA")
    private Float indiaForeignDirectInvestmentNetInflows;

    @Annotation("MEG5CHINA")
    @ColumnInfo(name="MEG5CHINA")
    private Float chinaForeignDirectInvestmentNetInflows;

    @Annotation("MEG5USA")
    @ColumnInfo(name="MEG5USA")
    private Float usaForeignDirectInvestmentNetInflows;

    @Annotation("MEG6INDIA")
    @ColumnInfo(name="MEG6INDIA")
    private Float indiaNetOutflows;

    @Annotation("MEG6CHINA")
    @ColumnInfo(name="MEG6CHINA")
    private Float chinaNetOutflows;

    @Annotation("MEG6USA")
    @ColumnInfo(name="MEG6USA")
    private Float usaNetOutflows;

    public Integer getYear() {
        return year;
    }

    public Long getValue(String countryCode, String graphCode) {
        String key = graphCode + countryCode;
        for (Field field : ((Object) this).getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(Annotation.class)) {
                Annotation annotation = field.getAnnotation(Annotation.class);
                if (annotation.value() == key) {
                    try {
                        return (Long) field.get(this);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
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
