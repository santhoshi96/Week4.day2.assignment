package week4.Day2.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingIncident {

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
				//open
				driver.findElement(By.xpath("//div[text()='Open']")).click();
				driver.switchTo().frame("gsft_main");
				//click on existing incident number
				Thread.sleep(2000);
				WebElement firstNumber = driver.findElement(By.xpath("//input[@placeholder='Search']"));
				firstNumber.click();
				firstNumber.sendKeys("INC0010671");
				driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
				String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
				System.out.println("updated Incident Number is:"+incidentNumber);
				WebElement urgencyDd = driver.findElement(By.id("incident.urgency"));
				Select dd = new Select(urgencyDd);
				dd.selectByVisibleText("1 - High");
				System.out.println("selected and updated urgency value is High");
				Thread.sleep(2000);
				Select stateDd = new Select(driver.findElement(By.id("incident.state")));
				stateDd .selectByIndex(1);
				System.out.println("selected and updated State value is In Progress");
				driver.findElement(By.id("activity-stream-work_notes-textarea")).sendKeys("updated");
				driver.findElement(By.id("sysverb_update_bottom")).click();
				WebElement incidentNumber2 = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
				incidentNumber2.click();
				incidentNumber2.sendKeys(incidentNumber);
				incidentNumber2.sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				String Priority = driver.findElement(By.cssSelector("table#incident_table>tbody>tr>td:nth-of-type(7)")).getText();
				if(Priority.equals("1 - Critical")) {
					System.out.println("Priority 1 - Critical updated sucessfully");
				}else
				{
					System.out.println("Priority is not updated sucessfully");
				}
				String State = driver.findElement(By.xpath("//td[text()='In Progress']")).getText();
				if(State.equals("In Progress")) {
					System.out.println("In Progress updated sucessfully");
				}else
				{
					System.out.println("In Progress is not updated sucessfully");
				}
	}

}
