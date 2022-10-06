package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author vsanap
 * @created on 01-Oct-22
 * @project pro-automation
 */
@Slf4j
public class FramesPage extends CommonPage{
    WebDriver driver;

    public FramesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "frame1")
    WebElement frame1;

    @FindBy(how = How.ID, using = "frame2")
    WebElement frame2;

    public String switchToFrame1(){
        switchToFrame(frame1);
        return driver.findElement(By.id("sampleHeading")).getText().trim();
    }

    public String switchToFrame2(){
        switchToFrame(frame2);
        return driver.findElement(By.id("sampleHeading")).getText().trim();
    }
}
