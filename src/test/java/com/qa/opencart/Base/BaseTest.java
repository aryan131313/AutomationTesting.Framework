package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.Driverfactory.Driverclass;
import com.qa.opencart.Pages.AccountsPage;
import com.qa.opencart.Pages.CheckOutpage;
import com.qa.opencart.Pages.LoginPage;
import com.qa.opencart.Pages.ProductInfoPage;
import com.qa.opencart.Pages.Registerpage;
import com.qa.opencart.Pages.SearchResultPage;

@Listeners(ChainTestListener.class)

public class BaseTest {
	
	public Driverclass Dc;
	public WebDriver driver;
	public LoginPage lp;
	public AccountsPage AccPage;
	public SearchResultPage searchresultpage;
	public ProductInfoPage productinfopage;
	public CheckOutpage checkoutpage;
	public Properties prop;
	
	public Registerpage rp;
	
	@Parameters ({"browser"})
	
	@BeforeTest
	public void setup(String browserName) {
		 Dc=new Driverclass();
		prop= Dc.initProp();
		if(browserName!=null) {
			
			prop.setProperty("browser", browserName);
		}
		  driver=Dc.initDriver(prop);  //return type of initWebdriver is WeDriver
		  
		   lp=new LoginPage(driver);
	}
	
	@AfterMethod //after every test of every class it wikk make sure test cases are running fine and if any test case is failing chaintest listener it will creaate a sreenshot from driverfactoryclass
	public void Attachscreenshot(ITestResult result) { //ITestREsult its interface coming from testng
		
		if(!result.isSuccess()) {
			ChainTestListener.embed(Driverclass.getScreenshotASFile(), "image/png");
		}
		
		
	}
	
//
//	@AfterTest
//	public void tearDown() {
//			
//		driver.quit();
//	}

}
