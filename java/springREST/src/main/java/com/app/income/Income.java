package com.app.income;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;


@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="income_id")
    private int id;

    @Column(name="income_value")
    private float value;

    @Column(name="income_reoccurring")
    private boolean reoccurring;

    @Column(name="creation_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isReoccurring() {
        return reoccurring;
    }

    public void setReoccurring(boolean reoccurring) {
        this.reoccurring = reoccurring;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
