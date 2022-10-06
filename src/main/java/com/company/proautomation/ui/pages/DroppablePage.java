package com.company.proautomation.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author vsanap
 * @created on 02-Oct-22
 * @project pro-automation
 */
@Slf4j
public class DroppablePage extends CommonPage{
    private WebDriver driver;

    public DroppablePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(how = How.ID, using = "draggable")
    WebElement draggableElement;

    @FindBy(how = How.ID, using = "droppable")
    WebElement droppableElement;


    public void dragElement(){
        dragElement(draggableElement, droppableElement);
    }

    public boolean isDropped() {
        //return driver.findElement(By.xpath("//div[@id=\"droppable\"]//p[contains(text(),\"Dropped\")]")).isDisplayed();
        return droppableElement.findElement(By.tagName("p")).isDisplayed();
    }
}
