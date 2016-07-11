package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class CostOfGainPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        float costOfGain = Float.parseFloat(editText.getText().toString());
        return costOfGain < 0.1 || costOfGain > 10;
    }
}
