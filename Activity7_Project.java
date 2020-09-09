/*
 *  Adding qualifications Goal: Add employee qualifications 
 *  a. Open the OrangeHRM page and login with credentials provided 
 *  b. Find the “My Info” menu item and click it. 
 *  c. On the new page, find the Qualification option on the left side menu and click it. 
 *  d. Add Work Experience and click Save. 
 *  e. Close the browser. 
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity7_Project {
  WebDriver driver;
  @DataProvider(name="Authentication")
  public Object[][] systemCrendential() {
    return new Object[][] {{"orange","orangepassword123"}};
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test(dataProvider = "Authentication",priority=1)
  public void login(String userId, String password) {
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(userId);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test(priority=2)
  public void addWorkExperiance() throws InterruptedException {
	  Thread.sleep(3000);
	  // Find the “My Info” menu item and click it
	  driver.findElement(By.linkText("My Info")).click();
	  // On the new page, find the Qualification option on the left side menu and click it. 
	  driver.findElement(By.linkText("Qualifications")).click();
	  // Add Work Experience and click Save. 
	  driver.findElement(By.xpath("//input[@id='addWorkExperience']")).click();
	  // enter details
	  driver.findElement(By.id("experience_employer")).clear();
	  driver.findElement(By.id("experience_employer")).sendKeys("IBM");
	  driver.findElement(By.id("experience_jobtitle")).sendKeys("Selenium Tester");
	  WebElement fromDate = driver.findElement(By.id("experience_from_date"));
	  fromDate.clear();
	  fromDate.sendKeys("2000-01-01");
	  driver.findElement(By.cssSelector("textarea#experience_comments")).sendKeys("Testing Success");
	  //save
	  driver.findElement(By.id("btnWorkExpSave")).click();
	  Reporter.log("Save");
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
