package com.company.proautomation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
class TestBeforeAfter2 extends AbstractTestNGSpringContextTests {

	@Value("${base.url}")
	private String baseURL;

	@BeforeClass
	void beforeClass(){
		log.info("this is before class 3");
	}


	@BeforeMethod
	void beforeMethod(){
		log.info("this is before method 3");
	}

	@Test
	void loginTest1() {
		log.info("login test 4");
	}

	@Test
	void loginTest2() {
		log.info("login test 5");
	}

	@AfterMethod
	void afterMethod(){
		log.info("this is after method 3");
	}

	@AfterClass
	void afterClass(){
		log.info("this is after class 3");
	}

}
