package testcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Aboutphonepage;
import pages.Settingspage;
import utilities.Model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static base.Baseclass.getDriver;


@Listeners(Listener.Listenerclass.class)
public class Aboutphone {


    AppiumDriver driver=null;

    @Test
    public  void checkDeviceDetails() throws IOException {
        driver= getDriver();

        Settingspage settingspage=new Settingspage(driver);

        Aboutphonepage aboutphonepage=new Aboutphonepage(driver);

        settingspage.clickGeneralSetting();
        aboutphonepage.clickAboutPhone();

        ObjectMapper objectMapper=new ObjectMapper();
         Model aboutphonemodel= objectMapper.readValue(new File("./src/test/resources/aboutphone.json"), Model.class);

        System.out.println(aboutphonepage.verifyIOSversion());
        System.out.println(aboutphonepage.verifyModelname());
        System.out.println(aboutphonepage.verifyModelNumber());



        Assert.assertEquals(aboutphonepage.verifyIOSversion(),aboutphonemodel.osversion());
        Assert.assertEquals(aboutphonepage.verifyModelname(),aboutphonemodel.modelName());

        Assert.assertEquals(aboutphonepage.verifyModelNumber(),aboutphonemodel.modelNumber());

    }

    @Test
    public void secondTestcase()
    {
    }
}
