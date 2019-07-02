package com.example.administrator.nutritionmaster.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/26.
 */

public class Dietbean {
    private int id;
    private String foodname;
    private String foodheat;
    private String amountheat;
    private String time;
    private int foodid;
    private Date date;
    private int count;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFoodname() {
        return foodname;
    }
    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }
    public String getFoodheat() {
        return foodheat;
    }
    public void setFoodheat(String foodheat) {
        this.foodheat = foodheat;
    }
    public String getAmountheat() {
        return amountheat;
    }
    public void setAmountheat(String amountheat) {
        this.amountheat = amountheat;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getFoodid() {
        return foodid;
    }
    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Dietbean{" +
                "id=" + id +
                ", foodname='" + foodname + '\'' +
                ", foodheat='" + foodheat + '\'' +
                ", amountheat='" + amountheat + '\'' +
                ", time='" + time + '\'' +
                ", foodid=" + foodid +
                ", date=" + date +
                ", count=" + count +
                '}';
    }
}
