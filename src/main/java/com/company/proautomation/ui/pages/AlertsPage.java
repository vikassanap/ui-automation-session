package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author vsanap
 * @created on 02-Oct-22
 * @project pro-automation
 */
@Slf4j
public class AlertsPage extends CommonPage{
    private WebDriver driver;

    public AlertsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "alertButton")
    WebElement simpleAlertButton;

    @FindBy(how = How.ID, using = "promtButton")
    WebElement promptAlertButton;

    @FindBy(how = How.ID, using = "confirmButton")
    WebElement confirmAlertButton;

    public void clickOnSimpleAlertButton() {
        simpleAlertButton.click();
    }

    public void clickOnPromptAlertButton() {
        promptAlertButton.click();
    }

    public void clickOnConfirmAlertButton() {
        confirmAlertButton.click();
    }
}
