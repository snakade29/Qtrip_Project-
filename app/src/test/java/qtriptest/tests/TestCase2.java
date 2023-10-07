package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase2 {

    public static RemoteWebDriver driver;


    @BeforeSuite(alwaysRun = true)
    public void setup() throws MalformedURLException {
        driver = DriverSingleton.getDriver();
    }


    @Test(dataProvider = "TestCase2", dataProviderClass = DP.class,description = "Verify Search and Filter flow  ",priority=2,groups={"Search and Filter flow"})
    public void TestCase02(  String CityName, String CategoryFilter, String DurationFilter,
            String expectedFilterResult, String expectedUnfilteredResult)
            throws InterruptedException, MalformedURLException {

        ReportSingleton.test=ReportSingleton.report.startTest( "Verify that search and filter Work fine ");
         String CityNotPresent = "Delhi"  ;
        HomePage home = new HomePage(driver);


        home.navigateToHomePage();

        home.searchCity(CityNotPresent);

        Thread.sleep(2000);


        Assert.assertTrue(home.verifyCityNotFound(), "No matches found is not displayed ");

        home.searchCity(CityName);

        Thread.sleep(1000);



        Assert.assertTrue(home.assertAutoCompletetext(CityName), "Same city is not displayed");
        Thread.sleep(1000);
        home.SelectCity(CityName);

        Thread.sleep(2000);



        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.clearFilter();
        Thread.sleep(1000);
        int expectedUnfilterCount = Integer.parseInt(expectedUnfilteredResult);

        Assert.assertEquals(adventurePage.getResultCount(), expectedUnfilterCount);

        adventurePage.setFilterValue(DurationFilter);

        adventurePage.setCategoryValue(CategoryFilter);

        int expectedFiltercount = Integer.parseInt(expectedFilterResult);

              try {
                Assert.assertEquals(adventurePage.getResultCount(), expectedFiltercount);
                ReportSingleton.test.log(LogStatus.PASS,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Succesfully  Verify Search and Filter flow ");
} catch (Exception e) {
    //TODO: handle exception
    ReportSingleton.test.log(LogStatus.FAIL,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Failed to Verify Search and Filter flow ");

}
        



    }

    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
       
    }



}
