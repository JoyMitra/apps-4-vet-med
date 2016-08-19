//
//  BSEUITests.swift
//  BSEUITests
//
//  Created by Shubh Chopra on 29/7/16.
//  Copyright © 2015 Null Development. All rights reserved.
//

import XCTest

import UIKit

import Parse



class BSEUITests: XCTestCase {
    var vc : ViewController!
    var vc2 : NewGroupViewController!
    override func setUp() {
        super.setUp()
        
        // Put setup code here. This method is called before the invocation of each test method in the class.
            // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
  
    func testStartupCallsEmailAlert() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
      
       // let vc = UIViewController()
        let vc = FakeAlertView()
        
        
       vc.getEmail()
        
        
                if let alert = vc.viewcontrollertopresent as? UIAlertController {
                    XCTAssertEqual("Enter Email", alert.title!)
                    XCTAssertTrue(vc.isFirstTime(), "User First Time")
                }
            
            
    }
    
    func testCorrentEmailInStartUpSavesAndCallsNameAlert() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        let vc = FakeAlertView()
        vc.getEmail()
        let app = XCUIApplication()
        if let alert = vc.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter Email", alert.title!)
            //     app.alerts["Enter Email"].textFields.elementBoundByIndex(0).tap()
        }
        app.alerts["Enter Email"].textFields["name@example.com"].typeText("Shubhchopra@gmail.com")
        let vc2 = FakeAlertView()
          //  app.alerts["Enter Email"].buttons["OK"].tap();
            if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
                XCTAssertEqual("Enter your Name", alert2.title!)
            }
            
        
        
    }
    func testIncorrectEmailonSetupTiggersError() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        
        let vc = FakeAlertView()
        vc.getEmail()
        let app = XCUIApplication()
        if let alert = vc.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter Email", alert.title!)
            //     app.alerts["Enter Email"].textFields.elementBoundByIndex(0).tap()
        }
        app.alerts["Enter Email"].textFields["name@example.com"].typeText("Shubhchopragmail.com")
        app.alerts["Enter Email"].buttons["OK"].tap()
        //  app.alerts["Enter Email"].buttons["OK"].tap();
        XCTAssertTrue(app.alerts["Incorrect Email"].exists)
        
        
        
    }

    func testCorrectEmailAndCorrentFirstNameCallsLastNameAlert() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                
                self.viewcontrollertopresent = viewcontrollertopresent
                
                
            }
        }
        
        let vc = FakeAlertView()
        vc.getEmail()
        let app = XCUIApplication()
        
        
        if let alert = vc.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter Email", alert.title!)
            //     app.alerts["Enter Email"].textFields.elementBoundByIndex(0).tap()
        }
        app.alerts["Enter Email"].textFields["name@example.com"].typeText("Shubhchopra@gmail.com")
        let vc2 = FakeAlertView()
        
        app.alerts["Enter Email"].buttons["OK"].tap()
        

        
        //  app.alerts["Enter Email"].buttons["OK"].tap();
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter your Name", alert2.title!)
        }
        
        app.alerts["Enter your Name"].textFields["Your First Name"].typeText("Shubh")
        let vc3 = FakeAlertView()
        app.alerts["Enter your Name"].buttons["OK"].tap()
        
 
        XCTAssertTrue(app.alerts["Enter your Name"].exists)

        

    
    }
    
    func testGroupIncorrectEmailTiggersIncorrectAlert()
    {
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        let app = XCUIApplication()
        if let alert = vc.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter Email", alert.title!)
        app.alerts["Enter Email"].buttons["Not now"].tap()
        }
        
            app.buttons["Groups"].tap()
            let app2 = app
            app2.tables.staticTexts["Add New Group"].tap()
            
            let elementsQuery = app.scrollViews.otherElements
            elementsQuery.textFields["Email"].tap()
        
        elementsQuery.textFields["Email"].typeText("Shubh.chopra94gmail.com");
        
            elementsQuery.textFields["Address 1"].tap()
       
        XCTAssertTrue(app.alerts["Incorrect email"].exists)
        
        
    }
    func testCorrectPhoneInGroupIsAcceptedNoAlert()
    {
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            var AlertCalled = true
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        var app = XCUIApplication()
        if (vc.isFirstTime() == true)
        {
        if let alert = vc.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter Email", alert.title!)
            app.alerts["Enter Email"].buttons["Not now"].tap()
        }
        }
        app.buttons["Groups"].tap()
        let app2 = app
        app2.tables.staticTexts["Add New Group"].tap()
        
        var elementsQuery = app.scrollViews.otherElements
        elementsQuery.textFields["Email"].swipeUp()
        

        elementsQuery.textFields["Phone"].tap()
        
        elementsQuery.textFields["Phone"].typeText("3473612600");
        
        elementsQuery.textFields["Zip"].tap()
        
       
       // app.alerts["Incorrect phone"].collectionViews.buttons["OK"].tap()
       XCTAssertFalse(app.alerts["Incorrect phone"].exists)
        
      
        
    }
    func testInCorrectNumberInGroupTriggersAlert()
    {
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        var app = XCUIApplication()
        if (vc.isFirstTime() == true)
        {
            if let alert = vc.viewcontrollertopresent as? UIAlertController {
                XCTAssertEqual("Enter Email", alert.title!)
                app.alerts["Enter Email"].buttons["Not now"].tap()
            }
        }
        app.buttons["Groups"].tap()
        let app2 = app
        app2.tables.staticTexts["Add New Group"].tap()
        let elementsQuery = app.scrollViews.otherElements
        elementsQuery.textFields["Email"].swipeUp()
        elementsQuery.textFields["Phone"].tap()
        elementsQuery.textFields["Phone"].typeText("34736126");
        elementsQuery.textFields["Zip"].tap()
      
            XCTAssertTrue(app.alerts["Incorrect phone"].exists)
    }
    func testCorrectNumberInGroupNoAlert()
    {
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        var app = XCUIApplication()
        if (vc.isFirstTime() == true)
        {
            if let alert = vc.viewcontrollertopresent as? UIAlertController {
                XCTAssertEqual("Enter Email", alert.title!)
                app.alerts["Enter Email"].buttons["Not now"].tap()
            }
        }
        app.buttons["Groups"].tap()
        let app2 = app
        app2.tables.staticTexts["Add New Group"].tap()
        
        let elementsQuery = app.scrollViews.otherElements
        elementsQuery.textFields["Email"].swipeUp()

        elementsQuery.textFields["Phone"].tap()
        
        elementsQuery.textFields["Phone"].typeText("(347)361-2600");
       XCTAssertFalse(app.alerts["Incorrect phone"].exists)
    
    }
    func testCorrectNumberInGroupNoAlert2()
    {
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        var app = XCUIApplication()
        if (vc.isFirstTime() == true)
        {
            if let alert = vc.viewcontrollertopresent as? UIAlertController {
                XCTAssertEqual("Enter Email", alert.title!)
                app.alerts["Enter Email"].buttons["Not now"].tap()
            }
        }
        app.buttons["Groups"].tap()
        let app2 = app
        app2.tables.staticTexts["Add New Group"].tap()
        
        let elementsQuery = app.scrollViews.otherElements
        elementsQuery.textFields["Email"].swipeUp()
        
        elementsQuery.textFields["Phone"].tap()
        
        elementsQuery.textFields["Phone"].typeText("347.361.2600");
        
       XCTAssertFalse(app.alerts["Incorrect phone"].exists)
        
        
    }
    func elemetswithinwindow (element: XCUIElement) -> Bool{
    
        guard element.exists && !CGRectIsEmpty(element.frame) && element.hittable else {return false}
        return CGRectContainsRect(XCUIApplication().windows.elementBoundByIndex(0).frame, element.frame)
    }
    
    func ScrollDown()
    {
       
    }
    
    func testTwoSameBullIDsTriggersConfirmationAlert()
    {
        
        let app = XCUIApplication()
    
        app.alerts["Enter Email"].collectionViews.buttons["Not now"].tap()
        app.buttons["Groups"].tap()
        
        let tablesQuery = app.tables
        tablesQuery.staticTexts["Add New Group"].tap()
        let scrollViewsQuery = app.scrollViews
        let elementsQuery = scrollViewsQuery.otherElements
        elementsQuery.textFields["Rancher Name"].tap()
        elementsQuery.textFields["Rancher Name"].typeText("Rancherxyz")
        while !elemetswithinwindow(elementsQuery.buttons["Save"]) {
            elementsQuery.textFields["Rancher Name"].swipeUp()
        }
        elementsQuery.buttons["Save"].tap()
        let addNewBullStaticText = tablesQuery.staticTexts["Add New Bull"]
        addNewBullStaticText.tap()
        app.alerts["Enter Bull ID"].textFields.elementBoundByIndex(0).tap()
        app.alerts["Enter Bull ID"].textFields.elementBoundByIndex(0).typeText("111")
         let app2 = app
        let enterBullIdAlert = app.alerts["Enter Bull ID"]
        let okButton = enterBullIdAlert.collectionViews.buttons["OK"]
        okButton.tap()
        let tagButton = app.alerts["Select Bull Type"].collectionViews.buttons["Tag"]
        tagButton.tap()
        app.navigationBars["Bull ID: 111"].childrenMatchingType(.Button).matchingIdentifier("Back").elementBoundByIndex(0).tap()
        addNewBullStaticText.tap()
        app.alerts["Enter Bull ID"].textFields.elementBoundByIndex(0).tap()
        app.alerts["Enter Bull ID"].textFields.elementBoundByIndex(0).typeText("111")
        okButton.tap()
        tagButton.tap()
       XCTAssertTrue( app.alerts["Same ID Alert!"].exists)
        
    }

    func testIncorectZipcodeAtStartupTriggersIncorrectAlert() {
    
        
        let app = XCUIApplication()
        let enterEmailAlert = app.alerts["Enter Email"]
        enterEmailAlert.tap()
        enterEmailAlert.collectionViews.textFields["name@example.com"]
        app.otherElements.containingType(.Alert, identifier:"Enter Email").element.tap()
        
        let collectionViewsQuery = app.alerts["Enter your Name"].collectionViews
        collectionViewsQuery.textFields["Your First Name"].tap()
        collectionViewsQuery.textFields["Your First Name"]
        
        let okButton = collectionViewsQuery.buttons["OK"]
        okButton.tap()
        collectionViewsQuery.textFields["Your Last Name"].tap()
        collectionViewsQuery.textFields["Your Last Name"]
        okButton.tap()
        
        let collectionViewsQuery2 = app.alerts["Enter Clinic Name"].collectionViews
        collectionViewsQuery2.textFields["Your Clinic Name"].tap()
        collectionViewsQuery2.textFields["Your Clinic Name"]
        collectionViewsQuery2.buttons["OK"].tap()
        
        let collectionViewsQuery3 = app.alerts["Enter your Address1"].collectionViews
        collectionViewsQuery3.textFields["Your Address1"].tap()
        collectionViewsQuery3.textFields["Your Address1"]
        collectionViewsQuery3.buttons["OK"].tap()
        
        let collectionViewsQuery4 = app.alerts["Enter your Address2"].collectionViews
        collectionViewsQuery4.textFields[" Your Address2"].tap()
        collectionViewsQuery4.textFields[" Your Address2"]
    
    }
    
    func testIncorrectZipAtStartUpTriggersIncorrectAlert() {
        
        class FakeAlertView: ViewController{
            var viewcontrollertopresent: UIViewController?
            override func presentViewController(viewcontrollertopresent: UIViewController, animated flag: Bool, completion: (() -> Void)?) {
                self.viewcontrollertopresent = viewcontrollertopresent
            }
        }
        // let vc = UIViewController()
        let vc = FakeAlertView()
        vc.getEmail()
        if (vc.isFirstTime() == true)
        {
            if let alert = vc.viewcontrollertopresent as? UIAlertController {
                XCTAssertEqual("Enter Email", alert.title!)
            }
        }

        let app = XCUIApplication()
        app.alerts["Enter Email"].collectionViews.textFields["name@example.com"].tap()
        app.alerts["Enter Email"].collectionViews.textFields["name@example.com"].typeText("Shubh.chopra@gmail.com")
         app.alerts["Enter Email"].buttons["OK"].tap()
        var collectionViewsQuery = app.alerts["Enter your Name"].collectionViews
        collectionViewsQuery.textFields["Your First Name"].tap()
        collectionViewsQuery.textFields["Your First Name"].typeText("Shubh")
        
        let okButton = collectionViewsQuery.buttons["OK"]
        okButton.tap()
        collectionViewsQuery.textFields["Your Last Name"].tap()
        collectionViewsQuery.textFields["Your Last Name"].typeText("Chopra")
        collectionViewsQuery = app.alerts["Enter your Name"].collectionViews
       app.alerts["Enter your Name"].buttons["OK"].tap()
        let collectionViewsQuery2 = app.alerts["Enter Clinic Name"].collectionViews
        collectionViewsQuery2.textFields["Your Clinic Name"].typeText("XYZ")
        
        let okButton2 = collectionViewsQuery2.buttons["OK"]
        okButton2.tap()
        app.alerts["Enter your Address1"].collectionViews.textFields["Your Address1"].tap()
        app.alerts["Enter your Address1"].collectionViews.textFields["Your Address1"].typeText("xyz")
        app.alerts["Enter your Address1"].buttons["OK"].tap()
        app.alerts["Enter your Address2"].collectionViews.textFields[" Your Address2"]
        app.alerts["Enter your Address2"].buttons["OK"].tap()
        app.alerts["Enter your City"].collectionViews.textFields["Your City"].tap()
        app.alerts["Enter your City"].collectionViews.textFields["Your City"].typeText("Manhattan")
        app.alerts["Enter your City"].collectionViews.textFields["Your City"].buttons["OK"].tap()
        let collectionViewsQuery3 = app.alerts["Enter your Phone"].collectionViews
        collectionViewsQuery3.textFields["Your Phone Number"].typeText("3473612600")
        
        let okButton3 = collectionViewsQuery3.buttons["OK"]
        okButton3.tap()
        
        let collectionViewsQuery4 = app.alerts["Enter your Zip Code"].collectionViews
        collectionViewsQuery4.textFields["Your Zip Code"].typeText("1234")
        collectionViewsQuery4.buttons["OK"].tap()
        XCTAssertTrue( app.alerts["Incorrect Zip"].exists)
        
    }
    
}
