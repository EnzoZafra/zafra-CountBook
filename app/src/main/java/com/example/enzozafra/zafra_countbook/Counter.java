package com.example.enzozafra.zafra_countbook;

import java.util.Date;

public class Counter {
    private String name;
    private Date dateEdited;
    private Integer currentValue;
    private Integer initialValue;
    private String comment;

    public Counter(String name, Integer initialValue) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.dateEdited = new Date();
    }

    public Counter(String name, Integer initialValue, String comment) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.dateEdited = new Date();
        this.comment = comment;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.dateEdited;
    }

    public Integer getCurrentValue() {
        return this.currentValue;
    }

    public Integer getInitialValue() {
        return this.initialValue;
    }

    public String getComment() {
        return this.comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialValue(Integer value) {
        this.initialValue = value;
    }

    public void setCurrentValue(Integer value) {
        this.currentValue = value;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void incCounter() {
        this.currentValue++;
    }

    public void decCounter() {
        this.currentValue--;
    }
}
