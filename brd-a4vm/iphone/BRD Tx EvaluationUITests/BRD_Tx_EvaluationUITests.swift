//
//  BRD_Tx_EvaluationUITests.swift
//  BRD Tx EvaluationUITests
//
//  Created by Kevin Manase on 8/11/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

import XCTest

class BRD_Tx_EvaluationUITests: XCTestCase {
    
    let app = XCUIApplication()
    typealias CompletionHandler = (success:Bool) -> Void
    
    override func setUp() {
        super.setUp()
        
        // Put setup code here. This method is called before the invocation of each test method in the class.
        
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        app.launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
        app.terminate()
    }
    
    // Test the app with empty fields
    func testEmptyValues() {
        resetValues()
        useApp { (success) in
            if (success == true) {
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Error"].exists)
            }
        }
    }
    
    // Test the app with invalid parameters
    func testInvalidValues() {
        resetValues()
        useApp { (success) in
            if (success == true) {
                self.setBadPopulationVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Error"].exists)
                self.app.buttons["Okay"].tap()
                self.app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
                self.app.buttons["Reset"].tap() // There is a hidden reset button
                self.app.buttons["Start"].tap()
                self.setGoodParameterVariables()
                self.app.buttons["Next"].tap()
                self.setBadDrug1ParameterVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Error"].exists)
            }
        }
    }
    
    // Test the app with good values
    func testValidValues() {
        resetValues()
        useApp { (success) in
            if (success == true) {
                self.setGoodParameterVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Drug 1"].exists)
                self.setGoodDrug1ParameterVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Drug 2"].exists)
                self.setGoodDrug2ParameterVariables()
                self.app.buttons["Compare"].tap()
                XCTAssert(self.app.staticTexts["Results"].exists)
            }
        }
    }
    
    // Test if values were retained
    func testRetainedValues() {
        resetValues()
        useApp { (success) in
            if (success == true) {
                self.setGoodParameterVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Drug 1"].exists)
                self.setGoodDrug1ParameterVariables()
                self.app.buttons["Next"].tap()
                XCTAssert(self.app.staticTexts["Drug 2"].exists)
                self.setGoodDrug2ParameterVariables()
                self.app.buttons["Compare"].tap()
                XCTAssert(self.app.staticTexts["Results"].exists)
                self.app.terminate()
                self.app.launch()
            }
        }
        useApp { (success) in
            if (success == true) {
                if (self.app.buttons["Start"].exists) {
                    self.app.buttons["Start"].tap()
                }
                self.app.buttons["Next"].tap()
                self.app.buttons["Next"].tap()
                self.app.buttons["Compare"].tap()
                XCTAssert(self.app.staticTexts["Results"].exists)
                let result = self.app.textViews.elementBoundByIndex(0).value
                XCTAssertTrue(result!.containsString("99.76"))
            }
        }
    }
    
    // Test to see if we retained previously retained values
    func testRetainChangedValues() {
        resetValues()
        useApp { (success) in
            if (success == true) {
                self.setGoodParameterVariables()
                self.app.buttons["Next"].tap()
                self.app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
                let rawMorbidity = String(self.app.textFields["0-100%"].value!)
                XCTAssertEqual(rawMorbidity,"30")
                self.changeValue()
                self.app.buttons["Next"].tap()
                self.app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
                let modMorbidity = String(self.app.textFields["0-100%"].value!)
                XCTAssertEqual(modMorbidity,"50")
            }
        }
    }
    
    // Start using the app by click the "Start" button
    func useApp(completionHandler: CompletionHandler) {
        let startButton = app.buttons["Start"]
        let welcomeText = app.staticTexts["WELCOME"]
        let exists = NSPredicate(format: "exists == 1")
        
        expectationForPredicate(exists, evaluatedWithObject: startButton, handler: nil)
        expectationForPredicate(exists, evaluatedWithObject: welcomeText, handler: nil)
        waitForExpectationsWithTimeout(50) { (error: NSError?) in
            if (error == nil) {
                startButton.tap()
                completionHandler(success: true)
            }
        } // User input, fails if not watched
    }
    
    func changeValue() {
        let morbidityField = app.textFields["0-100%"]
        morbidityField.pressForDuration(1.2)
        app.menuItems["Select All"].tap()
        morbidityField.typeText("50")
    }
    
