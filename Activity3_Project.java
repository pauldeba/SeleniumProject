/***
 *  Logging into the site Goal: Open the site and login with the credentials provided 
 *  a. Open the browser to the login page of OrangeHRM site. 
 *  b. Find and select the username and password fields 
 *  c. Enter login credentials into the respective fields 
 *  d. Click login
 *	e. Verify that the homepage has opened. 
 *	f. Close the browser.
 */

package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Activity3_Project {
	WebDriver driver;
  
  @BeforeTest
  public void beforeTest() {
	  //instantiate the webdriver
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test
  public void testCase() throws InterruptedException {
	  WebElement userid = driver.findElement(By.id("txtUsername"));
	  WebElement password = driver.findElement(By.cssSelector("input#txtPassword"));
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  userid.click();
	  password.click();
	  
	  userid.sendKeys("orange");
	  //Thread.sleep(2000);
	  password.sendKeys("orangepassword123");
	  
	  driver.findElement(By.cssSelector("input.button")).click();
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  
	  WebElement dashboard = driver.findElement(By.xpath("//h1[text()='Dashboard']"));
	  //dashboard.isDisplayed();
	  Assert.assertTrue(dashboard.isDisplayed());
	  String text = dashboard.getText();
	  Assert.assertEquals(text, "Dashboard");
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
