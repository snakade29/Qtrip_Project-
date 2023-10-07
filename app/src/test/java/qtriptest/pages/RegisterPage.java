package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {



    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    public String lastGeneratedUsername = "";

    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void navigateToRegisterPage() {

        SeleniumWrapper.navigate(driver, url);
        // if (!driver.getCurrentUrl().equals(this.url)) {
        //     driver.get(this.url);
        // }
    }





 

      @FindBy(name="email")
      WebElement usernametxt  ;

     
     
      @FindBy(name ="password")
      WebElement passwordtxt ;

      @FindBy(name="confirmpassword")
      WebElement ConfirmPasswordtxt;

      @FindBy(xpath="//button[contains(text(),'Register Now')]")
      WebElement RegisterNowBtn ;


    public void RegisterNewUser(String username , String password , String ConfirmPassword , Boolean generateRandomUsername  ) throws MalformedURLException
    {
          
       if (generateRandomUsername == true)
      username = username+UUID.randomUUID().toString();
          lastGeneratedUsername=username; 
        // usernametxt.sendKeys(username);
        // passwordtxt.sendKeys( password);
        // ConfirmPasswordtxt.sendKeys(ConfirmPassword);
        // RegisterNowBtn.click() ;
        SeleniumWrapper.sendKeys(usernametxt, username );
        SeleniumWrapper.sendKeys(  passwordtxt, password  );
        SeleniumWrapper.sendKeys(   ConfirmPasswordtxt , ConfirmPassword );
        SeleniumWrapper.click(RegisterNowBtn);
    }



    public String lastGeneratedUsername ()
    {
        return lastGeneratedUsername;
    }
}
 