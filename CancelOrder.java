package week4.Day2.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CancelOrder {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://dev103117.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().frame(0);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		String orderNumber = "REQ0010001";

		WebElement filterNavigator = driver.findElement(By.id("filter"));
		filterNavigator.sendKeys("Service Catalog");
		Thread.sleep(1000);
		filterNavigator.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//div[text()='Requests']")).click();

		System.out.println(orderNumber);

driver.switchTo().frame(0);
		
		WebElement forTextNum = driver
				.findElement(By.xpath("//span[@class='input-group-addon input-group-select']//select[1]"));
		new Select(forTextNum).selectByIndex(1);

		WebElement searchRequest = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchRequest.click();
		searchRequest.sendKeys(orderNumber);
		searchRequest.sendKeys(Keys.ENTER);

		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();


		WebElement requestApproval = driver.findElement(By.id("sc_request.approval"));
		new Select(requestApproval).selectByIndex(3); 
		WebElement requestState = driver.findElement(By.id("sc_request.request_state"));
		new Select(requestState).selectByIndex(5); 


		driver.findElement(By.xpath("(//button[text()='Cancel Request'])[2]")).click();
		
		String expectedResult = "No records to display";
		String verifyCancelStatus = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();

		if (verifyCancelStatus.equalsIgnoreCase(expectedResult)) {
			System.out.println("Order has been cancelled successfully");

		} else {
			System.out.println("order has not cancelled");
		}
		}
}

	

