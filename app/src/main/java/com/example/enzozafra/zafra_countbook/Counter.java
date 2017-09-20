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
        this.dateEdited = new Date();
    }

    public Counter(String name, Integer initialValue, String comment) {
        this.name = name;
        this.initialValue = initialValue;
        this.dateEdited = new Date();
        this.comment = comment;

    }
}
