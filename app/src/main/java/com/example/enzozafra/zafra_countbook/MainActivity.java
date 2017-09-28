package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Counter> list = new ArrayList<>();
    private ListItemAdapter adapter;
    private static int NEW_COUNTER_ACTIVITY_REQUEST_CODE = 1;
    private static int EDIT_COUNTER_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate custom adapter
        adapter = new ListItemAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.counter_listview);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(MainActivity.this, EditCounterActivity.class);
                Counter selectedCounter = (Counter)adapter.getItemAtPosition(position);
                intent.putExtra("EDIT_COUNTER", selectedCounter);
                intent.putExtra("COUNTER_LIST", list);
                startActivityForResult(intent, EDIT_COUNTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_COUNTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Counter newCounter = (Counter) data.getSerializableExtra("NEW_COUNTER");
            list.add(newCounter);
            adapter.notifyDataSetChanged();
        } else if (requestCode == EDIT_COUNTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int oldCounterIndex = data.getIntExtra("OLD_COUNTER_INDEX", -1);
            Counter editedCounter = (Counter) data.getSerializableExtra("EDIT_COUNTER");

            if (oldCounterIndex != -1) {
                list.set(oldCounterIndex, editedCounter);
            }
            adapter.notifyDataSetChanged();
        }
    }

    /** Called when the user clicks the "+" button */
    public void newCounter(View view) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent, NEW_COUNTER_ACTIVITY_REQUEST_CODE);
    }
}
