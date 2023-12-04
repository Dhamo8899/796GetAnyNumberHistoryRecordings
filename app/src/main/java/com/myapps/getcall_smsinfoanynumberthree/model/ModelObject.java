package com.myapps.getcall_smsinfoanynumberthree.model;

public class ModelObject {

    int img_icon;
    String tittle;
    String number;
    String time;

    public ModelObject(int img_icon, String tittle, String number, String time) {
        this.img_icon = img_icon;
        this.tittle = tittle;
        this.number = number;
        this.time = time;
    }

    public int getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(int img_icon) {
        this.img_icon = img_icon;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
