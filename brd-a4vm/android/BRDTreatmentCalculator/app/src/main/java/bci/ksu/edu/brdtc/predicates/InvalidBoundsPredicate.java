package bci.ksu.edu.brdtc.predicates;

public class InvalidBoundsPredicate implements ValidatePredicate {
    public boolean test(String input) {
        float percent = Float.parseFloat(input);
        return percent > 100 || percent < 0;
    }
}
