package week4.Day2.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
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
		firstNumber.sendKeys("INC0010811");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("updated Incident Number is:"+incidentNumber);
		//String oldWindow = driver.getWindowHandle();
		driver.findElement(By.id("sysverb_delete_bottom")).click();
		Set<String> delete = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(delete);
		driver.switchTo().window(lstWindowHandles.get(1));
		driver.switchTo().frame("form-frame");
        driver.findElement(By.id("ok_button")).click();
		WebElement incidentNumber2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		incidentNumber2.click();
		incidentNumber2.sendKeys(incidentNumber);
		incidentNumber2.sendKeys(Keys.ENTER);
		String noRecordsTo = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		if(noRecordsTo.equals("No records to display")) {
			System.out.println("Incident Deleted sucessfully");
			driver.close();
		}else
		{
			System.out.println("Incident is not Deleted");
		}
	}

}
