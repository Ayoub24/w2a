package com.scname.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	/*
	 * WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 *  
	 */
    public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	@BeforeSuite
	public void setUp() {
		
		if (driver == null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				
				System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				
				driver = new FirefoxDriver();			
				
			}else if(config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\eclipse-workspace\\projectname\\src\\test\\resources\\executables\\chromedriver.exe");
				
				driver = new ChromeDriver();
			}
			
			driver.get(config.getProperty("url"));
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicite.wait")), TimeUnit.SECONDS);
			
		}
		
		
		
	}

	@AfterSuite
	public void tearDown() {
		if(driver != null) {
			
			driver.quit();
			
		}
		
		
	}

}
