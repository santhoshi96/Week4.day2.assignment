package week4.Day2.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Createnewincident {

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
		//caller
		driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys("System");
		String oldWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		Set<String> findCaller = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(findCaller);
		driver.switchTo().window(lstWindowHandles.get(1));
		String text = driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).getText();
		System.out.println(text);
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		//short description
		driver.switchTo().window(oldWindow);
		Thread.sleep(2000);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Hiiiiiiiiiiiiii");
		//submit
		driver.findElement(By.id("sysverb_insert")).click();
		//enter created incident number and search
		WebElement incidentNumber2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		incidentNumber2.click();
		incidentNumber2.sendKeys(incidentNumber);
		incidentNumber2.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		//verify incident is created successfully
		String createdNumber = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(createdNumber .equals( incidentNumber)) {
			System.out.println("New incident created successfully");
			
		}else
		{
			System.out.println("New incident is not created successfully");
		}
		
	}

}
