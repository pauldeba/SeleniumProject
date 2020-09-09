/*
 *  Verify that the “Directory” menu item is visible and clickable Goal: Verify that the “Directory” menu item is visible and clickable 
 *  a. Open the OrangeHRM page and login with credentials provided. 
 *  b. Locate the navigation menu. 
 *  c. Verify that the “Directory” menu item is visible and clickable. 
 *  d. If clickable, click on the menu item. 
 *  e. Verify that the heading of the page matches “Search Directory”. 
 *  f. Close the browser.
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity6_Project {
	WebDriver driver;
  
  @DataProvider(name="Authentication")
  public Object[][] credentials() {
    return new Object[][] {{"orange","orangepassword123"}};
  }
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
	  
  }
  
  @Test(dataProvider = "Authentication")
  public void login(String userId, String password) {
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(userId);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();	  
  }
  
  @Test
  public void menu() throws InterruptedException {
	  Thread.sleep(3000);
	  WebElement directory = driver.findElement(By.xpath("//b[text()='Directory']"));
	  //Verify that the “Directory” menu item is visible and clickable. 
	  //directory.isDisplayed();
	  //directory.isEnabled();
	  if(directory.isDisplayed() & directory.isEnabled() ) {
		  System.out.println("Directory is Visible and Clickable");
		  // If clickable, click on the menu item. 
		  directory.click();
		  Thread.sleep(2000);
		  // Verify that the heading of the page matches “Search Directory.
		  String val= driver.findElement(By.xpath("//h1[text()='Search Directory']")).getText();
		  Assert.assertEquals(val, "Search Directory");
		  Reporter.log("Match the Header of page");
	  } else {
		  System.out.println("Directory is not visible and Clickable");
	  }
		  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
