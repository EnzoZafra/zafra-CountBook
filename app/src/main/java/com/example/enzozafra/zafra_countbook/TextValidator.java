package com.example.enzozafra.zafra_countbook;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * This class watches for any changes in a textView and runs a validator to validate user input.
 */
public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    public abstract void validate(TextView textView, String text);
}
