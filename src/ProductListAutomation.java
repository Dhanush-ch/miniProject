import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListAutomation {

	static WebDriver driver;
	
	

	/* Driver Setup */

	public void createDriver(String browser) {
		if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}

		driver.get("https://www.pepperfry.com");
		
		
	}
	
	
	

	/* Verify Title */

	public void verifyTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Online Furniture Shopping Store: Shop Online in India for Furniture, Home Decor, Homeware Products @ Pepperfry";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Titles matched");
		} else {
			System.out.println("Titles does not match");
		}
	}
	
	
	

	/* Navigating to Furniture -> Benches */

	public void navigate() {

		WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		exp_wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"(//a[@class='level-top'])[1]"))));
		WebElement furniture = driver.findElement(By.xpath("(//a[@class='level-top'])[1]"));
		
		Actions action = new Actions(driver);
		action.moveToElement(furniture).perform();
		
		exp_wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"//a[@href='https://www.pepperfry.com/furniture-benches.html?type=hover-furniture']"))));
		WebElement benches = driver.findElement(By.xpath("//a[@href='https://www.pepperfry.com/furniture-benches.html?type=hover-furniture']"));
		benches.click();
	
	}
	
	

	/* Benches and its Count */

	public void benchCount() {		
		
		// Category List
		List<WebElement> listt = driver
				.findElements(By.xpath("//div[@class = 'cat-wrap-ttl' ]"));
		
		int i = 1;
		for(WebElement ele: listt) {
			System.out.println(i + ". " + ele.getText());
			
			if(ele.getText().contains("Industrial")) {
				
				String[] tempArr = ele.getText().split(" ");
				
				int industrialbenchCount = Integer.parseInt(tempArr[1].replaceAll("[^0-9]", ""));
				if (industrialbenchCount > 1) {
					System.out.println("Contains more than 1 Industrial benches");
				}
			}
			i++;
		}
	}

	public void driverClose() {

		driver.close();
	}

	
	
	public static void main(String args[]) {

		ProductListAutomation obj = new ProductListAutomation();

		Scanner sc = new Scanner(System.in);
		System.out.println("1. Chrome\n2. Firefox");

		int browser_no = sc.nextInt();
		String browser = null;

		if (browser_no == 1) {
			System.out.println("Selected Chrome");
			browser = "Chrome";
		} else if (browser_no == 2) {
			System.out.println("Selected Firefox");
			browser = "Firefox";
		}

		
		obj.createDriver(browser);
		obj.verifyTitle();
		obj.navigate();
		obj.benchCount();
		obj.driverClose();

	}
}
