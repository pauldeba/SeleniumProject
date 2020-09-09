/***
 *  Get the url of the header image Goal: Print the url of the header image to the console 
 *  a. Open a browser. 
 *  b. Navigate to ‘http://alchemy.hguy.co/orangehrm’. 
 *  c. Get the url of the header image. 
 *  d. Print the url to the console. 
 *  e. Close the browser. 
 * 
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity2_Project {
	WebDriver driver;
  
  @BeforeTest
  public void beforeTest() {
	  //initiate the Driver instance
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test
  public void testCase() {
	  // find the element by TagName
	  WebElement headerImage = driver.findElement(By.tagName("img"));
	  // get the URL of the link
	  String url = headerImage.getAttribute("src");
	  // write the URL in console
	  System.out.println("Url of the Image:"+url);
	  Reporter.log("URL of the page has collected sucessfully.");
  }

  @AfterTest
  public void afterTest() {
	  // close the session
	  driver.quit();
  }

}
