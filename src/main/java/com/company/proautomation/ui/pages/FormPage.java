package com.company.proautomation.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
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
public class FormPage extends CommonPage{
    WebDriver driver;

    public FormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    @FindBy(how = How.TAG_NAME, using ="h5" )
    WebElement formMessageHolder;

    @FindBy(how = How.ID, using = "firstName")
    WebElement firstNameInput;

    @FindBy(how = How.ID, using = "lastName")
    WebElement lastNameInput;

    @FindBy(how = How.ID, using = "userEmail")
    WebElement emailInput;

    @FindBy(how = How.NAME, using = "gender")
    List<WebElement> genderRadio;

    @FindBy(how = How.ID, using = "userNumber")
    WebElement mobileInput;

    @FindBy(how = How.ID, using = "submit")
    WebElement submitButton;

    public void setFirstNameInput(String value) {
        scrollAndSet(firstNameInput, value);
    }

    public boolean isWelcomeMessageDisplayed() {
        return formMessageHolder.isDisplayed();
    }

    public void setLastNameInput(String value) {
        scrollAndSet(lastNameInput, value);
    }

    public void setMobileInput(String value) {
        scrollAndSet(mobileInput, value);
    }

    public void setEmailInput(String value) {
        scrollAndSet(emailInput, value);
    }

    public void selectGender(String value) {
        for (WebElement gender: genderRadio) {
            if (gender.getAttribute("value").trim().equalsIgnoreCase(value)) {
                scrollAndClick(gender);
            }
        }
    }

    public void clickSubmit(){
        scrollAndClick(submitButton);
    }

    public void fillFormWithMandatoryFields(String fname, String lname, String mobile, String gender,
                                            String email) {
        setFirstNameInput(fname);
        setLastNameInput(lname);
        setMobileInput(mobile);
        setEmailInput(email);
        selectGender(gender);

        clickSubmit();
    }

    public void fillFormWithMandatoryFieldsJS(String fname, String lname, String mobile) {
        setFirstNameInputJS(fname);
        setLastNameInputJS(lname);
        setMobileInputJS(mobile);

        clickSubmitJS();
    }

    private void clickSubmitJS() {
        clickByJS(submitButton);
    }

    public void clickByJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void setValueByJS(WebElement element, String value){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='"+ value +"';", element);
    }

    private void setMobileInputJS(String mobile) {
        setValueByJS(mobileInput, mobile);
    }

    private void setLastNameInputJS(String lname) {
        setValueByJS(lastNameInput, lname);
    }

    private void setFirstNameInputJS(String fname) {
        setValueByJS(firstNameInput, fname);
    }
}
