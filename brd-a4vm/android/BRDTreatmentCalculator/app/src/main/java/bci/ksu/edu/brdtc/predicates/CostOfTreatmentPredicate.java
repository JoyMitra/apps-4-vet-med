package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class CostOfTreatmentPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        float costOfTreatment = Float.parseFloat(editText.getText().toString());
        return costOfTreatment < 0 || costOfTreatment > 50;
    }
}
