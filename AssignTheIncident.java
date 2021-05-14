package week4.Day2.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignTheIncident {

	public static void main(String[] args) throws InterruptedException {
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
		//open
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Open']")).click();
		driver.switchTo().frame("gsft_main");
		//click on existing incident number
		Thread.sleep(2000);
		WebElement firstNumber = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		firstNumber.click();
		firstNumber.sendKeys("INC0010796");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("updated Incident Number is:"+incidentNumber);
		driver.findElement(By.id("sys_display.incident.assignment_group")).click();
		String oldWindow = driver.getWindowHandle();
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> firstGroup = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(firstGroup);
		driver.switchTo().window(lstWindowHandles.get(1));
		driver.findElement(By.xpath("//input[@class='form-control']")).click();
		WebElement Search1 = driver.findElement(By.xpath("//input[@class='form-control']"));
		Search1.sendKeys("software");
		Search1.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Software")).click();
		driver.switchTo().window(oldWindow);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sys_display.incident.assigned_to")).click();
		driver.findElement(By.xpath("//button[@data-for='sys_display.incident.assigned_to']//span[1]")).click();
		Set<String> secondGroup = driver.getWindowHandles();
		List<String> lstWindowHandles1 = new ArrayList<String>(secondGroup);
		driver.switchTo().window(lstWindowHandles1.get(1));
		Thread.sleep(2000);
		WebElement assignmentTo = driver.findElement(By.xpath("(//label[text()='Search'])[2]/following::input"));
		assignmentTo.sendKeys("ITIL");
		assignmentTo.sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("ITIL User")).click();
		Thread.sleep(2000);
		driver.switchTo().window(oldWindow);
		WebElement workNotes = driver.findElement(By.id("activity-stream-textarea"));
		workNotes.click();
		workNotes.sendKeys("updated");
		driver.findElement(By.id("sysverb_update_bottom")).click();
		WebElement incidentNumber2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		incidentNumber2.click();
		incidentNumber2.sendKeys(incidentNumber);
		incidentNumber2.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String software1 = driver.findElement(By.cssSelector("table#incident_table>tbody>tr>td:nth-of-type(10)>a")).getText();
		String iTILUser = driver.findElement(By.cssSelector("table#incident_table>tbody>tr>td:nth-of-type(11)")).getText();
		if(software1.equals("software")) {
			System.out.println("software is updated successfully");
		}else
		{
			System.out.println("software is not updated sucessfully");
		}
		if(iTILUser.equals("iTILUser")) {
			System.out.println("iTILUser is updated successfully");
		}else
		{
			System.out.println("iTILUser is not updated successfully");
		}
		
		
		
		
	}

}
