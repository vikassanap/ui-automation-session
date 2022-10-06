package com.company.proautomation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
class TestBeforeAfter1 extends AbstractTestNGSpringContextTests {

	@Value("${base.url}")
	private String baseURL;

	@BeforeSuite
	void beforeSuite(){
		log.info("this is before suite");
	}


	@BeforeTest
	void beforeTest(){
		log.info("this is before test");
	}

	@BeforeClass
	void beforeClass(){
		log.info("this is before class 1");
	}


	@BeforeMethod
	void beforeMethod(){
		log.info("this is before method 1");
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
		log.info("this is after method 1");
	}

	@AfterClass
	void afterClass(){
		log.info("this is after class 1");
	}

	@AfterTest
	void afterTest(){
		log.info("this is after test");
	}

	@AfterSuite
	void afterSuite(){
		log.info("this is after suite");
	}
}
