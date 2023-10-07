package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
private static RemoteWebDriver  driver ;

    // Private constructor to prevent instantiation from other classes
    public DriverSingleton() {
    }

    // Public method to get the instance of the WebDriver
    public static RemoteWebDriver getDriver() throws MalformedURLException {
        if (driver == null)
        {
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
            driver.manage().window().maximize();
            System.out.println("createDriver()");
        }
        return driver;   
        }
       

    // Public method to quit the WebDriver instance
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}




    
 