package com.company.proautomation;

import com.company.proautomation.base.TestListeners;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
@Listeners(TestListeners.class)
class TestDependency extends AbstractTestNGSpringContextTests {

	@Test
	void verifyLogin1(){
		log.info("verify login test case 1");
	}

	@Test(dependsOnMethods = {"verifyLogin1"})
	void verifyLogin(){
		log.info("verify login test case 0");
	}


}
