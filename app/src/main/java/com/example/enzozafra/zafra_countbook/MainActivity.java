package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Counter> list = new ArrayList<>();
    private ListItemAdapter adapter;
    private static int SECOND_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate custom adapter
        adapter = new ListItemAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.counter_listview);
        lView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Counter newCounter = (Counter) data.getSerializableExtra("NEW_COUNTER");
            list.add(newCounter);
            adapter.notifyDataSetChanged();
            Log.d("inif", newCounter.toString());
            Log.d("inif", list.toString());
        }
    }

    /** Called when the user clicks the "+" button */
    public void newCounter(View view) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }
}
