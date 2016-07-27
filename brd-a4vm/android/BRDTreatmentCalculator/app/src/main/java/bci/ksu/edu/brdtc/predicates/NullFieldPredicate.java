package bci.ksu.edu.brdtc.predicates;

public class NullFieldPredicate implements ValidatePredicate {
    public boolean test(String input) {
        int textLength = input.trim().length();
        return textLength == 0;
    }
}
