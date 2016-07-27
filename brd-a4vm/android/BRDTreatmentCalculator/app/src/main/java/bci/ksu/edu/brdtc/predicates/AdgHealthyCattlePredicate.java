package bci.ksu.edu.brdtc.predicates;

public class AdgHealthyCattlePredicate implements ValidatePredicate {
    public boolean test(String input) {
        float adgHealthyCattle = Float.parseFloat(input);
        return adgHealthyCattle < 0.1 || adgHealthyCattle > 5;
    }
}
