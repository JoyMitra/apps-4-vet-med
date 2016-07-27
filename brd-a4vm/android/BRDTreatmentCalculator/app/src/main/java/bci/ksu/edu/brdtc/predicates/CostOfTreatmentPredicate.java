package bci.ksu.edu.brdtc.predicates;

public class CostOfTreatmentPredicate implements ValidatePredicate {
    public boolean test(String input) {
        float costOfTreatment = Float.parseFloat(input);
        return costOfTreatment < 0 || costOfTreatment > 50;
    }
}
