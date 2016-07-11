package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class AdgHealthyCattlePredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        float adgHealthyCattle = Float.parseFloat(editText.getText().toString());
        return adgHealthyCattle < 0.1 || adgHealthyCattle > 5;
    }
}
