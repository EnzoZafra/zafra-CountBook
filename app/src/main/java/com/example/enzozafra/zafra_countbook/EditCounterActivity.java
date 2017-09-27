package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


        // Load values of the selected counter into the UI
        Counter selectedCounter = (Counter) getIntent().getSerializableExtra("EDIT_COUNTER");

        nameEdit.setText(selectedCounter.getName());
        initEdit.setText(selectedCounter.getInitialValue().toString());
        currEdit.setText(selectedCounter.getCurrentValue().toString());
        commentEdit.setText(selectedCounter.getComment());


        nameEdit.addTextChangedListener(new TextValidator(nameEdit) {
            @Override public void validate(TextView textView, String text) {
                if ((text.isEmpty() || nameEdit.getText().toString().isEmpty() || initEdit.getText().toString().isEmpty() ||
                        currEdit.getText().toString().isEmpty())
                        && okButton.isEnabled()) {
                    okButton.setEnabled(false);
                    errorText.setVisibility(View.VISIBLE);

                } else {
                    okButton.setEnabled(true);
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });

        initEdit.addTextChangedListener(new TextValidator(initEdit) {
            @Override public void validate(TextView textView, String text) {
                if ((text.isEmpty() || nameEdit.getText().toString().isEmpty() || initEdit.getText().toString().isEmpty() ||
                        currEdit.getText().toString().isEmpty())
                        && okButton.isEnabled()) {
                    okButton.setEnabled(false);
                    errorText.setVisibility(View.VISIBLE);
                } else {
                    okButton.setEnabled(true);
                    errorText.setVisibility(View.INVISIBLE);
                }
            }
        });

        currEdit.addTextChangedListener(new TextValidator(currEdit) {
            @Override public void validate(TextView textView, String text) {
                if ((text.isEmpty() || nameEdit.getText().toString().isEmpty() || initEdit.getText().toString().isEmpty() ||
                        currEdit.getText().toString().isEmpty())
                        && okButton.isEnabled()) {
                    okButton.setEnabled(false);
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
                //TODO: edit the counter
                Counter editedCounter = new Counter("TEST", 21); //change this

                Intent output = new Intent();
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
