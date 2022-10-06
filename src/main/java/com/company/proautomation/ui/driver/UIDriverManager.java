package com.company.proautomation.ui.driver;

import com.company.proautomation.ui.enums.DriverType;
import com.company.proautomation.ui.enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

/**
 * This class manages selenium webdriver instance
 * @author vsanap
 * @created on 28-Sep-22
 * @project pro-automation
 */
@Component
@Slf4j
public class UIDriverManager {
    private WebDriver driver;

    @Value("${ui.driver.type}")
    private DriverType driverType;

    @Value("${ui.driver.implicit-wait}")
    private int implicitWait;

    @Value("${ui.driver.env}")
    private EnvironmentType env;

    @Value("${ui.driver.screenshot.path}")
    private String screenshotPath;

    @Value("${ui.driver.headless}")
    private boolean headlessEnabled;

    @Value("${ui.driver.hub}")
    private String hubURL;

    @Autowired
    EventListener eventListener;

    /**
     * Method to get web driver instance
     *
     * @return web driver
     */
    public WebDriver getDriver(){
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver(){
        switch (env) {
            case REMOTE:
                driver = createRemoteDriver();
                break;
            case LOCAL:
                driver = createLocalDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver(){
        switch (driverType) {
            case CHROME:
                //WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Installations\\temp\\pro-automation\\target";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);
                if(headlessEnabled){
                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments("window-size=1400,2100");
                }

                try {
                    driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                break;
            case FIREFOX:

                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds

        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);

        driver = eventFiringDecorator.decorate(driver);

        return driver;
    }

    private WebDriver createLocalDriver(){

        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--diable--notifications");
                chromeOptions.addArguments("force-device-scale-factor=0.80");
                String downloadDir = "C:\\Installations\\temp\\pro-automation\\target";
                HashMap prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
                prefs.put("safebrowsing.enabled", "false");
                chromeOptions.setExperimentalOption("prefs", prefs);

                if(headlessEnabled){
                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments("window-size=1400,2100");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:

                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //default 0 seconds, 0.5 seconds

        EventFiringDecorator eventFiringDecorator = new EventFiringDecorator(eventListener);

        driver = eventFiringDecorator.decorate(driver);

        return driver;
    }

    public void closeBrowser(){
        driver.close();
        driver.quit();
        driver = null;
    }

    public void takeScreenshot(String testCaseName){

        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        String fileWithPath = screenshotPath +
                testCaseName.trim().replaceAll(" ", "_") + ".png";

        File DestFile=new File(fileWithPath);

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        }
        catch (IOException exception) {
            log.error("can not capture screenshot: {}", exception.getMessage());
        }

    }
}
