package bci.ksu.edu.brdtc;

import junit.framework.Assert;

import org.junit.Test;

import java.text.DecimalFormat;

import bci.ksu.edu.brdtc.parameters.Drug;
import bci.ksu.edu.brdtc.parameters.Population;

public class CalculatorUnitTest {

    @Test
    public void testCalculatorResult() {
        Drug drug1 = new Drug();
        Drug drug2 = new Drug();
        Population population = new Population();
        Calculator calculator = new Calculator();

        population.setMorbidity(30);
        population.setCog(1);
        population.setSp(1.45f);
        population.setPw(500);
        population.setDays(245);
        population.setAhc(3.4f);

        drug1.setTfp(30);
        drug1.setCfr(5);
        drug1.setCt1(5);
        drug1.setCp(3);

        drug2.setTfp(20);
        drug2.setCfr(4.5f);
        drug2.setCt1(25);
        drug2.setCp(2.7f);

        float result = calculator.calcDifferenceInReturnToOAM(drug1, drug2, population);
        DecimalFormat df = new DecimalFormat(".##");
        Assert.assertEquals("0.62", df.format(result));
    }
}
