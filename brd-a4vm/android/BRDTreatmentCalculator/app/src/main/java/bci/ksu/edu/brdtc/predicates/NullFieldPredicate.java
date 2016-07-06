package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class NullFieldPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        int textLength = editText.getText().toString().trim().length();
        return textLength == 0;
    }
}
