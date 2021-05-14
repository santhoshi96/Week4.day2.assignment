package week4.Day2.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderingMobile {

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
		WebElement filterNavigator = driver.findElement(By.id("filter"));
		filterNavigator.sendKeys("Service Catalog");
		filterNavigator.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//h2[text()[normalize-space()='Mobiles']]")).click();
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 6s']")).click();

		WebElement monthlyAllowances = driver.findElement(By.xpath("//div[contains(@class,'col-xs-6 ')]//select"));
		new Select(monthlyAllowances).selectByIndex(2); // Unlimited

		WebElement mobileColor = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6 ')]//select)[2]"));
		new Select(mobileColor).selectByIndex(3); // Rose Gold

		WebElement mobileStorage = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6 ')]//select)[3]"));
		new Select(mobileStorage).selectByIndex(2); // 128 GB

		driver.findElement(By.id("oi_order_now_button")).click();
		String mobileBrand = driver.findElement(By.cssSelector("div#sc_cart_view>table>tbody>tr>td>div>a")).getText();

		String orderStatusMsg = driver.findElement(By.cssSelector("div#sc_order_status_intro_text>div>span")).getText();

		String orderRequestNumber = driver.findElement(By.cssSelector("a#requesturl>b")).getText();

		String orderPlacedRequest = driver.findElement(By.cssSelector("div#sc_order_status_intro_text>dl")).getText();
		if (mobileBrand.equalsIgnoreCase("Apple iPhone 6s")) {
			System.out.println(mobileBrand + " ordered successfully " + orderRequestNumber);
			System.out.println(orderStatusMsg);
			System.out.print(orderPlacedRequest);
			driver.quit();

		} else {
			System.out.println("Unable to order " + mobileBrand);
			
		}
	}

}
