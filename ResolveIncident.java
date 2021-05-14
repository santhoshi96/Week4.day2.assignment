package week4.Day2.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ResolveIncident {

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
		firstNumber.sendKeys("INC0010815");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("updated Incident Number is:"+incidentNumber);
		WebElement StateDd = driver.findElement(By.id("incident.state"));
		Select dd = new Select(StateDd);
		dd.selectByVisibleText("Resolved");
		driver.findElement(By.xpath("//span[text()='Resolution Information']")).click();
		WebElement incidentclosecode = driver.findElement(By.id("incident.close_code")); 
		new Select(incidentclosecode).selectByVisibleText("Solved (Work Around)");
		driver.findElement(By.id("incident.close_notes")).sendKeys("Testing has been completed");
		Thread.sleep(2000);
		driver.findElement(By.id("sysverb_update_bottom")).click();
		Thread.sleep(2000);
		WebElement incidentNumber2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		incidentNumber2.click();
		incidentNumber2.sendKeys(incidentNumber);
		incidentNumber2.sendKeys(Keys.ENTER);
		String resolved = driver.findElement(By.xpath("//td[text()='Resolved']")).getText();
		if(resolved.equals("Resolved")){
			System.out.println("Incident is resolved sucessfully");
			}else {
				System.out.println("Incident is not resolved successfully");
			}
	}

}
