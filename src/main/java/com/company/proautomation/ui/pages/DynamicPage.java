package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author vsanap
 * @created on 04-Oct-22
 * @project pro-automation
 */
@Slf4j
public class DynamicPage extends CommonPage{
    private WebDriver driver;
    public DynamicPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "enableAfter")
    WebElement enableAfterButton;

    @FindBy(how = How.ID, using = "colorChange")
    WebElement colorChangeButton;

    @FindBy(how = How.ID, using = "visibleAfter")
    WebElement visibleAfterButton;

    public void clickOnEnableAfterButton() {
        waitForElementToBeClickable(enableAfterButton, 30);
        enableAfterButton.click();
    }

    public void clickOnVisibleAfterButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(enableAfterButton));
        enableAfterButton.click();
    }

    public boolean ifButtonColorChange() {

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);

        wait.until(ExpectedConditions.attributeContains(colorChangeButton, "class","danger"));
        return true;
    }



}
