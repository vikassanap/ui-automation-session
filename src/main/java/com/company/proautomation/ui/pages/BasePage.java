package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author vsanap
 * @created on 29-Sep-22
 * @project pro-automation
 */
@Slf4j
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    void clearAndSet(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    void scrollAndSet(WebElement element, String value) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        clearAndSet(element, value);
    }

    void scrollAndClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void switchToFrame(WebElement frameLocator) {
        driver.switchTo().frame(frameLocator);
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchToParentWindow(String mainWindowHandle){
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException exception) {
            log.error("got no alert exception, ignoring it");
            //click
        }
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptAlertWithInput(String value) {
        driver.switchTo().alert().sendKeys(value);
        acceptAlert();
    }

    public void dismissAlertWithInput(String value) {
        driver.switchTo().alert().sendKeys(value);
        dismissAlert();
    }

    public String getAlertMessage() {
        return driver.switchTo().alert().getText().trim();
    }

    public void dragElement(WebElement source, WebElement target){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }

    //wait

    public void waitForElementToBeClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
