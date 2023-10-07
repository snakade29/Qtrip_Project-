package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

RemoteWebDriver driver ;
String url ="https://qtripdynamic-qa-frontend.vercel.app/pages/login";

public LoginPage(RemoteWebDriver driver) {
    this.driver = driver ;
    PageFactory.initElements(this.driver,this);
}
public void navigateToLoginPage() {
    // if (!driver.getCurrentUrl().equals(this.url)) {
    //     driver.get(this.url);
    // }

    SeleniumWrapper.navigate(driver, url);
}

    @FindBy(xpath=" //button[contains(text(),'Login to QTrip')]")
    WebElement loginBtn ;


    @FindBy(name="email")
    WebElement usernametxt ;
    
    @FindBy(xpath="//input[@name='password']") 
    WebElement  passwordtxt ;


    

    public void PerformLogin(String username ,String password) throws MalformedURLException
    {
        // usernametxt.sendKeys(username);
        // passwordtxt.sendKeys(password);
        // loginBtn.click();
        SeleniumWrapper.sendKeys(usernametxt, username );
        SeleniumWrapper.sendKeys(passwordtxt, password );
        SeleniumWrapper.click(loginBtn);
    }


   



}
