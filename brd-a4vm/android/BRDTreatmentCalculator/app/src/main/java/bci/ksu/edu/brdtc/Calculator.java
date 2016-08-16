package bci.ksu.edu.brdtc;

import java.text.DecimalFormat;

import bci.ksu.edu.brdtc.parameters.Drug;
import bci.ksu.edu.brdtc.parameters.Population;

public class Calculator {

    // Debugging purposes
    private static final String LOG_CAT = Calculator.class.getSimpleName();
    private static DecimalFormat df = new DecimalFormat(".####");

    private float purchasePrice(float arrivalWeight) {
        return 1.9f * arrivalWeight;
    }

    private float numberNeverPulled(float morbidity) {
        return 1 - morbidity;
    }

    private float numberPulledTwiceOrMore(float morbidity, float treatmentFailurePercent) {
        return morbidity * treatmentFailurePercent;
    }

    private float numberDead(float morbidity, float caseFatalityRisk) {
        return morbidity * caseFatalityRisk;
    }

    private float numberPulledOnce(float numberNeverPulled, float numberPulledTwiceOrMore, float numberDead) {
        return 1 - numberNeverPulled - numberPulledTwiceOrMore - numberDead;
    }

    private float averageADG(float adgHealthyCattle, float numberNeverPulled,
                             float numberPulledTwiceOrMore, float numberPulledOnce) {
        return (adgHealthyCattle * numberNeverPulled) +
                (adgHealthyCattle * 0.926f * numberPulledOnce) +
                (adgHealthyCattle * 0.88f * numberPulledTwiceOrMore);
    }

    private float percentSoldAtFullPrice(float morbidity, float caseFatalityRisk, float chronicPercent) {
        return 1 - (morbidity * caseFatalityRisk) - chronicPercent;
    }

    private float saleWeight(float arrivalWeight, float averageADG, int days) {
        return arrivalWeight + (averageADG * days);
    }

    private float costOfGain(float costOfGain, float saleWeight, float arrivalWeight) {
        return costOfGain * (saleWeight - arrivalWeight);
    }

    private float costOf1stTreatment(float morbidity, float costOfTreatment) {
        return morbidity * costOfTreatment;
    }

    private float costOf2ndTreatment(float morbidity, float treatmentFailurePercent) {
        return morbidity * treatmentFailurePercent * 15;
    }

    private float incomePerAnimal(float saleWeight, float priceReceivedAtSale) {
        return saleWeight * priceReceivedAtSale;
    }

    private float incomePerChronicAnimal(float incomePerAnimal) {
        return incomePerAnimal * 0.8f;
    }

    private float grossIncome(float saleWeight, float priceReceivedAtSale,
                              float percentSoldAtFullPrice, float incomePerChronicAnimal,
                              float chronicPercent) {
        return (saleWeight * priceReceivedAtSale * percentSoldAtFullPrice) +
                (incomePerChronicAnimal * chronicPercent);
    }

    private float returnToOwnershipAndManagement(float morbidity, float pw, float ahc,
                                                 int days, float cog, float sp, float tfp,
                                                 float cp, float cfr, float ct1,
                                                 float purchasePrice, float numberNeverPulled) {
        float numberPulledTwiceOrMore = numberPulledTwiceOrMore(morbidity, tfp);
        float numberDead = numberDead(morbidity, cfr);
        float numberPulledOnce = numberPulledOnce(numberNeverPulled,
                numberPulledTwiceOrMore, numberDead);
        float averageAdg = averageADG(ahc, numberNeverPulled, numberPulledTwiceOrMore,
                numberPulledOnce);
        float percentSoldAtFullPrice = percentSoldAtFullPrice(morbidity, cfr, cp);
        float saleWeight = saleWeight(pw, averageAdg, days);
        float costOfGain = costOfGain(cog, saleWeight, pw);
        float costOf1stTreatment = costOf1stTreatment(morbidity, ct1);
        float costOf2ndTreatment = costOf2ndTreatment(morbidity, tfp);
        float incomePerAnimal = incomePerAnimal(saleWeight, sp);
        float incomePerChronicAnimal = incomePerChronicAnimal(incomePerAnimal);
        float grossIncome = grossIncome(saleWeight, sp, percentSoldAtFullPrice,
                incomePerChronicAnimal, cp);
        return grossIncome - purchasePrice - costOfGain - costOf1stTreatment - costOf2ndTreatment;
    }

    private float costOfTreatmentPerHead(float morbidity, float costOfTreatment,
                                             float treatmentFailurePercent) {
        return (morbidity * costOfTreatment) + (morbidity * treatmentFailurePercent * 15);
    }

    public float differenceInReturnToOAM(Drug txa, Drug txb, Population population) {
        //Get population and drug parameters
        float morbidity = population.getMorbidity() / 100;
        int pw = population.getPw();
        float ahc = population.getAhc();
        int days = population.getDays();
        float cog = population.getCog();
        float sp = population.getSp();
        float tfpa = txa.getTfp() / 100;
        float cpa = txa.getCp() / 100;
        float cfra = txa.getCfr() / 100;
        float cta1 = txa.getCt1();
        float tfpb = txb.getTfp() / 100;
        float cpb = txb.getCp() / 100;
        float cfrb = txb.getCfr() / 100;
        float ctb1 = txb.getCt1();

        //Calculate for both Treatment 1 and 2
        float purchasePrice = purchasePrice(pw);
        float numberNeverPulled = numberNeverPulled(morbidity);

        //Calculate for Treatment 1
        float returnToOwnershipAndManagementTxA = returnToOwnershipAndManagement(morbidity, pw,
                ahc, days, cog, sp, tfpa, cpa, cfra, cta1, purchasePrice, numberNeverPulled);

        //Calculate for Treatment 2
        float returnToOwnershipAndManagementTxB = returnToOwnershipAndManagement(morbidity, pw,
                ahc, days, cog, sp, tfpb, cpb, cfrb, ctb1, purchasePrice, numberNeverPulled);

        //Calculate Difference in Return to Ownership and Management
        return returnToOwnershipAndManagementTxB - returnToOwnershipAndManagementTxA;
    }

    public float calculateCostPivot(float differenceROAM, Drug txa, Drug txb,
                                           Population population) {
        if (differenceROAM == 0) {
            return 0;
        }
        float costDifferenetial = 0;
        if (differenceROAM < 0) {
            float ctb1 = txb.getCt1();
            Drug txbTemp = new Drug(txb);
            while (differenceROAM < 0) {
                ctb1 += .10;
                txbTemp.setCt1(ctb1);
                differenceInReturnToOAM(txa, txbTemp, population);
                costDifferenetial += .10;
                differenceROAM += .10;
            }
        } else {
            float cta1 = txa.getCt1();
            Drug txaTemp = new Drug(txa);
            while (differenceROAM > 0) {
                cta1 -= .10;
                txaTemp.setCt1(cta1);
                differenceInReturnToOAM(txa, txaTemp, population);
                costDifferenetial += .10;
                differenceROAM -= .10;
            }
        }
        return costDifferenetial;
    }
}
