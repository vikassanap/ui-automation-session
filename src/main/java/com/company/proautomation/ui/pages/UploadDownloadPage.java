package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
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
public class UploadDownloadPage extends CommonPage{
    private WebDriver driver;

    public UploadDownloadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "downloadButton")
    WebElement downloadButton;

    @FindBy(how = How.ID, using = "uploadFile")
    WebElement uploadInput;

    public void clickOnDownloadButton(){
        downloadButton.click();
    }

    public void setUploadFilePath(String path) {
        uploadInput.sendKeys(path);
    }

}
