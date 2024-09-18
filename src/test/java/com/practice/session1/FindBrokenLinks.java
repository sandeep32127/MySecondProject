package com.practice.session1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindBrokenLinks {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		Thread.sleep(5000);
		
		/*driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id = \"webklipper-publisher-widget-container-notification-frame\"]")));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id = \"webklipper-publisher-widget-container-notification-close-div\"]")));
		driver.findElement(By.xpath("//a[@id = \"webklipper-publisher-widget-container-notification-close-div\"]")).click();
		driver.switchTo().defaultContent();*/
		
		List<String> links = new ArrayList<String>();
		
		List<WebElement> linkElements = driver.findElements(By.tagName("link"));
		
		for (WebElement webElement : linkElements) {
			links.add(webElement.getAttribute("href"));
		}
		
		for (WebElement webElement : driver.findElements(By.tagName("img"))) {
			links.add(webElement.getAttribute("src"));
		}
		
		for (String link : links) {
			System.out.println("The links are : " +link);
		}
		
		System.out.println("The total number of links are "+ links.size());
		
		for (String link : links) {
			HttpURLConnection openConnection = (HttpURLConnection) (new URL(link)).openConnection();
			openConnection.connect();
			System.out.println("The response message is : "+openConnection.getResponseMessage()+"The response code is : "+openConnection.getResponseCode());
			openConnection.disconnect();
		}

		
		
		/*System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.close();*/
	}

}
