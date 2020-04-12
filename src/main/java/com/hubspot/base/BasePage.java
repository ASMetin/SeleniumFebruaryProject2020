package com.hubspot.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public static String flash;
	
	public WebDriver initialize_driver(Properties prop){
		
	//String browser = "chrome";
		String browser = prop.getProperty("browser");
		String headless = prop.getProperty("headless"); //headless
		flash = prop.getProperty("elementflash");
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/msm/Documents/Drivers/chromedriver");
			//WebDriverManager.chromedriver().setup();
			if(headless.equalsIgnoreCase("yes")){
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			}else{
			driver = new ChromeDriver();
			}
	}else if (browser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		if(headless.equalsIgnoreCase("yes")){
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--headless");
			driver = new FirefoxDriver(fo);
		}else{	
		driver = new FirefoxDriver();
		}
	}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// Eger TimeOutException hatasi veriyorsa Url hatasi vardir, url dogruysa sayfaya yada code lara bakilmali
		driver.get(prop.getProperty("url"));
		
		try {  // bir frameworkte NoSuchElementException veriyorsa wait ile ilgilidir.
			Thread.sleep(5000);
		} catch (Exception e) {
			
			e.printStackTrace(); // prints throwable exceptions
		}
		return driver;
	}
	public Properties initialize_propertise(){// this class get information from config.properties and import into framework
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("/Users/msm/Documents/workspace/1FebruaryTestNG2020/"
					+ "src/main/java/config/hubspot/config/config.properties");//The path of the referenced file
			prop.load(ip); //config.properties information loads
		} catch (IOException e) {//
			e.printStackTrace();// if there is an any error we can follow the log 
		}
		return prop;
	}
	public void quitBrowser(){
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("some exception occured while quitting browser");
		}
	}
	public void closeBrowser(){
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("some exception occured while closing browser");
		}
	}
}
