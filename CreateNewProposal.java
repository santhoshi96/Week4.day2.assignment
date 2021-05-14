package week4.Day2.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewProposal {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://dev103117.service-now.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.switchTo().frame(0);

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();


		WebElement filterNav = driver.findElement(By.id("filter"));
		filterNav.sendKeys("Proposal");
		filterNav.sendKeys(Keys.ENTER);


		driver.switchTo().frame(0);

		driver.findElement(By.cssSelector("button#sysverb_new")).click();

		String proposalNumber = driver.findElement(By.id("std_change_proposal.number")).getAttribute("value");
		System.out.println(proposalNumber);

		driver.findElement(By.id("std_change_proposal.short_description")).sendKeys("Test - New proposal creation");

		driver.findElement(By.xpath("//span[text()='Change Request values']")).click();

		WebElement shortDesc = driver.findElement(By.xpath("//td[@data-value='Short description']//input[1]"));
		shortDesc.click();
		shortDesc.clear();
		shortDesc.sendKeys("Test - New proposal creation");

		
		driver.findElement(By.cssSelector("button#sysverb_insert_bottom")).click();

		WebElement searchFilterOption = driver
				.findElement(By.cssSelector("span#std_change_proposal_hide_search>div>div>span>span>select"));
		new Select(searchFilterOption).selectByIndex(1);

		WebElement eleNumber = driver.findElement(By.cssSelector("span#std_change_proposal_hide_search>div>div>input"));
		eleNumber.click();
		eleNumber.sendKeys(proposalNumber);
		eleNumber.sendKeys(Keys.ENTER);

		String resultantNumber = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		
		if (resultantNumber.equalsIgnoreCase(proposalNumber)) {
			System.out.println("Proposal number created");
			driver.quit();

		} else {
			System.err.println("Proposal number is not created");
		}

	}

}