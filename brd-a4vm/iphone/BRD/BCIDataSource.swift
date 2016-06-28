//
//  BCIDataSource.swift
//  BRD
//
//  Created by Kevin Manase on 6/21/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import UIKit

class BCIDataSource {
    static let sharedInstance = BCIDataSource()
    
    //Dictionaries to be updated later
    var populationDictionary: [String:String!] = [:]
    var drug1Dictionary: [String:String!] = [:]
    var drug2Dictionary: [String:String!] = [:]
    var drug1Name: String!
    var drug2Name: String!
    
    //POPULATION AND DRUG PARAMETERS
    var pw: Double!   //D5: Arrival Weight (lb) [PW]
    var cog: Double! //D6: Cost of Gain ($/lb. gain) [CoG]
    var days: Double! //D7: Days on feed (days)
    var m: Double!    //D8: Morbidity % [M]
    var cta1: Double! //D9: Cost of treatment w/Drug A $ [CTA1]
    var ctb1: Double! //D10: Cost of treatment w/Drug B $ [CTB1]
    var tfpa: Double! //D11: Re-pull % with Drug A [TFPA]
    var tfpb: Double! //D12: Re-pull % with Drug B [TFPB]
    var cfra: Double! //D13: Case Fatality Risk for Treatment w/Drug A % [CFRA]
    var cfrb: Double! //D14: Case Fatality Risk for Treatment w/Drug B % [CFRB]
    var cpa: Double!  //D15: Chronic % w/TxA (default=1/4 death %, or enter your own %) - CPA
    var cpb: Double!  //D16: Chronic % w/TxB (default=1/4 death %, or enter your own %) - CPB
    var sp: Double!  //D17: Price received at sale ($/lbs.) [SP]
    var ahc: Double! //D18: ADG healthy cattle [AHC]
    
    //COST PARAMETERS
    var purchasePriceTxA: Double!
    var purchasePriceTxB: Double!
    var costOfGainTxA: Double!
    var costOfGainTxB: Double!
    var costOf1stTxA: Double!
    var costOf1stTxB: Double!
    var costOf2ndTxA: Double!
    var costOf2ndTxB: Double!
    var averageADGTxA: Double!
    var averageADGTxB: Double!
    var percentSoldAtFullPriceTxA: Double!
    var percentSoldAtFullPriceTxB: Double!
    var incomePerAnimalTxA: Double!
    var incomePerAnimalTxB: Double!
    var incomePerChronicAnimalTxA: Double!
    var incomePerChronicAnimalTxB: Double!
    var numberNeverPulledTxA: Double!
    var numberNeverPulledTxB: Double!
    var numberPulledOnceTxA: Double!
    var numberPulledOnceTxB: Double!
    var numberPulled2OrMoreTxA: Double!
    var numberPulled2OrMoreTxB: Double!
    var numberDeadTxA: Double!
    var numberDeadTxB: Double!
    
    //CALCULATIONS
    var saleWeightTxA: Double!
    var saleWeightTxB: Double!
    var grossIncomeTxA: Double!
    var grossIncomeTxB: Double!
    var returnToOwnershipAndManagementTxA: Double!
    var returnToOwnershipAndManagementTxB: Double!
    var costOfTreatmentPerHeadTxA: Double!
    var costOfTreatmentPerHeadTxB: Double!
    var differenceInReturnToOwnership: Double!
    
    
    
    func calculatePurchasePrice() {
        purchasePriceTxA = 1.9 * pw;
        purchasePriceTxB = 1.9 * pw;
    }
    
    func calculateNumbers() {
        numberNeverPulledTxA = 1 - m;
        numberNeverPulledTxB = 1 - m;
        numberPulled2OrMoreTxA = m * tfpa;
        numberPulled2OrMoreTxB = m * tfpb;
        numberDeadTxA = m * cfra;
        numberDeadTxB = m * cfrb;
        numberPulledOnceTxA = 1 - numberNeverPulledTxA - numberPulled2OrMoreTxA - numberDeadTxA;
        numberPulledOnceTxB = 1 - numberNeverPulledTxB - numberPulled2OrMoreTxB - numberDeadTxB;
    }
    
