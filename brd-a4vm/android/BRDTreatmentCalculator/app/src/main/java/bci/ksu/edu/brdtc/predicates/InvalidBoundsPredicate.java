package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class InvalidBoundsPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        float percent = Float.parseFloat(editText.getText().toString());
        return percent > 100 || percent < 0;
    }
}
