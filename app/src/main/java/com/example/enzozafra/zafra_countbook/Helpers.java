package com.example.enzozafra.zafra_countbook;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Helpers class contains helper functions used throughout the app.
 */
public class Helpers {

    /**
     * Checks if the input in a textbox is empty
     * @param textbox
     * @return
     */
    public static boolean verifyTextBox(TextView textbox) {
        return textbox.getText().toString().isEmpty();
    }

    /**
     * returns the index of a counter from a list of counters
     * @param list
     * @param counter
     * @return
     */
    public static int getCounterIndex(ArrayList<Counter> list, Counter counter) {
        for(Counter item : list) {
            if(item.equals(counter)) {
                return list.indexOf(item);
            }
        }
        return -1;
    }

    /**
     * Sets the date format to the given string format
     * @param date
     * @param format
     * @return
     */
    public static String setDateFormat(Date date, String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

}
