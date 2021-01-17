package com.app.history;


import com.fasterxml.jackson.annotation.JsonFormat;


public class HistoryData {

    private float value;

    @JsonFormat(pattern="yyyy-MM-dd")
    private String date;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
