package bci.ksu.edu.brdtc;

import org.junit.Assert;
import org.junit.Test;

import bci.ksu.edu.brdtc.predicates.AdgHealthyCattlePredicate;
import bci.ksu.edu.brdtc.predicates.ArrivalWeightPredicate;
import bci.ksu.edu.brdtc.predicates.CostOfGainPredicate;
import bci.ksu.edu.brdtc.predicates.CostOfTreatmentPredicate;
import bci.ksu.edu.brdtc.predicates.DaysOnFeedPredicate;
import bci.ksu.edu.brdtc.predicates.InvalidBoundsPredicate;
import bci.ksu.edu.brdtc.predicates.NullFieldPredicate;
import bci.ksu.edu.brdtc.predicates.PriceReceivedAtSalePredicate;
import bci.ksu.edu.brdtc.predicates.ValidatePredicate;

public class PredicatesUnitTest {

    @Test
    public void adgHealthyCattleBounds() throws Exception {
        String number = "2.5";
        String number2 = "0";
        String number3 = "6";

        ValidatePredicate vp = new AdgHealthyCattlePredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void arrivalWeightBounds() throws Exception {
        String number = "500";
        String number2 = "99";
        String number3 = "1500";

        ValidatePredicate vp = new ArrivalWeightPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void costOfGainBounds() throws Exception {
        String number = "5";
        String number2 = "0.01";
        String number3 = "11";

        ValidatePredicate vp = new CostOfGainPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void costOfTreatmentBounds() throws Exception {
        String number = "25";
        String number2 = "-1";
        String number3 = "100";

        ValidatePredicate vp = new CostOfTreatmentPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void daysOnFeedBounds() throws Exception {
        String number = "200";
        String number2 = "0";
        String number3 = "500";

        ValidatePredicate vp = new DaysOnFeedPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void invalidBounds() throws Exception {
        String number = "50";
        String number2 = "-1";
        String number3 = "101";

        ValidatePredicate vp = new InvalidBoundsPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }

    @Test
    public void nullField() throws Exception {
        String number = "500";
        String number2 = "";

        ValidatePredicate vp = new NullFieldPredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
    }

    @Test
    public void priceReceivedAtSaleBounds() throws Exception {
        String number = "20";
        String number2 = "0";
        String number3 = "100";

        ValidatePredicate vp = new PriceReceivedAtSalePredicate();
        Assert.assertFalse(vp.test(number));
        Assert.assertTrue(vp.test(number2));
        Assert.assertTrue(vp.test(number3));
    }
}
