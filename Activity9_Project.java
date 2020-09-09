/*
 *  Retrieve emergency contacts Goal: Login and retrieve the emergency contacts for the user 
 *  a. Open the OrangeHRM page and login with credentials provided. 
 *  b. Navigate to the “My Info” page. 
 *  c. Locate the left hand menu. 
 *  d. Click on the “Emergency Contacts” menu item. 
 *  e. Retrieve information about all the contacts listed in the table. 
 *  f. Print all the information to the console. 
 *  g. Close the browser.
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity9_Project {
//instantiate the WebDriver	
  WebDriver driver;
  WebDriverWait wait;
  
  @DataProvider(name="Authentication")
  public Object[][] dp() {
    return new Object[][] {{ "orange", "orangepassword123" }};
  }
  @BeforeTest(alwaysRun=true)
  public void beforeTest() {
	  // initiate the Firefox browser
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test(dataProvider = "Authentication",priority=1)
  public void f(String userid, String password) {
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(userid);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
	  // explicit wait for 10 second
	  wait = new WebDriverWait(driver,10);
  }
  
  @Test(priority=2)
  public void emergencyContractRetrive() throws InterruptedException {
	  Thread.sleep(5000);
	  // Navigate to the “My Info” page. 
	  driver.findElement(By.id("menu_pim_viewMyDetails")).click();
	  // Locate the left hand menu.Click on the “Emergency Contacts” menu item. 
	  Thread.sleep(3000);
	  driver.findElement(By.linkText("Emergency Contacts")).click();
	  // Retrieve information about all the contacts listed in the table
	  List<WebElement> column = driver.findElements(By.xpath("//tbody/tr[1]/td"));
	  List<WebElement> row = driver.findElements(By.xpath("//tbody/tr"));
	  System.out.println("Total Row in the table: "+column.size()+ " and Total Column in the table: "+row.size());
	  for(int i=1;i<row.size()+1;i++) {
		  String name = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText();
		  Reporter.log("Name: "+name,true);
		  
		  String rel = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).getText();
		  Reporter.log("Relation: "+rel,true);
		  
		  String mobile = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]")).getText();
		  Reporter.log("Mobile Number: "+mobile,true);
		  // print in a single line
		  Reporter.log(i+": Emergency Contact Name: "+name+" | Relationship: "+rel+" | Mobile Number: "+mobile,true);
		  
	  }
	  
	 
  }

  @AfterTest
  public void afterTest() {
	  // close the browser
	  driver.quit();
  }

}
