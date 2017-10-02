package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity class for Viewing / Editing counter information.
 */
public class EditCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        final Button okButton = (Button) findViewById(R.id.okButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        final EditText nameEdit = (EditText) findViewById(R.id.nameEditText);
        final EditText initEdit = (EditText) findViewById(R.id.initEditText);
        final EditText currEdit = (EditText) findViewById(R.id.currEditText);
        final EditText commentEdit = (EditText) findViewById(R.id.commentEditView);
        final TextView errorText = (TextView) findViewById(R.id.errorText);
        final TextView dateText = (TextView) findViewById(R.id.dateView);

        // Load values of the selected counter into the UI
        final Counter selectedCounter = (Counter) getIntent().getSerializableExtra("EDIT_COUNTER");
        final ArrayList<Counter> counterList = (ArrayList<Counter>) getIntent().getSerializableExtra("COUNTER_LIST");

        nameEdit.setText(selectedCounter.getName());
        initEdit.setText(selectedCounter.getInitialValue().toString());
        currEdit.setText(selectedCounter.getCurrentValue().toString());
        commentEdit.setText(selectedCounter.getComment());
        dateText.setText(Helpers.setDateFormat(selectedCounter.getDate(), "yyyy-MM-dd"));


        nameEdit.addTextChangedListener(new TextValidator(nameEdit) {
            @Override public void validate(TextView textView, String text) {
                if (text.isEmpty() || Helpers.verifyTextBox(initEdit) || Helpers.verifyTextBox(currEdit)) {
                    okButton.setEnabled(false);
                    errorText.setText(getString(R.string.newCounterError));
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    okButton.setEnabled(true);
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });

        initEdit.addTextChangedListener(new TextValidator(initEdit) {
            @Override public void validate(TextView textView, String text) {
                if (text.isEmpty() || Helpers.verifyTextBox(nameEdit) || Helpers.verifyTextBox(currEdit)) {
                    okButton.setEnabled(false);
                    errorText.setText(getString(R.string.newCounterError));
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    okButton.setEnabled(true);
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });

        currEdit.addTextChangedListener(new TextValidator(currEdit) {
            @Override public void validate(TextView textView, String text) {
                if (text.isEmpty() || Helpers.verifyTextBox(nameEdit) || Helpers.verifyTextBox(initEdit)) {
                    okButton.setEnabled(false);
                    errorText.setText(getString(R.string.newCounterError));
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    okButton.setEnabled(true);
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                Integer init = Integer.valueOf(initEdit.getText().toString());
                Integer curr = Integer.valueOf(currEdit.getText().toString());
                String comment = commentEdit.getText().toString();
                Counter editedCounter = new Counter(name, init, comment, curr);

                int selectedCounterIndex = Helpers.getCounterIndex(counterList, selectedCounter);

                Intent output = new Intent();
                output.putExtra("OLD_COUNTER_INDEX", selectedCounterIndex);
                output.putExtra("EDIT_COUNTER", editedCounter);
                setResult(RESULT_OK, output);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
