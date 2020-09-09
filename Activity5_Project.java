/***
 *  Edit user information Goal: Edit a user’s information 
 *  a. Open the OrangeHRM page and login with credentials provided 
 *  b. Find the “My Info” menu item and click it. 
 *  c. On the new page, click the Edit button. 
 *  d. Fill in the Name, Gender, Nationality, and the DOB fields. 
 *  e. Click Save. 
 *  f. Close the browser.
 */
package TestNGProject;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity5_Project {
	// Initiate WebDriver
	WebDriver driver;
	
  
  @DataProvider (name="Authentication")
  public static Object[][] LoginCredentials() {
    return new Object[][] { { "orange", "orangepassword123" }};
  }
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test(dataProvider = "Authentication",priority=1)
  public void login(String id, String password) {
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(id);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test(priority=2)
  public void updatePersonalDetails() throws InterruptedException {
	  driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	  Thread.sleep(3000);
	  //Find the “My Info” menu item and click it
	  driver.findElement(By.linkText("My Info")).click();
	  // On the new page, click the Edit button. 
	  driver.findElement(By.cssSelector("input#btnSave")).click();
	  
	  //Fill in the Name, Gender, Nationality, and the DOB fields.
	  WebElement firstName = driver.findElement(By.id("personal_txtEmpFirstName"));
	  WebElement lastName = driver.findElement(By.id("personal_txtEmpLastName"));
	  firstName.clear();
	  lastName.clear();
	  firstName.sendKeys("Chandra");
	  lastName.sendKeys("R");
	  driver.findElement(By.id("personal_optGender_1")).click();
	  Reporter.log("Gender is Selected");
	  WebElement nationality = driver.findElement(By.id("personal_cmbNation"));
	  Select dropDown = new Select(nationality);
	  dropDown.selectByIndex(82);;
	  Reporter.log("Nationality is Selected");
	  WebElement dob = driver.findElement(By.id("personal_DOB"));
	  dob.clear();
	  dob.sendKeys("1990-01-01");
	  Thread.sleep(1000);
	  // Click Save
	  driver.findElement(By.id("btnSave")).click();
	  Reporter.log("Save and Close the browser");
  }
  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
