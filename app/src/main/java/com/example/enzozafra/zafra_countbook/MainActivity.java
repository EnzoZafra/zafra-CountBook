package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate list
        ArrayList<Counter> list = new ArrayList<>();

        // Tests - Delete after
        Counter testcounter1 = new Counter("Test counter 1", 12, "here is a comment");
        Counter testcounter2 = new Counter("This counter should have no comment", 0);
        list.add(testcounter1);
        list.add(testcounter2);

        //instantiate custom adapter
        ListItemAdapter adapter = new ListItemAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.counter_listview);
        lView.setAdapter(adapter);
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
