package com.company.proautomation;

import com.company.proautomation.base.TestListeners;
import com.company.proautomation.base.TestRetry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
@Listeners(TestListeners.class)
class TestAssertions extends AbstractTestNGSpringContextTests {

	@Test(retryAnalyzer = TestRetry.class)
	void softAssertions(){

		SoftAssert softAssert = new SoftAssert();

		log.info("this is test case soft assert");
		log.info("this is step 1");
		softAssert.assertEquals(1,1);

		log.info("this is step 2");
		softAssert.assertEquals(1,2);

		log.info("this is step 3");
		softAssert.assertEquals(2,2);

		softAssert.assertAll();
	}


	@Test
	void hardAssertions(){
		log.info("this is test case hard assert");
		log.info("this is step 1");
		Assert.assertEquals(1,1);

		log.info("this is step 2");
		Assert.assertEquals(1,2);

		log.info("this is step 3");
		Assert.assertEquals(2,2);
	}


}
