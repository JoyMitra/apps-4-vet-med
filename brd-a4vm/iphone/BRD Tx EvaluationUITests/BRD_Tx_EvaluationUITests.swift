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
        useApp()
        app.buttons["Next"].tap()
        XCTAssert(app.staticTexts["Error"].exists)
    }
    
    // Test the app with invalid parameters
    func testInvalidValues() {
        resetValues()
        useApp()
        setBadPopulationVariables()
        app.buttons["Next"].tap()
        XCTAssert(app.staticTexts["Error"].exists)
        app.buttons["Okay"].tap()
        app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
        app.buttons["Reset"].tap() // There is a hidden reset button
        app.buttons["Start"].tap()
        setGoodParameterVariables()
        app.buttons["Next"].tap()
        setBadDrug1ParameterVariables()
        app.buttons["Next"].tap()
        XCTAssert(app.staticTexts["Error"].exists)
    }
    
    // Test the app with good values
    func testValidValues() {
        resetValues()
        useApp()
        setGoodParameterVariables()
        app.buttons["Next"].tap()
        XCTAssert(app.staticTexts["Drug 1"].exists)
        setGoodDrug1ParameterVariables()
        app.buttons["Next"].tap()
        XCTAssert(app.staticTexts["Drug 2"].exists)
        setGoodDrug2ParameterVariables()
        app.buttons["Compare"].tap()
        XCTAssert(app.staticTexts["Results"].exists)
    }
    
    // Test if values were retained
    func testRetainedValues() {
        testValidValues()
        app.terminate()
        app.launch()
        useApp()
        app.buttons["Next"].tap()
        app.buttons["Next"].tap()
        app.buttons["Compare"].tap()
        XCTAssert(app.staticTexts["Results"].exists)
        let result = app.textViews.elementBoundByIndex(0).value
        XCTAssertTrue(result!.containsString("99.76"))
    }
    
    // Test to see if we retained previously retained values
    func testRetaineChangedValues() {
        resetValues()
        resetValues()
        useApp()
        setGoodParameterVariables()
        app.buttons["Next"].tap()
        app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
        let rawMorbidity = String(app.textFields["0-100%"].value!)
        XCTAssertEqual(rawMorbidity,"30")
        changeValue()
        app.buttons["Next"].tap()
        app.navigationBars.buttons.elementBoundByIndex(0).tap() // Back button
        let modMorbidity = String(app.textFields["0-100%"].value!)
        XCTAssertEqual(modMorbidity,"50")
    }
    
    // Start using the app by click the "Start" button
    func useApp() {
        XCTAssert(app.staticTexts["WELCOME"].exists)
        app.buttons["Start"].tap()
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
    }
    
}

extension XCUIElement {
    internal func scrollToElement(element: XCUIElement) {
        while !element.hittable {
            swipeUp()
        }
    }
}