    func setGoodParameterVariables() {
        let morbidityField = app.textFields["0-100%"]
        morbidityField.tap()
        morbidityField.typeText("30")
        
        let cogField = app.textFields["$/lb/head"]
        cogField.tap()
        cogField.typeText("1")
        
        let priceField = app.textFields["$/lb"]
        priceField.tap()
        priceField.typeText("1.45")
        
        let arrivalWeightField = app.textFields["lb"]
        app.scrollToElement(arrivalWeightField)
        arrivalWeightField.tap()
        arrivalWeightField.typeText("300")
        
        let daysField = app.textFields["days"]
        app.scrollToElement(daysField)
        daysField.tap()
        daysField.typeText("120")
        
        let adgField = app.textFields["lb/head/day"]
        app.scrollToElement(adgField)
        adgField.tap()
        adgField.typeText("1.45")
    }
    
    func setGoodDrug1ParameterVariables() {
        let drugNameField = app.textFields["Drug name"]
        drugNameField.tap()
        drugNameField.typeText("Terramycin")
        
        let cfrField = app.textFields["$"]
        app.scrollToElement(cfrField)
        cfrField.tap()
        cfrField.typeText("1.23")
        
        let tpfField = app.textFields["tpf"]
        app.scrollToElement(tpfField)
        tpfField.tap()
        tpfField.typeText("20")
        
        let costField = app.textFields["cfr"]
        app.scrollToElement(costField)
        costField.tap()
        costField.typeText("50")
        
        let chronicField = app.textFields["chronic"]
        app.scrollToElement(chronicField)
        chronicField.tap()
        chronicField.typeText("1.2")
    }
    
    func setBadDrug1ParameterVariables() {
        let drugNameField = app.textFields["Drug name"]
        drugNameField.tap()
        drugNameField.typeText("Terramycin")
        
        let cfrField = app.textFields["$"]
        app.scrollToElement(cfrField)
        cfrField.tap()
        cfrField.typeText("1.23")
        
        let tpfField = app.textFields["tpf"]
        app.scrollToElement(tpfField)
        tpfField.tap()
        tpfField.typeText("500")
        
        let costField = app.textFields["cfr"]
        app.scrollToElement(costField)
        costField.tap()
        costField.typeText("50")
        
        let chronicField = app.textFields["chronic"]
        app.scrollToElement(chronicField)
        chronicField.tap()
        chronicField.typeText("10")
    }
    
    func setGoodDrug2ParameterVariables() {
        let drugNameField = app.textFields["Drug name"]
        drugNameField.tap()
        drugNameField.typeText("Excenel")
        
        let cfrField = app.textFields["$"]
        app.scrollToElement(cfrField)
        cfrField.tap()
        cfrField.typeText("2.23")
        
        let tpfField = app.textFields["tpf"]
        app.scrollToElement(tpfField)
        tpfField.tap()
        tpfField.typeText("5")
        
        let costField = app.textFields["cfr"]
        app.scrollToElement(costField)
        costField.tap()
        costField.typeText("3")
        
        let chronicField = app.textFields["chronic"]
        app.scrollToElement(chronicField)
        chronicField.tap()
        chronicField.typeText("2.45")
    }
    
    func setBadPopulationVariables() {
        let morbidityField = app.textFields["0-100%"]
        morbidityField.tap()
        morbidityField.typeText("120")
        
        let cogField = app.textFields["$/lb/head"]
        cogField.tap()
        cogField.typeText("-2")
        
        let priceField = app.textFields["$/lb"]
        app.scrollToElement(priceField)
        priceField.tap()
        priceField.typeText("50")
        
        let arrivalWeightField = app.textFields["lb"]
        app.scrollToElement(arrivalWeightField)
        arrivalWeightField.tap()
        arrivalWeightField.typeText("50")
        
        let daysField = app.textFields["days"]
        app.scrollToElement(daysField)
        daysField.tap()
        daysField.typeText("120")
        
        let adgField = app.textFields["lb/head/day"]
        app.scrollToElement(adgField)
        adgField.tap()
        adgField.typeText("3")
    }
    
    func resetValues() {
        app.buttons["Reset"].tap() // There is a hidden Reset button. Check Storyboard
        app.buttons["Reset"].tap()
        app.buttons["Reset"].tap()
    }
}

extension XCUIElement {
    internal func scrollToElement(element: XCUIElement) {
        while !element.hittable {
            swipeUp()
        }
    }
}
