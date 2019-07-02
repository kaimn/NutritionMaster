package com.example.administrator.nutritionmaster.entity;

/**
 * Created by Administrator on 2018/3/9.
 */

import java.io.Serializable;
import java.util.Date;

public class Userbean implements Serializable{
    private int id;
    private String username;
    private String password;
    private String nikename;
    private String email;
    private Date birthday;
    private Date registerdate;
    private Date lastlogindate;
    private String height;
    private String weight;
    private String gold;
    private String defineheat;
    private String phone;
    private String pic;
    private String sex;
    private String declaration;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNikename() {
        return nikename;
    }
    public void setNikename(String nikename) {
        this.nikename = nikename;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Date getRegisterdate() {
        return registerdate;
    }
    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }
    public Date getLastlogindate() {
        return lastlogindate;
    }
    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getGold() {
        return gold;
    }
    public void setGold(String gold) {
        this.gold = gold;
    }
    public String getDefineheat() {
        return defineheat;
    }
    public void setDefineheat(String defineheat) {
        this.defineheat = defineheat;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getDeclaration() {
        return declaration;
    }
    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }
    @Override
    public String toString() {
        return "Userbean [id=" + id + ", username=" + username + ", password="
                + password + ", nikename=" + nikename + ", email=" + email
                + ", birthday=" + birthday + ", registerdate=" + registerdate
                + ", lastlogindate=" + lastlogindate + ", height=" + height
                + ", weight=" + weight + ", gold=" + gold + ", defineheat="
                + defineheat + ", phone=" + phone + ", pic=" + pic + ", sex="
                + sex + ", declaration=" + declaration + "]";
    }

}

