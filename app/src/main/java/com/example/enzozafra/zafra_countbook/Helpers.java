package com.example.enzozafra.zafra_countbook;

import android.widget.TextView;
import java.util.ArrayList;

public class Helpers {

    public static boolean verifyTextBox(TextView textbox) {
        return textbox.getText().toString().isEmpty();
    }

    public static int getCounterIndex(ArrayList<Counter> list, Counter counter) {
        for(Counter item : list) {
            if(item.equals(counter)) {
                return list.indexOf(item);
            }
        }
        return -1;
    }

}
