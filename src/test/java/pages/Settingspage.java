package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Settingspage {



  @iOSXCUITFindBy(id ="General")
  private  WebElement general=null;


  public Settingspage(AppiumDriver driver)
  {
      PageFactory.initElements(new AppiumFieldDecorator(driver),this);

  }

  public void clickGeneralSetting()
  {

      general.click();

  }

}
