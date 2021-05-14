package week4.Day2.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewIncidentWithoutFillingMandatoryField {

	public static void main(String[] args) throws InterruptedException {
		//Launch ServiceNow application
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver = new ChromeDriver();
				driver.get("https://dev103117.service-now.com");
				driver.manage().window().maximize();
				//username
				driver.switchTo().frame("gsft_main");
				driver.findElement(By.id("user_name")).sendKeys("admin");
				//Paswword
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("India@123");
				//Login
				driver.findElement(By.id("sysverb_login")).click();
				//in filter click incident and Tab
				Thread.sleep(2000);
				WebElement Filter = driver.findElement(By.id("filter"));
				Filter.sendKeys("Incident");
				Filter.sendKeys(Keys.ENTER);
				//Click Create New
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[text()='Create New']")).click();
				//print incident number
				driver.switchTo().frame("gsft_main");
				String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
				System.out.println("Incident Number is:"+incidentNumber);
				driver.findElement(By.id("sysverb_insert")).click();
				String theFollowingMandatory = driver.findElement(By.className("outputmsg_text")).getText();
				if(theFollowingMandatory.equals("The following mandatory fields are not filled in: Short description, Caller")) {
					System.out.println("The following mandatory fields are not filled in: Short description, Caller error message should be displayed");
				}else
				{
					System.out.println("Error message is not displaying");
				}

	}

}
