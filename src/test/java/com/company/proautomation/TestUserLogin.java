package com.company.proautomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = ProAutomationApplication.class)
@Slf4j
class TestUserLogin extends AbstractTestNGSpringContextTests {

	@Value("${base.url}")
	private String baseURL;

	@Autowired
	Student student;

	@Test
	void contextLoads() {
		log.info("Hello world!");
		System.out.println(baseURL);

		student.setName("vikas");
		student.setRollNo(12345);

		student.sayHello();

		System.out.println(student.toString());
	}

	@Test
	void browserTest() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		driver.get(baseURL);

		driver.quit();
	}

}