    func calculateAverageADG() {
        averageADGTxA = (ahc * numberNeverPulledTxA) + (ahc * 0.926 * numberPulledOnceTxA) + (ahc * 0.88 * numberPulled2OrMoreTxA);
        averageADGTxB = (ahc * numberNeverPulledTxB) + (ahc * 0.926 * numberPulledOnceTxB) + (ahc * 0.88 * numberPulled2OrMoreTxB);
    }
    
    func calculatePercentSoldFullPrice() {
        percentSoldAtFullPriceTxA = 1 - (m * cfra) - cpa;
        percentSoldAtFullPriceTxB = 1 - (m * cfrb) - cpb;
    }
    
    func calculateSaleWeight() {
        saleWeightTxA = pw + (averageADGTxA * days);
        saleWeightTxB = pw + (averageADGTxB * days);
    }
    
    func calculateCostOfGain() {
        costOfGainTxA = cog * (saleWeightTxA - pw);
        costOfGainTxB = cog * (saleWeightTxB - pw);
    }
    
    func calculateCostOf1stTx() {
        costOf1stTxA = m * cta1;
        costOf1stTxB = m * ctb1;
    }
    
    //FIXME: Should this be cpa and cpb?
    func calculateCostOf2ndTx() {
        costOf2ndTxA = m * tfpa * 15;
        costOf2ndTxB = m * tfpb * 15;
    }
    
    func calculateIncomePerAnimalSoldAtFullPrice() {
        incomePerAnimalTxA = saleWeightTxA * sp;
        incomePerAnimalTxB = saleWeightTxB * sp;
    }
    
    func calculateIncomePerChronicAnimal() {
        incomePerChronicAnimalTxA = incomePerAnimalTxA * 0.8;
        incomePerChronicAnimalTxB = incomePerAnimalTxB * 0.8;
    }
    
    func calculateGrossIncome() {
        grossIncomeTxA = (saleWeightTxA * sp * percentSoldAtFullPriceTxA) + (incomePerChronicAnimalTxA * cpa);
        grossIncomeTxB = (saleWeightTxB * sp * percentSoldAtFullPriceTxB) + (incomePerChronicAnimalTxB * cpb);
    }
    
    func calculateReturnToOwnershipAndManagement() {
        returnToOwnershipAndManagementTxA = grossIncomeTxA - purchasePriceTxA - costOfGainTxA - costOf1stTxA - costOf2ndTxA;
        returnToOwnershipAndManagementTxB = grossIncomeTxB - purchasePriceTxB - costOfGainTxB - costOf1stTxB - costOf2ndTxB;
    }
    
    //FIXME: Should this be cpa and cpb?
    func calculateCostOfTreatmentPerHead() {
        costOfTreatmentPerHeadTxA = (m * cta1) + (m * tfpa * 15);
        costOfTreatmentPerHeadTxB = (m * ctb1) + (m * tfpb * 15);
    }
    
    func calculateDifferenceInReturnToOwnership() {
        differenceInReturnToOwnership = returnToOwnershipAndManagementTxB - returnToOwnershipAndManagementTxA;
    }
    
    func calculateEverything() {
        m = m/100
        tfpa = tfpa/100
        tfpb = tfpb/100
        cfra = cfra/100
        cfrb = cfrb/100
        cpa = cpa/100
        cpb = cpb/100
        calculatePurchasePrice()
        calculateNumbers()
        calculateAverageADG()
        calculatePercentSoldFullPrice()
        calculateSaleWeight()
        calculateCostOfGain()
        calculateCostOf1stTx()
        calculateCostOf2ndTx()
        calculateIncomePerAnimalSoldAtFullPrice()
        calculateIncomePerChronicAnimal()
        calculateGrossIncome()
        calculateReturnToOwnershipAndManagement()
        calculateCostOfTreatmentPerHead()
        calculateDifferenceInReturnToOwnership()
    }
    
    func reset() {
        m = m*100
        tfpa = tfpa*100
        tfpb = tfpb*100
        cfra = cfra*100
        cfrb = cfrb*100
        cpa = cpa*100
        cpb = cpb*100
    }

}
