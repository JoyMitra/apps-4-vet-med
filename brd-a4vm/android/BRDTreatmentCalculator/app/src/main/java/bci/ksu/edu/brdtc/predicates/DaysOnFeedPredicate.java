package bci.ksu.edu.brdtc.predicates;

public class DaysOnFeedPredicate implements ValidatePredicate {
    public boolean test(String input) {
        int daysOnFeed = Integer.parseInt(input);
        return daysOnFeed < 1 || daysOnFeed > 400;
    }
}
