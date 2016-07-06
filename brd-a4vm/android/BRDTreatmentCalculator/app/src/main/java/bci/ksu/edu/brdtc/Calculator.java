package bci.ksu.edu.brdtc;

import android.util.Log;

import java.text.DecimalFormat;

import bci.ksu.edu.brdtc.parameters.Drug;
import bci.ksu.edu.brdtc.parameters.Population;

public class Calculator {

    // Debugging purposes
    private static final String LOG_CAT = Calculator.class.getSimpleName();
    private static DecimalFormat df = new DecimalFormat(".####");

    private float calcPurchasePrice(float arrivalWeight) {
        return 1.9f * arrivalWeight;
    }

    private float calcNumberNeverPulled(float morbidity) {
        return 1 - morbidity;
    }

    private float calcNumberPulledTwiceOrMore(float morbidity, float treatmentFailurePercent) {
        return morbidity * treatmentFailurePercent;
    }

    private float calcNumberDead(float morbidity, float caseFatalityRisk) {
        return morbidity * caseFatalityRisk;
    }

    private float calcNumberPulledOnce(float numberNeverPulled, float numberPulledTwiceOrMore, float numberDead) {
        return 1 - numberNeverPulled - numberPulledTwiceOrMore - numberDead;
    }

    private float calcAverageADG(float adgHealthyCattle, float numberNeverPulled,
                                 float numberPulledTwiceOrMore, float numberPulledOnce) {
        return (adgHealthyCattle * numberNeverPulled) +
                (adgHealthyCattle * 0.926f * numberPulledOnce) +
                (adgHealthyCattle * 0.88f * numberPulledTwiceOrMore);
    }

    private float calcPercentSoldAtFullPrice(float morbidity, float caseFatalityRisk, float chronicPercent) {
        return 1 - (morbidity * caseFatalityRisk) - chronicPercent;
    }

    private float calcSaleWeight(float arrivalWeight, float averageADG, int days) {
        return arrivalWeight + (averageADG * days);
    }

    private float calcCostOfGain(float costOfGain, float saleWeight, float arrivalWeight) {
        return costOfGain * (saleWeight - arrivalWeight);
    }

    private float calcCostOf1stTreatment(float morbidity, float costOfTreatment) {
        return morbidity * costOfTreatment;
    }

    private float calcCostOf2ndTreatment(float morbidity, float treatmentFailurePercent) {
        return morbidity * treatmentFailurePercent * 15;
    }

    private float calcIncomePerAnimal(float saleWeight, float priceReceivedAtSale) {
        return saleWeight * priceReceivedAtSale;
    }

    private float calcIncomePerChronicAnimal(float incomePerAnimal) {
        return incomePerAnimal * 0.8f;
    }

    private float calcGrossIncome(float saleWeight, float priceReceivedAtSale,
                                  float percentSoldAtFullPrice, float incomePerChronicAnimal,
                                  float chronicPercent) {
        return (saleWeight * priceReceivedAtSale * percentSoldAtFullPrice) +
                (incomePerChronicAnimal * chronicPercent);
    }

    private float calcReturnToOwnershipAndManagement(float morbidity, float pw, float ahc,
                                                     int days, float cog, float sp, float tfp,
                                                     float cp, float cfr, float ct1,
                                                     float purchasePrice, float numberNeverPulled) {
        float numberPulledTwiceOrMore = calcNumberPulledTwiceOrMore(morbidity, tfp);
        float numberDead = calcNumberDead(morbidity, cfr);
        float numberPulledOnce = calcNumberPulledOnce(numberNeverPulled,
                numberPulledTwiceOrMore, numberDead);
        float averageAdg = calcAverageADG(ahc, numberNeverPulled, numberPulledTwiceOrMore,
                numberPulledOnce);
        float percentSoldAtFullPrice = calcPercentSoldAtFullPrice(morbidity, cfr, cp);
        float saleWeight = calcSaleWeight(pw, averageAdg, days);
        float costOfGain = calcCostOfGain(cog, saleWeight, pw);
        float costOf1stTreatment = calcCostOf1stTreatment(morbidity, ct1);
        float costOf2ndTreatment = calcCostOf2ndTreatment(morbidity, tfp);
        float incomePerAnimal = calcIncomePerAnimal(saleWeight, sp);
        float incomePerChronicAnimal = calcIncomePerChronicAnimal(incomePerAnimal);
        float grossIncome = calcGrossIncome(saleWeight, sp, percentSoldAtFullPrice,
                incomePerChronicAnimal, cp);
        return grossIncome - purchasePrice - costOfGain - costOf1stTreatment - costOf2ndTreatment;
    }

    private float calcCostOfTreatmentPerHead(float morbidity, float costOfTreatment,
                                             float treatmentFailurePercent) {
        return (morbidity * costOfTreatment) + (morbidity * treatmentFailurePercent * 15);
    }

    public float calcDifferenceInReturnToOAM(Drug drugOne, Drug drugTwo, Population population) {
        //Get population and drug parameters
        float morbidity = population.getMorbidity() / 100;
        int pw = population.getPw();
        float ahc = population.getAhc();
        int days = population.getDays();
        float cog = population.getCog();
        float sp = population.getSp();
        float tfpa = drugOne.getTfp() / 100;
        float cpa = drugOne.getCp() / 100;
        float cfra = drugOne.getCfr() / 100;
        float cta1 = drugOne.getCt1();
        float tfpb = drugTwo.getTfp() / 100;
        float cpb = drugTwo.getCp() / 100;
        float cfrb = drugTwo.getCfr() / 100;
        float ctb1 = drugTwo.getCt1();

        //Calculate for both Treatment 1 and 2
        float purchasePrice = calcPurchasePrice(pw);
        float numberNeverPulled = calcNumberNeverPulled(morbidity);

        //Calculate for Treatment 1
        float returnToOwnershipAndManagementTxA = calcReturnToOwnershipAndManagement(morbidity, pw,
                ahc, days, cog, sp, tfpa, cpa, cfra, cta1, purchasePrice, numberNeverPulled);

        //Calculate for Treatment 2
        float returnToOwnershipAndManagementTxB = calcReturnToOwnershipAndManagement(morbidity, pw,
                ahc, days, cog, sp, tfpb, cpb, cfrb, ctb1, purchasePrice, numberNeverPulled);

        //Calculate Difference in Return to Ownership and Management
        return returnToOwnershipAndManagementTxB - returnToOwnershipAndManagementTxA;
    }
}
