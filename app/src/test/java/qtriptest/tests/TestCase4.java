package qtriptest.tests;

import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestCase4 {

 public static RemoteWebDriver driver ;
 String lastGeneratedUsername ;

 @BeforeSuite(alwaysRun = true)
    public void setup() throws MalformedURLException
    {
        driver = DriverSingleton.getDriver();
    }
 
@Test (dataProvider = "TestCase4",dataProviderClass = DP.class,enabled=true ,description = "Verify booking history can be viewed",priority=4,groups={"Reliability Flow"})
public void  TestCase04( String newusername ,String password ,String  dataset1 ,String  dataset2 ,String dataset3 ) throws InterruptedException, MalformedURLException
{

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

          String [] data1 =  dataset1.split(";");
          String [] data2 =  dataset2.split(";");
          String [] data3 =  dataset3.split(";");

          List<String[]>  list = new ArrayList<>();

          list.add(data1);
          list.add(data2);
          list.add(data3);
          ReportSingleton.test=ReportSingleton.report.startTest( "Verify booking history can be viewed");
         for(String[]  data : list)
          {
          homepg.searchCity(data[0]);

          Thread.sleep(1000);
      


                   Assert.assertTrue(home.assertAutoCompletetext(data[0]), "Same city is not displayed");
                   Thread.sleep(1000);
                   home.SelectCity( data[0]);


                   Thread.sleep(1000);
                   AdventurePage  aPage  = new AdventurePage(driver) ;
                   

                   aPage.selectAdventure( data[1]);
                   Thread.sleep(1000);
                   Thread.sleep(1000);
                   AdventureDetailsPage adetailPage = new AdventureDetailsPage(driver); 
                   Thread.sleep(1000);
                   Thread.sleep(1000);
                   adetailPage.bookAdventure(data[2] ,  data[3], data[4]);
                   Thread.sleep(3000);
                   Assert.assertTrue( adetailPage.isBookingSuccessfull() , "Booking Failed");
                   System.out.println(adetailPage.isBookingSuccessfull());
                   Thread.sleep(3000);
                   HistoryPage history = new HistoryPage(driver);
                   Thread.sleep(3000);

                   history.getReservations();
                   Thread.sleep(3000);
                   history.clickonHome();
          }
          Thread.sleep(3000);
          HistoryPage history = new HistoryPage(driver);
          history.getReservations();
          Thread.sleep(3000);
          try {
            ReportSingleton.test.log(LogStatus.PASS, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Successfully  verified  booking history");
            Assert.assertTrue(history.verifyNumberofReservationCount()  , ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+"Failed to verify records of reservation ");
          } catch (Exception e) {
            //TODO: handle exception
            ReportSingleton.test.log(LogStatus.FAIL, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Failed to verify booking history");
          }
           

        Thread.sleep(3000);
        home.logoutUser();
}
  



@AfterSuite
public static void quitDriver() {
    System.out.println("quit()");
    driver.quit();

    ReportSingleton.report.endTest(ReportSingleton.test);
    ReportSingleton.report.flush();

}




}
