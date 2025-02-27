package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static io.appium.java_client.clipboard.ClipboardContentType.URL;

public class Baseclass {

   static  public IOSDriver driver=null;
   static public ExtentReports extentReports;
  static   public ExtentTest extendtest;

    AppiumDriverLocalService localService=null;



   static public IOSDriver getDriver() throws MalformedURLException {

        XCUITestOptions xcuiTestOptions=new XCUITestOptions();

        xcuiTestOptions.setAutomationName(AutomationName.IOS_XCUI_TEST);

        xcuiTestOptions.setBundleId("com.apple.Preferences");
        xcuiTestOptions.setNoReset(false);
        xcuiTestOptions.setUdid("7B22F631-9FFB-4104-BBF8-5C65D139022E");


        driver =new IOSDriver(new URL("http://127.0.0.1:4723/"),xcuiTestOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       return driver;
    }

    @BeforeSuite
    public void setUp() throws IOException {


       localService= new AppiumServiceBuilder().withTimeout(Duration.ofSeconds(10)).build();

       // localService=AppiumDriverLocalService.buildDefaultService();

       localService.start();

       extentReports=new ExtentReports();


        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("./Reports/reports"+new Date().getTime()+".html");
        extentReports.attachReporter(extentSparkReporter);

        Properties properties=new Properties();
        properties.load(new FileInputStream(new File("./src/test/resources/model.properties")));

        Model phoneModel=new Model( Double.parseDouble(properties.getProperty("iosversion")),properties.getProperty("modelname"),properties.getProperty("modelnumber"));

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(new File("./src/test/resources/aboutphone.json"),phoneModel);


        if(localService.isRunning())
        {
            if(driver==null)
            {
                getDriver();
            }
        }



    }

    @AfterSuite
    public void teadown()
    {
        extentReports.flush();
        localService.stop();
     //   driver.close();
    }

}
