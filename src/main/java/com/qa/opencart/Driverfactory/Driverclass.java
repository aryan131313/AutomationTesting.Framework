package com.qa.opencart.Driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.frameworks.FrameworksExceptions;
public class Driverclass {
	
	public WebDriver driver;
	
	public Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver=new  ThreadLocal<WebDriver>();   
	
	public OptionsManager optionsmanager;
	
	public WebDriver initDriver(Properties prop) {
		
		optionsmanager=new OptionsManager(prop);
		
		String browser=prop.getProperty("browser");

		System.out.println("Browser Name is : "+browser);
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":

			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			
			break;
		
          case "firefox":
        	 
			tlDriver.set(new FirefoxDriver(optionsmanager.getfirfoxOptions()));
			break;
			
          case "edge":
  
  			tlDriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
  			break;
  			
          case "safari":
  	
  			tlDriver.set(new SafariDriver());
  			break;
			
		default:
			System.out.println(AppErrors.INVALID_BROWSER_MESSAGE);
			throw new FrameworksExceptions("Browser not Supported ");
			//break; as we throwing exception we cannot break it
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public Properties initProp() {
		
		prop=new Properties();
		
		try {

			FileInputStream fp=new FileInputStream("src/test/resources/config/config.properties");
			
			try {
				prop.load(fp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	public static  WebDriver getDriver() {
		
		return tlDriver.get();
	}
	
	public static File getScreenshotASFile() {
		
		File file=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return file;
		
		
	}

}
