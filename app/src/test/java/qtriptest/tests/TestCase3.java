package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase3 {
    public static RemoteWebDriver driver; 
String lastGeneratedUsername  ;



@BeforeSuite(alwaysRun = true)
public void setup() throws MalformedURLException
{
    driver = DriverSingleton.getDriver();
}
    // Launch Browser using Zalenium
 


@Test  (dataProvider = "TestCase3",dataProviderClass = DP.class,description = "verify Booking and Cancellation Flow",priority=3,groups={"Booking and Cancellation Flow"}) 
public void TestCase03 ( String newusername ,String password ,String searchcity ,String adventurename ,String guestname ,String date ,String count) throws InterruptedException, MalformedURLException
{
    ReportSingleton.test=ReportSingleton.report.startTest("Booking and Cancellation Flow");
     boolean generateRandomUsername = true ;
         
        HomePage home = new HomePage(driver);
         
            home.navigateToHomePage();
            Thread.sleep(1000);
            home.ClickOnRegister();

            Thread.sleep(1000);

            RegisterPage register = new RegisterPage(driver);

            register.RegisterNewUser(newusername, password,password  , generateRandomUsername);


            Thread.sleep(1000);
            lastGeneratedUsername = register.lastGeneratedUsername();

                LoginPage login = new LoginPage(driver);
                Thread.sleep(1000);
                    
              login.PerformLogin(lastGeneratedUsername,password);


              Thread.sleep(1000);

              HomePage homepg = new HomePage(driver);

              Thread.sleep(1000);

              homepg.searchCity(searchcity);

              Thread.sleep(1000);
          


                       Assert.assertTrue(home.assertAutoCompletetext(searchcity), "Same city is not displayed");
                       Thread.sleep(1000);
                       home.SelectCity(searchcity);


                    //    Thread.sleep(5000);
                    Thread.sleep(2000);
                       AdventurePage  aPage  = new AdventurePage(driver) ;
                    //    Thread.sleep(5000);
                    Thread.sleep(1000);
                       aPage.selectAdventure( adventurename );
                    //    Thread.sleep(5000);
                       Thread.sleep(1000);
                     
                       AdventureDetailsPage adetailPage = new AdventureDetailsPage(driver); 
                       Thread.sleep(1000);
                    //    Thread.sleep(1000);
                       adetailPage.bookAdventure(guestname, date, count);
                       Thread.sleep(10000);

                       try {

                        
                        Assert.assertTrue( adetailPage.isBookingSuccessfull() , "Booking Failed");
                        ReportSingleton.test.log(LogStatus.PASS, "Booking Successfull");
                    } catch (Exception e) {
                        //TODO: handle exception
                        ReportSingleton.test.log(LogStatus.PASS, "Booking Failed");
                       }
                       
                       
                       Thread.sleep(2000);
                       HistoryPage history = new HistoryPage(driver);
                       Thread.sleep(2000);

                       history.getReservations();
                       Thread.sleep(3000);
                       history.cancelreservation();
                       Thread.sleep(3000);
                       driver.navigate().refresh();

                       Thread.sleep(3000);
                       try {
                        Assert.assertFalse(history.transactionId(), "Reservation Not canceled");
                        ReportSingleton.test.log(LogStatus.PASS, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+"Cancelation Successfull");
                       } catch (Exception e) {
                        //TODO: handle exception
                        ReportSingleton.test.log(LogStatus.FAIL, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+"Cancelation Failed");
                       }
                        
    

                        home.logoutUser();

                        Thread.sleep(3000);
                        

}


@AfterSuite
public static void quitDriver() {
    System.out.println("quit()");
    driver.quit();
}





}
