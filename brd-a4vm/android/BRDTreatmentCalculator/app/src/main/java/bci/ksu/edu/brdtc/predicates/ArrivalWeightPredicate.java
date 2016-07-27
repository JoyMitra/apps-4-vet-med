package bci.ksu.edu.brdtc.predicates;

public class ArrivalWeightPredicate implements ValidatePredicate {
    public boolean test(String input) {
        int arrivalWeight = Integer.parseInt(input);
        return arrivalWeight < 100 || arrivalWeight > 1200;
    }
}
