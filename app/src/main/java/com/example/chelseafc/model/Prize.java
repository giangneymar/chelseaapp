package com.example.chelseafc.model;

import java.io.Serializable;

public class Prize implements Serializable {
    private String namePrize;
    private String yearPrize;
    private int imgPrize;

    public Prize(String namePrize, String yearPrize, int imgPrize) {
        this.namePrize = namePrize;
        this.yearPrize = yearPrize;
        this.imgPrize = imgPrize;
    }

    public String getNamePrize() {
        return namePrize;
    }

    public String getYearPrize() {
        return yearPrize;
    }

    public int getImgPrize() {
        return imgPrize;
    }
}
