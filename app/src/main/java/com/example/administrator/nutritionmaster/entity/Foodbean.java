package com.example.administrator.nutritionmaster.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.
 */

public class Foodbean implements Serializable{
    private int id;
    private String name;
    private float heat;
    private float calcium;
    private float protein;
    private float fat;
    private float carbohydrate;
    private float vitaminc;
    private float df;
    private float vitamine;
    private float vitamina;
    private float cholesterol;
    private float na;
    private float type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeat() {
        return heat;
    }

    public void setHeat(float heat) {
        this.heat = heat;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public float getVitaminc() {
        return vitaminc;
    }

    public void setVitaminc(float vitaminc) {
        this.vitaminc = vitaminc;
    }

    public float getDf() {
        return df;
    }

    public void setDf(float df) {
        this.df = df;
    }

    public float getVitamine() {
        return vitamine;
    }

    public void setVitamine(float vitamine) {
        this.vitamine = vitamine;
    }

    public float getVitamina() {
        return vitamina;
    }

    public void setVitamina(float vitamina) {
        this.vitamina = vitamina;
    }

    public float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public float getNa() {
        return na;
    }

    public void setNa(float na) {
        this.na = na;
    }

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Foodbean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heat=" + heat +
                ", calcium=" + calcium +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                ", vitaminc=" + vitaminc +
                ", df=" + df +
                ", vitamine=" + vitamine +
                ", vitamina=" + vitamina +
                ", cholesterol=" + cholesterol +
                ", na=" + na +
                ", type=" + type +
                '}';
    }
}
