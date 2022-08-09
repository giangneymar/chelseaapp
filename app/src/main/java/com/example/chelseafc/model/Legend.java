package com.example.chelseafc.model;

import java.io.Serializable;

public class Legend implements Serializable {
    private String nameLegend;
    private String DOB;
    private int number;
    private int imgLegend;
    private String national;
    private int numberMatch;
    private String season;

    public Legend(String nameLegend, String DOB, int number, int imgLegend, String national, int numberMatch, String season) {
        this.nameLegend = nameLegend;
        this.DOB = DOB;
        this.number = number;
        this.imgLegend = imgLegend;
        this.national = national;
        this.numberMatch = numberMatch;
        this.season = season;
    }

    public String getNameLegend() {
        return nameLegend;
    }

    public String getDOB() {
        return DOB;
    }

    public int getNumber() {
        return number;
    }

    public int getImgLegend() {
        return imgLegend;
    }

    public String getNational() {
        return national;
    }

    public int getNumberMatch() {
        return numberMatch;
    }

    public String getSeason() {
        return season;
    }
}
