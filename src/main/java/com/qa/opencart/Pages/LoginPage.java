package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class LoginPage {
	
	private final By email=By.id("input-email");
	private final By pass=By.id("input-password");
	private final By loginbtn=By.xpath("//input[@type='submit']");
	private final By forgotpsw=By.xpath("(//a[text()='Forgotten Password'])[2]");
	private final By header=By.tagName("h2");
	private final By register=By.xpath("(//a[text()='Register'])[2]");
	
	private WebDriver driver;

	private ElementsUtils elutils;

	// public constructor
	public LoginPage(WebDriver driver) {

	    this.driver = driver;
	    elutils = new ElementsUtils(driver);

	}
	
	//Actions Methods:
	
	public String getpageTitle() {
		String title=elutils.WaitforTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		
		System.out.println("Title is : "+title);
		
		return title;
	}
	
	public String Getpageurl() {
		String currenturl=elutils.WaitforURL(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT );
		System.out.println("current url is : "+currenturl);
		
		return currenturl;
	}
	
	public   boolean checkforggotlinkexist() {
		
		boolean flag=elutils.iselementDisplayed(forgotpsw);
		return flag;
	}
	
	public boolean isheaderexist()
	{
		System.out.println("Header Text is : "+driver.findElement(header).getText());
		boolean flag=elutils.iselementDisplayed(header);
		return flag;
	}
	
	
	public AccountsPage dologin(String username ,String password) throws InterruptedException {
		
		elutils.dosendkeys(email, username);
		elutils.dosendkeys(pass, password);
		
		elutils.doClick(loginbtn);
		Thread.sleep(2000);
		
		//return driver.getTitle(); as sson as we login we have go to my accounts page for we have to return Accounts page object here.
		
		elutils.WaitforTitle(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		
		 return new AccountsPage(driver);  // as sson as we login a accountspage object is created it will return in where we have to call this method its called page chaining
	}
	
  public Registerpage navigatetoRegisterPage() {
	  
	  elutils.waitForElementPresence(register, AppConstants.DEFAULT_SHORT_WAIT).click();
	  
	  return new Registerpage(driver);
  }
    

}
