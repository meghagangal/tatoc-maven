package com.org.learningMaven.myMavenProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePageActions {

	WebDriver driver;
	
	public HomePageActions(WebDriver driver) {
		this.driver = driver;
	}
	
	public void verifyPageLauched() {
		WebElement heading = driver.findElement(By.xpath("/html/body/div/div[2]/h1"));
		Assert.assertTrue(heading.isDisplayed());
		System.out.println("Page is launched");
	}
	
	public void launchPage(String string) {
		driver.get(string);
		System.out.println("User Launched URL: " + string);
	}

	public void verifylinkIsDisplayed() {
		WebElement logo = driver.findElement(By.className("title"));
		Assert.assertTrue(logo.isDisplayed());
		System.out.println("User is on homepage");
		
	}

	public void clickOnBasicCourseLink() {
		driver.findElement(By.xpath("//a[text()='Basic Course']")).click();
		System.out.println("User Clicked Basic Course link");
		
	}
	

}
