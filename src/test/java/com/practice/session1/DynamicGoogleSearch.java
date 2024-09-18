package com.practice.session1;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicGoogleSearch {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//textarea[@name= 'q']")).sendKeys("testing");
		
		List<WebElement> listSuggestions = driver.findElements(By.xpath("//ul[@role = 'listbox']/descendant::li"));
		
		for (WebElement suggestion : listSuggestions) {
			System.out.println(suggestion.getText());
			
			if(suggestion.getText().equalsIgnoreCase("testing course")) {
				suggestion.click();
				break;
			}
		}
		
		driver.quit();
	}
}
