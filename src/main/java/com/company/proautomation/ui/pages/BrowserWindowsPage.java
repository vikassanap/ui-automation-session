package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;
import java.util.Set;

/**
 * @author vsanap
 * @created on 01-Oct-22
 * @project pro-automation
 */
@Slf4j
public class BrowserWindowsPage extends CommonPage{
    private WebDriver driver;
    private String mainWindowHandle;
    private String childWindowHandle;

    public BrowserWindowsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, driver);
    }

    @FindBy(how = How.ID, using = "tabButton")
    WebElement newTabButton;

    @FindBy(how = How.ID, using = "windowButton")
    WebElement newWindowButton;

    public String openNewTab(){
        String text = null;
        mainWindowHandle = driver.getWindowHandle();
        newTabButton.click();

        Set<String> windowHandles= driver.getWindowHandles();

        for (String windowHandle: windowHandles) {
            if(!Objects.equals(mainWindowHandle, windowHandle)) {
                driver.switchTo().window(windowHandle);
                childWindowHandle = windowHandle;
                text = driver.findElement(By.xpath("//h1[contains(text(),\"This is a sample page\")]")).getText().trim();
                break;
            }
        }
        return text;
    }

    public void switchToParentWindow() {
        switchToParentWindow(mainWindowHandle);
    }

    public String openNewWindow(){
        String text = null;
        mainWindowHandle = driver.getWindowHandle();
        newWindowButton.click();

        Set<String> windowHandles= driver.getWindowHandles();

        for (String windowHandle: windowHandles) {
            if(!Objects.equals(mainWindowHandle, windowHandle)) {
                driver.switchTo().window(windowHandle);
                childWindowHandle = windowHandle;
                text = driver.findElement(By.xpath("//h1[contains(text(),\"This is a sample page\")]")).getText().trim();
                break;
            }
        }
        return text;
    }

}
