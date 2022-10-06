package com.company.proautomation;

import com.company.proautomation.base.TestListeners;
import com.company.proautomation.ui.driver.UIDriverManager;
import com.company.proautomation.ui.helpers.AssertHelper;
import com.company.proautomation.ui.pages.*;
import com.company.proautomation.ui.pages.constants.ConfirmPopupConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
@Listeners(TestListeners.class)
class TestForms extends AbstractTestNGSpringContextTests {

	@Value("${base.url}")
	private String baseURL;

	@Value("${windows.url}")
	private String windowsURL;

	@Value("${droppable.url}")
	private String droppableURL;

	@Value("${dynamic.url}")
	private String dynamicUrl;

	@Autowired
	UIDriverManager driverManager;

	@Autowired
	AssertHelper assertHelper;

	WebDriver driver;

	@BeforeMethod
	void createBrowser() {
		driver = driverManager.getDriver();
	}

	@Test(enabled = true)
	void testFormWithMandatoryFields() {
		HashMap<String, String> userDetails = new HashMap<>();
		userDetails.put("fname", "vikas");
		userDetails.put("lname", "sanap");
		userDetails.put("email", "vsanap@gmail.com");
		userDetails.put("mobile", "9988776655");
		userDetails.put("gender", "Male");

		driver.get(baseURL);

		FormPage formPage = new FormPage(driver);
		formPage.clickFormsLink();
		formPage.clickPracticeFormsLink();

		assertHelper.assertTrue(formPage.isWelcomeMessageDisplayed(), "welcome message not visible");

		formPage.fillFormWithMandatoryFields(userDetails.get("fname"), userDetails.get("lname"),
				userDetails.get("mobile"), userDetails.get("gender"), userDetails.get("email"));

		ConfirmPopup confirmPopup = new ConfirmPopup(driver);

		assertHelper.assertTrue(confirmPopup.isConfirmMessageVisible(), "confirmation message not visible");

		assertHelper.assertEquals(String.format("%s %s", userDetails.get("fname"), userDetails.get("lname")),
				confirmPopup.getAttributeValue("Student Name"), "student name mismatch");
		assertHelper.assertEquals(userDetails.get("email"), confirmPopup.getAttributeValue("Student Email"),
				"email mismatch");
		assertHelper.assertEquals(userDetails.get("gender"), confirmPopup.getAttributeValue("Gender"),
				"gender mismatch");
		assertHelper.assertEquals("123", confirmPopup.getAttributeValue("Mobile"),
				"mobile mismatch");

		confirmPopup.clickCloseButton();
	}

	@Test(enabled = false)
	void testUploadDownload() {
		driver.get(baseURL);

		FormPage formPage = new FormPage(driver);
		formPage.clickUploadDownloadLink();

		UploadDownloadPage uploadDownloadPage = new UploadDownloadPage(driver);
		uploadDownloadPage.clickOnDownloadButton();

		uploadDownloadPage.setUploadFilePath("C:\\Installations\\temp\\pro-automation\\src\\test\\resources\\application.properties");
	}

	@Test(enabled = false)
	void testBrowserWindows() {
		driver.get(windowsURL);

		BrowserWindowsPage windowsPage = new BrowserWindowsPage(driver);
		log.info(windowsPage.openNewTab());
		windowsPage.switchToParentWindow();

		log.info(windowsPage.openNewWindow());
		windowsPage.switchToParentWindow();

	}

	@Test(enabled = false)
	void testFrames() {
		driver.get(windowsURL);

		BrowserWindowsPage windowsPage = new BrowserWindowsPage(driver);
		windowsPage.clickOnFramesLink();

		FramesPage framesPage = new FramesPage(driver);

		log.info("frame 1: {}",framesPage.switchToFrame1());

		framesPage.switchToParentFrame();

		log.info("frame 2: {}",framesPage.switchToFrame2());

	}

	@Test(enabled = false)
	void testAlerts() {
		driver.get(windowsURL);

		BrowserWindowsPage windowsPage = new BrowserWindowsPage(driver);
		windowsPage.clickOnAlertsLink();

		AlertsPage alertsPage = new AlertsPage(driver);
		alertsPage.clickOnSimpleAlertButton();
		log.info("simple alert text: {}", alertsPage.getAlertMessage());
		alertsPage.acceptAlert();

		alertsPage.clickOnConfirmAlertButton();
		log.info("confirm alert text: {}", alertsPage.getAlertMessage());
		alertsPage.acceptAlert();

		alertsPage.clickOnPromptAlertButton();
		log.info("simple alert text: {}", alertsPage.getAlertMessage());
		alertsPage.acceptAlertWithInput("vsanap");
	}

	@Test(enabled = false)
	void testDroppable() {
		driver.get(droppableURL);

		DroppablePage droppablePage = new DroppablePage(driver);
		droppablePage.dragElement();

		Assert.assertTrue(droppablePage.isDropped());
	}

	@Test(enabled = false)
	void testFormWithMandatoryFieldsJS() {
		HashMap<String, String> userDetails = new HashMap<>();
		userDetails.put("fname", "vikas");
		userDetails.put("lname", "sanap");
		userDetails.put("email", "vsanap@gmail.com");
		userDetails.put("mobile", "9988776655");
		userDetails.put("gender", "Male");

		driver.get(baseURL);

		FormPage formPage = new FormPage(driver);
		formPage.clickFormsLink();
		formPage.clickPracticeFormsLink();

		assertHelper.assertTrue(formPage.isWelcomeMessageDisplayed(), "welcome message not visible");

		formPage.fillFormWithMandatoryFieldsJS(userDetails.get("fname"), userDetails.get("lname"),
				userDetails.get("mobile"));
	}

	@Test(enabled = false)
	void testExceptions1() {

		driver.get(baseURL);

		FormPage formPage = new FormPage(driver);
		formPage.clickFormsLink();
		formPage.clickPracticeFormsLink();

		formPage.clickFormsLink();
		formPage.clickPracticeFormsLink();
	}

	@Test(enabled = false)
	void testExceptions2() {
		driver.get(windowsURL);

		BrowserWindowsPage windowsPage = new BrowserWindowsPage(driver);
		windowsPage.clickOnAlertsLink();

		AlertsPage alertsPage = new AlertsPage(driver);
		alertsPage.clickOnSimpleAlertButton();
		log.info("simple alert text: {}", alertsPage.getAlertMessage());
		alertsPage.acceptAlert();
		alertsPage.acceptAlert();
	}

	@Test(enabled = false)
	void testDynamicURL() {
		driver.get(dynamicUrl);

		DynamicPage dynamicPage = new DynamicPage(driver);

		dynamicPage.clickOnVisibleAfterButton();
	}

	@Test(enabled = false)
	void testFluentWait() {
		driver.get(dynamicUrl);

		DynamicPage dynamicPage = new DynamicPage(driver);

		Assert.assertTrue(dynamicPage.ifButtonColorChange());
	}

	@AfterMethod
	void closeBrowser(ITestResult testResult){
		if( testResult.getStatus() == ITestResult.FAILURE) {
			driverManager.takeScreenshot(testResult.getMethod().getMethodName());
		}
		//driverManager.closeBrowser();
	}

}
