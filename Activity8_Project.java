/*  Applying for a leave Goal: Login and apply for a leave on the HRM site 
 *  a. Open the OrangeHRM page and login with credentials provided 
 *  b. Navigate to the Dashboard page and click on the Apply Leave option. 
 *  c. Select leave type and duration of the leave. 
 *  d. Click Apply. 
 *  e. Navigate to the My Leave page to check the status of leave application. 
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class Activity8_Project {
	WebDriver driver;
	  
  @DataProvider(name="Authentication")
  public Object[][] credential() {
    return new Object[][] {{ "orange", "orangepassword123" }};
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.get("http://alchemy.hguy.co/orangehrm");
  }
  
  @Test(dataProvider = "Authentication", priority=1)
  public void login(String userid, String password) {
	  
	  driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(userid);
	  driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
  }
  
  @Test (priority=2)
  public void applyLeave() throws InterruptedException {
	  Thread.sleep(3000);
	  driver.findElement(By.linkText("Dashboard")).click();
	  //driver.findElement(By.cssSelector("a#menu_dashboard_index")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Apply Leave")).click();
	  //driver.findElement(By.xpath("//span[text()='Apply Leave']")).click();
	  //driver.findElement(By.xpath("//body/div[1]/div[3]/div/div[2]/div/div/div/div/fieldset/div/div/table/tbody/tr/td[4]/div")).click();
	  
	  //Select leave type and duration of the leave. 
	  Thread.sleep(10000);
	  WebElement list = driver.findElement(By.id("applyleave_txtLeaveType"));
	  Select dropDown = new Select(list);
	  //dropDown.selectByValue("Paid Leave");
	  dropDown.selectByIndex(1);
	  WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
	  WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
	  fromDate.clear();
	  fromDate.sendKeys("2020-11-17");
	  toDate.clear();
	  toDate.sendKeys("2020-11-18");
	  driver.findElement(By.id("applyleave_txtComment")).sendKeys("Testing");
	  
	  // Click Apply
	  driver.findElement(By.id("applyBtn")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id("menu_leave_viewMyLeaveList")).click();
	  //driver.findElement(By.xpath("//tbody/tr/td/a")).click();
	  Thread.sleep(3000);
	  String status = driver.findElement(By.xpath("//tbody/tr/td[6]/a")).getText();
	  System.out.println("Leave Status: "+status);
	  Reporter.log("Leave Status: "+status,true);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
