package com.example.enzozafra.zafra_countbook;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Counter class is a Model class that represents the object for the UI.
 *
 * This class implements serializable to be able to transfer data from one activity to another
 * and also gives the ability to save data into a local file.
 */
public class Counter implements Serializable {
    private String name;
    private Date dateEdited;
    private Integer currentValue;
    private Integer initialValue;
    private String comment;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Counter constructor if the user does not supply a comment.
     * Will set the current value as the initial value and the date as the current date.
     * @param name
     * @param initialValue
     */
    public Counter(String name, Integer initialValue) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        String unformattedDate = new Date().toString();
        try {
            this.dateEdited = format.parse(unformattedDate);
        } catch (java.text.ParseException e) {
            this.dateEdited = new Date();
        }
    }

    /**
     * Counter constructor if the user supplies a comment.
     * Sets the current value = the initial value and the date as the current date.
     * @param name
     * @param initialValue
     * @param comment
     */
    public Counter(String name, Integer initialValue, String comment) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = initialValue;
        String unformattedDate = new Date().toString();
        try {
            this.dateEdited = format.parse(unformattedDate);
        } catch (java.text.ParseException e) {
            this.dateEdited = new Date();
        }
        this.comment = comment;
    }

    /**
     * Counter constructor if the user supplies a comment and a current value.
     * Sets the date as the current date.
     * @param name
     * @param initialValue
     * @param comment
     * @param currentValue
     */
    public Counter(String name, Integer initialValue, String comment, Integer currentValue) {
        this.name = name;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        String unformattedDate = new Date().toString();
        try {
            this.dateEdited = format.parse(unformattedDate);
        } catch (java.text.ParseException e) {
            this.dateEdited = new Date();
        }
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

    /**
     * Increments the current value of the counter
     */
    public void incCounter() {
        this.currentValue++;
    }

    /**
     * Decrements the current value of the counter
     */
    public void decCounter() {
        this.currentValue--;
    }

    /**
     * Checks if two Counter objects are equal to each other
     * @param obj
     * @return
     */
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
