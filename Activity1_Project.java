/***
 * Verify the website title Goal: Read the title of the website and verify the text 
 * 
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;

public class Activity1_Project {
	WebDriver driver;
  
  
  @BeforeMethod
  public void beforeMethod() {
	  // initiate the Driver instances
	  driver = new FirefoxDriver();
	  // get the title of Website
	  driver.get("http://alchemy.hguy.co/orangehrm");  
  }
  
  @Test
  public void testCase() {
	  // get the title of the page
	  String title = driver.getTitle();
	  // print the title to console
	  System.out.println("Title of the page:"+title);
	  Reporter.log("Title of the page displayed"+title);
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
