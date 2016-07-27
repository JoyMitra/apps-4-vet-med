package bci.ksu.edu.brdtc.predicates;

public class CostOfGainPredicate implements ValidatePredicate {
    public boolean test(String input) {
        float costOfGain = Float.parseFloat(input);
        return costOfGain < 0.1 || costOfGain > 10;
    }
}
