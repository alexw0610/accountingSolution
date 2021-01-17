package com.app.expense;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="expense_id")
    private int id;

    @Column(name="expense_value")
    private float value;

    @Column(name="expense_reoccurring")
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
