package week4.Day2.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingOrder {

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
		filterNavigator.sendKeys("My Requests");
		Thread.sleep(1000);
		filterNavigator.sendKeys(Keys.ENTER);
		driver.switchTo().frame(0);
		driver.findElement(By.cssSelector("div#list_nav_task>div>div>h1>a")).click();
		driver.findElement(By.xpath("//div[text()='View']")).click();
		driver.findElement(By.xpath("//div[text()='Default view']")).click();
		Thread.sleep(1000);
		WebElement forTextNum = driver.findElement(By.cssSelector("span#task_hide_search>div>div>span>span>select"));
		new Select(forTextNum).selectByIndex(1);
        WebElement searchRequest = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchRequest.click();
		searchRequest.sendKeys(orderNumber);
		searchRequest.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
		driver.findElement(By.cssSelector("table#task_table>tbody>tr>td:nth-of-type(3)>a")).click();

		WebElement requestApproval = driver.findElement(By.id("sc_request.approval"));
		new Select(requestApproval).selectByIndex(1);

		WebElement requestState = driver.findElement(By.id("sc_request.request_state"));
		new Select(requestState).selectByIndex(2);


		WebElement shortDesc = driver.findElement(By.id("sc_request.short_description"));
		shortDesc.click();
		shortDesc.clear();
		shortDesc.sendKeys("Updating Approval");

        driver.findElement(By.cssSelector("button#sysverb_update_bottom")).click();
		
		String resultDescription = driver.findElement(By.cssSelector("table#task_table>tbody>tr>td:nth-of-type(7)"))
				.getText();

		if (resultDescription != null) {
			System.out.println("Order should be updated successfully");
			
		} else {
			System.err.println("Order should not be updated successfully");
		}



	}

}
