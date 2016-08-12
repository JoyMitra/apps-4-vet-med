package bci.ksu.edu.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrdTxEvalUITest {

    private AndroidDriver driver;

    @BeforeClass
    public void setup() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        // Need to build apk and specify path
        File app = new File("C:\\Users\\allan\\Projects\\apks\\app-debug.apk");
        if (app.exists()) {
            System.out.println("Located apk: " + app.toString());
            caps.setCapability(MobileCapabilityType.APP, app);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, 6.0);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "5.1 WVGA API 23");
            caps.setCapability("avd", "5_1_WVGA_API_23");
            caps.setCapability("appPackage", "bci.ksu.edu.brdtreatmentcalculator");
            caps.setCapability("appActivity", "bci.ksu.edu.brdtc.MainActivity");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.resetApp();
            System.out.println("Capabilities set");
        }
        else {
            System.out.println("Could not find apk..shutting down");
            tearDown();
        }
    }

    @Test
    public void test() throws Exception {
        // Landing Page
        WebElement weContinue = driver.findElementById("mainPageNextButton");
        weContinue.click();

        // Population Page
        WebElement weParamNext = driver.findElementById("popParamPageNextButton");
        weParamNext.click();
        WebElement weMorbidityET = driver.findElementById("morbidityEditText");
        weMorbidityET.sendKeys("30");
        WebElement weCogET = driver.findElementById("costOfGainEditText");
        weCogET.sendKeys("1");
        WebElement weSpET = driver.findElementById("priceReceivedAtSaleEditText");
        weSpET.sendKeys("1.45");
        WebElement weArrivalWeightET = driver.findElementById("arrivalWeightEditText");
        weArrivalWeightET.sendKeys("500");
        WebElement weDaysET = driver.findElementById("daysOnFeedEditText");
        weDaysET.sendKeys("245");
        verticalScroll();
        WebElement weAdgET = driver.findElementById("adgHealthyCattleEditText");
        weAdgET.sendKeys("3.4");
        weParamNext.click();
        Assert.assertNotNull(driver.findElementById("drugOneNameLabelTextView"));

        // Drug One Page
        WebElement weTxaNext = driver.findElementById("drugOnePageNextButton");
        weTxaNext.click();
        WebElement weTxaNameET = driver.findElementById("drugOneNameTextView");
        weTxaNameET.sendKeys("Terramycin");
        WebElement weTxaTfpET = driver.findElementById("drugOneTreatmentFailurePercentEditText");
        driver.tap(1, weTxaTfpET, 1);
        Thread.sleep(5000);
        weTxaTfpET.sendKeys("30");
        WebElement weTxaCfrET = driver.findElementById("drugOneCaseFatalityRiskEditText");
        weTxaCfrET.sendKeys("5");
        WebElement weTxaCtET = driver.findElementById("drugOneCostOfTreatmentEditText");
        weTxaCtET.sendKeys("51");
        weTxaNext.click();
        WebElement weTxaCprET = driver.findElementById("drugOneChronicPercentEditText");
        weTxaCprET.sendKeys("3");
        weTxaCtET.clear();
        weTxaCtET.sendKeys("5");
        WebElement weTxaBack = driver.findElementById("drugOnePageBackButton");
        weTxaBack.click();
        weParamNext.click();
        Assert.assertEquals(weTxaCtET.getText(), "5");
        weTxaNext.click();
        Assert.assertNotNull(driver.findElementById("drugTwoNameLabelTextView"));

        // Drug Two Page
        WebElement weTxbNext = driver.findElementById("drugTwoPageNextButton");
        WebElement weTxbNameET = driver.findElementById("drugTwoNameTextView");
        weTxbNameET.sendKeys("Oxytet");
        WebElement weTxbTfpET = driver.findElementById("drugTwoTreatmentFailurePercentEditText");
        driver.tap(1, weTxbTfpET, 1);
        Thread.sleep(5000);
        weTxbTfpET.sendKeys("20");
        WebElement weTxbCfrET = driver.findElementById("drugTwoCaseFatalityRiskEditText");
        weTxbCfrET.sendKeys("4.5");
        WebElement weTxbCtET = driver.findElementById("drugTwoCostOfTreatmentEditText");
        weTxbCtET.sendKeys("25");
        WebElement weTxbCprET = driver.findElementById("drugTwoChronicPercentEditText");
        weTxbCprET.sendKeys("2.7");
        weTxbNext.click();
        Assert.assertNotNull(driver.findElementById("resultsPageResetButton"));

        // Results Page
        WebElement weResultsBack = driver.findElementById("resultsPageBackButton");
        weResultsBack.click();
        Assert.assertEquals(weTxbNameET.getText(), "Oxytetracycline");
        weTxbNext.click();
        WebElement weResultsReset = driver.findElementById("resultsPageResetButton");
        weResultsReset.click();
        Assert.assertNotNull(weContinue);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Shutting down test");
        driver.closeApp();
    }

    private void verticalScroll() {
        Dimension size = driver.manage().window().getSize();
        int y_start=(int)(size.height*0.60);
        int y_end=(int)(size.height*0.30);
        int x=size.width/2;
        driver.swipe(x,y_start,x,y_end,4000);
    }
}
