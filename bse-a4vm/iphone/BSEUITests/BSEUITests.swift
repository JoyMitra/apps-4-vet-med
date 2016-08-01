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
  
    func testEmailAlert() {
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
    
    func testNameAlert() {
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
    func testIncorrectFail() {
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
        let vc2 = FakeAlertView()
        
        app.alerts["Enter Email"].buttons["OK"].tap()
        
        //  app.alerts["Enter Email"].buttons["OK"].tap();
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect Email", alert2.title!)
        }
        
        
        
    }

    func testNoName() {
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
        
 
        
        //  app.alerts["Enter Email"].buttons["OK"].tap();
        if let alert3 = vc3.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Enter your Name", alert3.title!)
        }
        

    
    }
    
    func testGroupEmail()
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
       let vc2 = FakeAlertView()
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect email", alert2.title!)
        }
        
    }
    func testCorrectPhoneInGroupIsAccepted()
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
        
        elementsQuery.textFields["Phone"].typeText("3473612600");
        
        elementsQuery.textFields["Zip"].tap()
        let vc2 = FakeAlertView()
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect phone", alert2.title!)
        }
        app = XCUIApplication()
        
        app.alerts["Incorrect phone"].buttons["OK"].tap();

        
    }
    func testGroupNumberPass()
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
            if let alert = vc.viewcontrollertopresent as? UIAlertController
            {
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
        let vc2 = FakeAlertView()
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect phone", alert2.title!)
        }
        app = XCUIApplication()
       
        app.alerts["Incorrect phone"].buttons["OK"].tap();
        
    }
    func testGroupNumberFail2()
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
        
        elementsQuery.textFields["Zip"].tap()
        let vc2 = FakeAlertView()
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect phone", alert2.title!)
        }
        app = XCUIApplication()
        
        app.alerts["Incorrect phone"].buttons["OK"].tap();
    }
    func testGroupNumberFail3()
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
        
        elementsQuery.textFields["Zip"].tap()
        let vc2 = FakeAlertView()
        if let alert2 = vc2.viewcontrollertopresent as? UIAlertController {
            XCTAssertEqual("Incorrect phone", alert2.title!)
        }
        app = XCUIApplication()
        
        app.alerts["Incorrect phone"].buttons["OK"].tap();
    }
    

    
}
