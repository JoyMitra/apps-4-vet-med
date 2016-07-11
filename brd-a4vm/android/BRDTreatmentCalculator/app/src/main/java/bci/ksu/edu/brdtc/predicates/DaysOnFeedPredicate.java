package bci.ksu.edu.brdtc.predicates;

import android.widget.EditText;

public class DaysOnFeedPredicate implements ValidatePredicate {
    public boolean test(EditText editText) {
        int daysOnFeed = Integer.parseInt(editText.getText().toString());
        return daysOnFeed < 1 || daysOnFeed > 400;
    }
}
