package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactory.*;

public class Aboutphonepage {


    @iOSXCUITFindBy(iOSNsPredicate = "name == \"About\" AND label == \"About\" AND type == \"XCUIElementTypeCell\"")
    private WebElement aboutsection;

    @iOSXCUITFindBy(accessibility = "iOS Version")
    WebElement iosVersion;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[`name == \"Model Name\"`]")
    WebElement modelname;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name=\"Model Number\"]")
    WebElement modelNumber;

    public Aboutphonepage(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void clickAboutPhone()
    {

        aboutsection.click();

    }

    public double verifyIOSversion()
    {


     return Double.parseDouble(iosVersion.getText());
    }

    public String verifyModelname()
    {
     return modelname.getText();
    }

    public String verifyModelNumber()
    {
      return modelNumber.getText();
    }


}
