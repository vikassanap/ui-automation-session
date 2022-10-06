package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author vsanap
 * @created on 29-Sep-22
 * @project pro-automation
 */
@Slf4j
public class CommonPage extends BasePage{
    WebDriver driver;

    public CommonPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(text(), \"Forms\")]")
    WebElement formsLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), \"Practice Form\")]")
    WebElement practiceFormLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), \"Upload and Download\")]")
    WebElement uploadDownloadLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), \"Browser Windows\")]")
    WebElement browserWindowsLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), \"Frames\")]")
    WebElement framesLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), \"Alerts\")]")
    WebElement alertsLink;

    public void clickFormsLink() {
        scrollAndClick(formsLink);
    }

    /**
     *
     */
    public void clickPracticeFormsLink() {
        try {
            practiceFormLink.click();
        }
        catch (ElementNotInteractableException exception) {
            log.error("got exception: {}", exception.getMessage());
            formsLink.click();
            practiceFormLink.click();
        }

    }

    public void clickUploadDownloadLink(){
        uploadDownloadLink.click();
    }

    public void clickOnBrowserWindowsLink(){
        browserWindowsLink.click();
    }

    public void clickOnFramesLink(){
        framesLink.click();
    }

    public void clickOnAlertsLink() {
        alertsLink.click();
    }

}
