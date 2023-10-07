package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
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

public class  TestCase1   {

    public static RemoteWebDriver driver;
    String lastGeneratedUsername = "";


    @BeforeSuite(alwaysRun = true)
    public void setup() throws IOException
    {
        driver = DriverSingleton.getDriver();
        ReportSingleton.report= ReportSingleton.getReportInstance();
       
       
    }



    @Test (dataProvider = "TestCase1",dataProviderClass =DP.class,enabled=true ,description = "Verify user Registration-Login-Logout",priority=1,groups={"Login Flow"})

    public void TestCase01(String UserName , String Password) throws InterruptedException, MalformedURLException {

        ReportSingleton.test=ReportSingleton.report.startTest( "Verify User Registration Login-Logout");
        boolean generateRandomUsername = true;
        boolean status = false ;
        HomePage home = new HomePage(driver);
      
        home.navigateToHomePage();
      
        home.ClickOnRegister();
       
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,
                "https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        
        RegisterPage register = new RegisterPage(driver);

        register.RegisterNewUser(UserName, Password , Password,
                generateRandomUsername);
        Thread.sleep(5000);
        currentUrl = driver.getCurrentUrl();


        try {


            Assert.assertEquals(currentUrl,"https://qtripdynamic-qa-frontend.vercel.app/pages/login");
            ReportSingleton.test.log(LogStatus.PASS, "Sucessfully registered");
        
        } catch (Exception e) {
             
            ReportSingleton.test.log(LogStatus.FAIL, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Test Failed");  
        }
         


        

        lastGeneratedUsername = register.lastGeneratedUsername();

        LoginPage login = new LoginPage(driver);
        
        login.PerformLogin(lastGeneratedUsername,  Password);
      

        Thread.sleep(5000);
        
 

        try {
            Assert.assertTrue(home.isUserLoggedIn());
            ReportSingleton.test.log(LogStatus.PASS, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Sucessfully Login");
        } catch (Exception e) {
            //TODO: handle exception

            ReportSingleton.test.log(LogStatus.FAIL,  ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Failed To  Login");
            
 
        }
         
        

        home.logoutUser();
 

        Assert.assertFalse(home.isUserLoggedIn());
      


        
        



    }



     



    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();

        
    }



}
