package bci.ksu.edu.brdtc.predicates;

public class PriceReceivedAtSalePredicate implements ValidatePredicate {
    public boolean test(String input) {
        float priceReceivedAtSale = Float.parseFloat(input);
        return priceReceivedAtSale < 0.1 || priceReceivedAtSale > 40;
    }
}
