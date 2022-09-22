package com.company.proautomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
class TestUserAccount extends AbstractTestNGSpringContextTests {

	@Value("${base.url}")
	private String baseURL;

	@BeforeClass
	void beforeClass(){
		log.info("this is before class");
	}


	@BeforeMethod
	void beforeMethod(){
		log.info("this is before method");
	}

	@Test
	void loginTest1() {
		log.info("login test 1");
	}

	@Test
	void loginTest2() {
		log.info("login test 2");
	}

	@AfterMethod
	void afterMethod(){
		log.info("this is after method");
	}

	@AfterClass
	void afterClass(){
		log.info("this is after class");
	}

}
