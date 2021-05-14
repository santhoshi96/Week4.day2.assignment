package week4.Day2.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateProposal {

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


		WebElement filterNav = driver.findElement(By.id("filter"));
		filterNav.sendKeys("Proposal");
		filterNav.sendKeys(Keys.ENTER);

		String proposalNumber = "STDCHG0001051";

		driver.findElement(By.xpath("//div[text()='My Proposals']")).click();

		driver.switchTo().frame(0);
		WebElement eleNumber = driver.findElement(By.cssSelector("span#std_change_proposal_hide_search>div>div>input"));
		eleNumber.click();
		eleNumber.sendKeys(proposalNumber);
		eleNumber.sendKeys(Keys.ENTER);

		driver.findElement(By.cssSelector("table#std_change_proposal_table>tbody>tr>td:nth-of-type(3)>a")).click();

		Select proposalState = new Select(driver.findElement(By.id("std_change_proposal.state")));
		proposalState.selectByIndex(1);
		System.out.println("Updated proposal state value as In-progress");
		System.out.println(" ");

		driver.findElement(By.xpath("//button[@id='lookup.std_change_proposal.assigned_to']//span[1]")).click(); // Lookup-using

		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("WindowHandling: " + allWindowHandles.size());
		List<String> lstWindowHandles = new ArrayList<String>(allWindowHandles);
		
		System.out.println("Window Handling: " + allWindowHandles.size());
		driver.switchTo().window(lstWindowHandles.get(1));
		System.out.println("Sub-Window Title: " + driver.getTitle());
		System.out.println("Sub-Window URL: " + driver.getCurrentUrl());
		

		driver.findElement(By.linkText("Change Manager")).click();

		Thread.sleep(1000);

		driver.switchTo().window(lstWindowHandles.get(0));

		driver.switchTo().frame(0);


		driver.findElement(By.id("lookup.std_change_proposal.category")).click(); // Lookup-using

		
		allWindowHandles = driver.getWindowHandles();
		System.out.println("WindowHandling: " + allWindowHandles.size());
		lstWindowHandles = new ArrayList<String>(allWindowHandles);
		
		
		System.out.println("Window Handling: " + allWindowHandles.size());
		driver.switchTo().window(lstWindowHandles.get(1));
		System.out.println("Sub-Window Title: " + driver.getTitle());
		System.out.println("Sub-Window URL: " + driver.getCurrentUrl());
		

		driver.findElement(By.linkText("Template Management")).click();

		driver.switchTo().window(lstWindowHandles.get(0));

		driver.switchTo().frame(0);

		driver.findElement(By.xpath("//span[text()='Change Request values']")).click();

		driver.findElement(By.xpath("//td[@data-value='Assignment group']//button[1]")).click();

		
		allWindowHandles = driver.getWindowHandles();
		System.out.println("WindowHandling: " + allWindowHandles.size());
		lstWindowHandles = new ArrayList<String>(allWindowHandles);
		
		System.out.println("Window Handling: " + allWindowHandles.size());
		driver.switchTo().window(lstWindowHandles.get(1));
		System.out.println("Sub-Window Title: " + driver.getTitle());
		System.out.println("Sub-Window URL: " + driver.getCurrentUrl());
		
		WebElement assignGroup = driver.findElement(By.xpath("(//label[text()='Search'])[2]/following::input"));
		assignGroup.sendKeys("Change Management");
		assignGroup.sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("Change Management")).click();

		Thread.sleep(1000);

		driver.switchTo().window(lstWindowHandles.get(0));

		driver.switchTo().frame(0);

		
		WebElement justification = driver.findElement(By.xpath("//td[@data-value='Justification']//textarea[1]"));
		justification.click();
		justification.clear();
		justification.sendKeys("N/A");

		WebElement riskImpact = driver
				.findElement(By.xpath("//td[@data-value='Risk and impact analysis']//textarea[1]"));
		riskImpact.click();
		riskImpact.clear();
		riskImpact.sendKeys("N/A");

		driver.findElement(By.cssSelector("button#sysverb_update_bottom")).click();
		Thread.sleep(1000);

		
		driver.findElement(By.xpath("//i[@data-list_id='std_change_proposal']")).click();
		Thread.sleep(1000);

		
		driver.findElement(By.xpath("//option[text()='Assigned to']")).click();
		driver.findElement(By.xpath("//div[@id='addRemoveButtons']//a[1]")).click();
		driver.findElement(By.cssSelector("button#ok_button")).click();

		String verifychangeNo = driver
				.findElement(By.cssSelector("table#std_change_proposal_table>tbody>tr>td:nth-of-type(3)")).getText();

		String verifyAssignedT0 = driver.findElement(By.linkText("Change Manager")).getText();
		

		String verifyStateValue = driver
				.findElement(By.cssSelector("table#std_change_proposal_table>tbody>tr>td:nth-of-type(6)")).getText();

		if ((verifychangeNo.equalsIgnoreCase(proposalNumber)) && (verifyAssignedT0.equalsIgnoreCase("Change Manager"))
				&& verifyStateValue.equalsIgnoreCase("In Progress")) {
			System.out.println("Updated Proposal Request");
			

		} else {
			System.err.println(" Proposal not updated");
		}

	}

}


