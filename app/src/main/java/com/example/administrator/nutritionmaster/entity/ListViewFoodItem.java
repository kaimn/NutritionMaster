package com.example.administrator.nutritionmaster.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/5.
 */

public class ListViewFoodItem implements Serializable{
    private String id;
    private String foodName;
    private String foodHeat;
    private String heatAmount;
    private int foodId;
    private int count;



    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getFoodHeat() {
        return foodHeat;
    }

    public void setFoodHeat(String foodHeat) {
        this.foodHeat = foodHeat;
    }

    public String getHeatAmount() {
        return heatAmount;
    }

    public void setHeatAmount(String heatAmount) {
        this.heatAmount = heatAmount;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ListViewFoodItem{" +
                "id='" + id + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodHeat='" + foodHeat + '\'' +
                ", heatAmount='" + heatAmount + '\'' +
                ", foodId=" + foodId +
                ", count=" + count +
                ", time=" + time +
                '}';
    }
}
