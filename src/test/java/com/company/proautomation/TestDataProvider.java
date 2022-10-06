package com.company.proautomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
class TestDataProvider extends AbstractTestNGSpringContextTests {

	/**
	 *
	 * @return
	 */
	@DataProvider(name = "users")
	public Object[][] getUsers() {


		return new Object[][] {
				{ "Cedric", "xyz", "Welcome" },
				{ "Vikas", "abc", "Bye" },
		};
	}


	@Test(dataProvider = "users", description = "")
	void userLogin(String userName, String password, String welcomeMessage){

		log.info("username {}, password {} and {}", userName, password, welcomeMessage);

	}


}
