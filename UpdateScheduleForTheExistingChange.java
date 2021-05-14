package week4.Day2.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateScheduleForTheExistingChange {

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
		Search.sendKeys("CHG0030660");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		String changeNumber = driver.findElement(By.id("change_request.number")).getAttribute("value");
		System.out.println("Change Number is:"+changeNumber);
		driver.findElement(By.xpath("(//span[@class='tab_caption_text'])[2]")).click();
		driver.findElement(By.xpath("(//button[@id='change_request.start_date.ui_policy_sensitive']//span)[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='calOtherMonthDateAnchor'])[2]")).click();
		WebElement dateTimePickerFrom = driver.findElement(By.id("GwtDateTimePicker_ok"));
		dateTimePickerFrom.click();
		String changerequeststartdate = driver.findElement(By.id("change_request.start_date")).getAttribute("value");
		System.out.println(changerequeststartdate);
		driver.findElement(By.xpath("(//button[@id='change_request.end_date.ui_policy_sensitive'])[2]")).click();
		WebElement dateTimePickerTo = driver.findElement(By.id("GwtDateTimePicker_ok"));
		dateTimePickerTo.click();
		String changerequestenddate = driver.findElement(By.id("change_request.end_date")).getAttribute("value");
		System.out.println(changerequestenddate);
		driver.findElement(By.id("label.ni.change_request.cab_required")).click();
		driver.findElement(By.xpath("//a[@data-date_time_format='yyyy-MM-dd']//span[1]")).click();
		driver.findElement(By.cssSelector("a#GwtDateTimePicker_day8")).click();
		driver.findElement(By.id("sysverb_update_bottom")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//div[text()='Open'])[3]")).click();
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		WebElement changeNumber2 = driver.findElement(By.xpath("//input[@class='form-control']"));
		changeNumber2.click();
		changeNumber2.sendKeys(changeNumber);
		changeNumber2.sendKeys(Keys.ENTER);
		String StartDate = driver.findElement(By.cssSelector("table#change_request_table>tbody>tr>td:nth-of-type(7)")).getText();
		String EndDate = driver.findElement(By.cssSelector("table#change_request_table>tbody>tr>td:nth-of-type(8)")).getText();
		if(StartDate .equals(changerequeststartdate)) {
			System.out.println("Startdate is updated successfully");
		}else {
			System.out.println("Startdate is not updated successfully");
		}
		if( EndDate.equals(changerequestenddate)) {
			System.out.println("Enddate is updated successfully");
		}else {
			System.out.println("Enddate is not updated successfully");
		}
	}

}
