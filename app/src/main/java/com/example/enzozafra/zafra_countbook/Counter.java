package com.example.enzozafra.zafra_countbook;

import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;

public class Counter implements Serializable {
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

    public Counter(String name, Integer initialValue, String comment, Integer currentValue) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
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

    // For testing purposes
    @Override
    public String toString() {
        return "Name: " + name + "; currentValue: " + currentValue.toString() +
                "; initialValue: " + initialValue.toString() + "; comment: " + comment +
                "; dateEdited: " + dateEdited.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Counter.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Counter other = (Counter) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.initialValue.equals(other.initialValue)) {
            return false;
        }
        if (!this.currentValue.equals(other.currentValue)) {
            return false;
        }
        if ((this.comment != null) && (other.comment != null) && !this.comment.equals(other.comment)) {
            return false;
        }
        return true;
    }
}
