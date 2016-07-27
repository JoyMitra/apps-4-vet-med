//
//  BRD_Tx_EvaluationTests.swift
//  BRD Tx EvaluationTests
//
//  Created by Kevin Manase on 7/26/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import XCTest
@testable import BRD_Tx_Evaluation

class BRD_Tx_EvaluationTests: XCTestCase {

        let source = BCIDataSource.sharedInstance
        let doubleFormat = ".2"
    
        override func setUp() {
            super.setUp()
            
            // Load variables to data source
            setVariables()
            
            // Calculate everything
            source.calculateEverything(test: true)
        }
        
        override func tearDown() {
            // Put teardown code here. This method is called after the invocation of each test method in the class.
            super.tearDown()
        }
    
        func setVariables() {
            print("Setting variables........")
            source.m = 0.3
            source.pw = 500
            source.days = 245
            source.tfpa = 0.3
            source.tfpb = 0.2
            source.cfra = 0.05
            source.cfrb = 0.045
            source.cpa = 0.03
            source.cpb = 0.027
            source.cog = 1
            source.cta1 = 5
            source.ctb1 = 25
            source.sp = 1.45
            source.ahc = 3.4
            print("Variables set............")
        }

        // Calculates Sale Weight for Tx A / Tx B - Sold full price
        // Cell 21 & 22
        func testCalculateSaleWeight() {            
            XCTAssertEqual("\(source.saleWeightTxA.format(doubleFormat))", "1299.49")
            XCTAssertEqual("\(source.saleWeightTxB.format(doubleFormat))", "1301.79")
        }
    
        // Calculate Gross Income Tx A / Tx B
        // Cell 23 & 24
        func testCalculateGrossIncome() {
            XCTAssertEqual("\(source.grossIncomeTxA.format(doubleFormat))", "1844.69")
            XCTAssertEqual("\(source.grossIncomeTxB.format(doubleFormat))", "1851.93")
        }

        // Calculates return to ownership and management with Tx A / Tx B
        // Cell 25 & 26
        func testCalculateReturnToOwnershipAndManagement() {
            XCTAssertEqual("\(source.returnToOwnershipAndManagementTxA.format(doubleFormat))", "92.35")
            XCTAssertEqual("\(source.returnToOwnershipAndManagementTxB.format(doubleFormat))", "91.73")
        }
    
        // Caculates cost of treamtement with Tx A / Tx B per head purchased
        // Cell 27 & 28
        func testCalculateCostOfTreatmentPerHead() {
            XCTAssertEqual("\(source.costOfTreatmentPerHeadTxA.format(doubleFormat))", "2.85")
            XCTAssertEqual("\(source.costOfTreatmentPerHeadTxB.format(doubleFormat))", "8.40")
        }
    
        // Calculates difference in return to ownership per animal
        // Cell 29 - If negative then A wins
        func testCalculateDifferenceInReturn() {
            XCTAssertEqual("\(source.differenceInReturnToOwnership.format(doubleFormat))", "-0.62")
        }
    
        // An easier version of the above test
        // If sign is minus then A wins
        func testBestDrug() {
            if (source.differenceInReturnToOwnership.isSignMinus) {
                print("Drug 1 is better")
            } else {
                print("Drug 2 is better")
            }
        }
}

