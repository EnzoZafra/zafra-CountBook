package com.example.enzozafra.zafra_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "countBook.sav";

    private ArrayList<Counter> counters = new ArrayList<>();
    private ListItemAdapter adapter;
    private static int NEW_COUNTER_ACTIVITY_REQUEST_CODE = 1;
    private static int EDIT_COUNTER_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load old Counters
        loadFromFile();

        //instantiate custom adapter
        adapter = new ListItemAdapter(counters, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.counter_listview);
        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(MainActivity.this, EditCounterActivity.class);
                Counter selectedCounter = (Counter)adapter.getItemAtPosition(position);
                intent.putExtra("EDIT_COUNTER", selectedCounter);
                intent.putExtra("COUNTER_LIST", counters);
                startActivityForResult(intent, EDIT_COUNTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_COUNTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Counter newCounter = (Counter) data.getSerializableExtra("NEW_COUNTER");
            counters.add(newCounter);
            adapter.notifyDataSetChanged();
            saveInFile();
        } else if (requestCode == EDIT_COUNTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int oldCounterIndex = data.getIntExtra("OLD_COUNTER_INDEX", -1);
            Counter editedCounter = (Counter) data.getSerializableExtra("EDIT_COUNTER");

            if (oldCounterIndex != -1) {
                counters.set(oldCounterIndex, editedCounter);
            }
            adapter.notifyDataSetChanged();
            saveInFile();
        }
    }

    /** Called when the user clicks the "+" button */
    public void newCounter(View view) {
        Intent intent = new Intent(this, NewCounterActivity.class);
        startActivityForResult(intent, NEW_COUNTER_ACTIVITY_REQUEST_CODE);
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
