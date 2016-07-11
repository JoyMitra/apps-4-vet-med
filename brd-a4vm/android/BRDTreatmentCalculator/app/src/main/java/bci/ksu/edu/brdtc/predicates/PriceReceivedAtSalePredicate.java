package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class PriceReceivedAtSalePredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        float priceReceivedAtSale = Float.parseFloat((editText.getText().toString()));
        return priceReceivedAtSale < 0.1 || priceReceivedAtSale > 40;
    }
}
