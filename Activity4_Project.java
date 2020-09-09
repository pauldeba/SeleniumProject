/*** Activity_Project
 *  Add a new employee Goal: Add an employee and their details to the site 
 *  a. Open the OrangeHRM page and login with credentials provided 
 *  b. Find the PIM option in the menu and click it. 
 *  c. Click the Add button to add a new Employee. 
 *  d. Fill in the required fields and click Save. 
 *  e. Navigate back to the Admin page and verify the creation of your employee. 
 *  f. Close the browser.
 */


package TestNGProject;

import org.testng.annotations.Test;

import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class Activity4_Project {
	// Initiate the WebDriver
  WebDriver driver;
  
  @DataProvider(name = "Authentication")
  public static Object[][] credentials() {
      return new Object[][] { { "orange", "orangepassword123" }};
  }
    
  @BeforeTest
  public void beforeTest() {
	  // Instantiate a new Firefox Driver
	  driver = new FirefoxDriver();
	  // open the URL
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test (dataProvider = "Authentication",priority=1)
  public void loginPage(String userid, String password) {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //Enter Userid, Password and Click Login button.
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(userid);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test (priority=2)	// Second test case which will run after the login session
  public void addEmployee() throws InterruptedException {
	  Thread.sleep(3000);
	  // Click PIM tab and add button
	  driver.findElement(By.xpath("//b[text()='PIM']")).click();
	  driver.findElement(By.xpath("//input[@name='btnAdd']")).click();
	  Thread.sleep(3000);
	  // enter firstname, lastname and click add button.
	  driver.findElement(By.id("firstName")).sendKeys("Astin");
	  driver.findElement(By.id("lastName")).sendKeys("Bibber");
	  driver.findElement(By.xpath("//p/input[@type='button']")).click();
	  Thread.sleep(2000);
	  // get EmpId while creating
	  String empId=driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value");
	  System.out.println("Emp ID: "+empId);
	  
	  // navigate to Admin page and followed to PIM page
	  driver.findElement(By.xpath("//b[text()='Admin']")).click();
	  driver.findElement(By.xpath("//b[text()='PIM']")).click();
	  Thread.sleep(2000);
	  // search the EmpId that is newly created
	  //driver.findElement(By.xpath("//input[@name='empsearch[id]']")).sendKeys(empId);
	  driver.findElement(By.cssSelector("input#empsearch_id")).clear();
	  driver.findElement(By.cssSelector("input#empsearch_id")).sendKeys(empId);
	  
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  driver.findElement(By.id("searchBtn")).click();
	  String id = driver.findElement(By.linkText(empId)).getText();
	  System.out.println("Emp Id after Creation:"+id);
	  // Match the id after creation of new employee
	  Assert.assertEquals(id, empId);
	  Reporter.log("Emplyee Id "+id+" found on Admin Page:");
  }
  
   
  
  @AfterTest
  public void afterTest() {
	  // close the browser
	  driver.quit();
  }

}
