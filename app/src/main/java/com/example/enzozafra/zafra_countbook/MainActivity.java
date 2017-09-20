package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the "+" button */
    public void newCounter(View view) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        /** TODO:
         * Add extra things? maybe? not sure...
         */


        startActivity(intent);
    }
}
