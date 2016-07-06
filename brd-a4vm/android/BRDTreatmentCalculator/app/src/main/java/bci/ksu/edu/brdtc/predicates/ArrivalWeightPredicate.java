package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class ArrivalWeightPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        int arrivalWeight = Integer.parseInt(editText.getText().toString());
        return arrivalWeight < 100 || arrivalWeight > 1200;
    }
}
