package com.qa.opencart.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class AccountsPage {
	
	
	private final By search=By.xpath("//input[@type='text']");
	private final By headers=By.tagName("h2");
	private final By logoutlinkexist=By.xpath("(//a[text()='Logout'])[2]");
	private final By searchIcon=By.xpath("//div[@id='search']//button");
	
	private WebDriver driver;
	
	private ElementsUtils elutils;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		
		 elutils = new ElementsUtils(driver);

	}
	
	
	public List<String> getHeaders() {
//		List<WebElement> elements = driver.findElements(headers);
//		List<String> headertext=new ArrayList<String>();
//		
//		for(WebElement e:elements) {
//			
//			String textlist= e.getText();
//			
//			headertext.add(textlist);
//		}
//		return headertext;
		List<String> elementsTextlist = elutils.getElementsTextlist(headers);
		return elementsTextlist;
	}
	
	
	public boolean checklogoutlinkexist() {
		
		boolean flag=elutils.getelement(logoutlinkexist).isDisplayed();
		return flag;
	}
	
	public SearchResultPage dosearch(String SearchValue) {
		
		WebElement el=elutils.waitForElementPresence(search, AppConstants.DEFAULT_SHORT_WAIT);
		el.clear();
		
		elutils.getelement(search).sendKeys(SearchValue);
		elutils.doClick(searchIcon);
		
		return new SearchResultPage(driver);
	}
	
	
}
