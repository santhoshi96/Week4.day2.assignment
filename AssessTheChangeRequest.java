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

public class AssessTheChangeRequest {

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
		Search.sendKeys("CHG0030651");
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		//print Change number
		String changeNumber = driver.findElement(By.id("change_request.number")).getAttribute("value");
		System.out.println("Change Number is:"+changeNumber);
		WebElement changerequeststate = driver.findElement(By.id("change_request.state")); 
		new Select(changerequeststate).selectByVisibleText("Assess"); 
		String oldWindow = driver.getWindowHandle();
		driver.findElement(By.id("sys_display.change_request.assignment_group")).click();
		driver.findElement(By.id("lookup.change_request.assignment_group")).click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("WindowHandling: " + allWindowHandles.size());
		List<String> lstWindowHandles = new ArrayList<String>(allWindowHandles);
		driver.switchTo().window(lstWindowHandles.get(1));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='treenode_0']/div[1]/div[1]/div[29]/table[1]/tbody[1]/tr[1]/td[1]/img[1]")).click();
		WebElement assignGroup = driver.findElement(By.cssSelector("div#treenode_0>div>div>div:nth-of-type(29)>div>div:nth-of-type(5)>table>tbody>tr>td:nth-of-type(3)>a"));
		assignGroup.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='lookup.change_request.assigned_to']//span[1]")).click();
		allWindowHandles = driver.getWindowHandles();
		lstWindowHandles = new ArrayList<String>(allWindowHandles);
		driver.switchTo().window(lstWindowHandles.get(1));
		WebElement assignmentTo = driver.findElement(By.xpath("(//label[text()='Search'])[2]/following::input"));
		assignmentTo.sendKeys("ITIL");
		assignmentTo.sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("ITIL User")).click();
		Thread.sleep(1000);
		driver.switchTo().window(lstWindowHandles.get(0));
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("button#sysverb_update_bottom")).click();
        String AssignedT0 = driver.findElement(By.cssSelector("table#change_request_table>tbody>tr>td:nth-of-type(10)")).getText();

		String StateValue = driver.findElement(By.cssSelector("table#change_request_table>tbody>tr>td:nth-of-type(6)")).getText();
		if(AssignedT0.equals("AssignedT0")) {
			System.out.println("iTILUser is updated successfully");
		}else
		{
			System.out.println("iTILUser is not updated successfully");
		}
		if(StateValue.equals("Asses")) {
			System.out.println(" StateValue is updated successfully");
		}else
		{
			System.out.println("StateValue is not updated successfully");
		}
	}

}
