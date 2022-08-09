package com.example.chelseafc.model;

import java.io.Serializable;

public class FirstTeam implements Serializable {
    private String namePlayer;
    private int number;
    private int imgPlayer;
    private String nationalPlayer;
    private String DOB;
    private String position;

    public FirstTeam(String namePlayer, int number, int imgPlayer, String nationalPlayer, String DOB, String position) {
        this.namePlayer = namePlayer;
        this.number = number;
        this.imgPlayer = imgPlayer;
        this.nationalPlayer = nationalPlayer;
        this.DOB = DOB;
        this.position = position;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public int getNumber() {
        return number;
    }

    public int getImgPlayer() {
        return imgPlayer;
    }

    public String getNationalPlayer() {
        return nationalPlayer;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPosition() {
        return position;
    }
}
