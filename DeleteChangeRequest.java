package week4.Day2.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteChangeRequest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev103117.service-now.com");
		driver.manage().window().maximize();
		//username
		driver.switchTo().frame(0);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		//Paswword
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("India@123");
		//Login
		driver.findElement(By.id("sysverb_login")).click();
		//in filter click change and Tab
		Thread.sleep(2000);
		WebElement Filter = driver.findElement(By.id("filter"));
		Filter.sendKeys("Change");
		Filter.sendKeys(Keys.ENTER);
		//Click Create New
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[text()='Open'])[3]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		WebElement Search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		Search.click();
		Search.sendKeys("CHG0030657");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		String changeNumber = driver.findElement(By.id("change_request.number")).getAttribute("value");
		System.out.println("Change Number is:"+changeNumber);
		driver.findElement(By.id("sysverb_delete_bottom")).click();
		driver.findElement(By.id("ok_button")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//div[text()='Open'])[3]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		WebElement changeNumber2 = driver.findElement(By.xpath("//input[@class='form-control']"));
		changeNumber2.click();
		changeNumber2.sendKeys(changeNumber);
		changeNumber2.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String noRecordsTo = driver.findElement(By.xpath("//td[text()='No records to display']")).getText();
		if(noRecordsTo.equals("No records to display")) {
			System.out.println("Deleted sucessfully");
			driver.close();
		}else
		{
			System.out.println("not Deleted");
		}
		

	}

}
