package com.qa.opencart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class SearchResultPage {
	
	
	private final By HeaderText=By.tagName("h1");
	
	private final By Searchproductlist=By.xpath("//div[@class='product-thumb']");
	
	
	private WebDriver driver;
	
	private ElementsUtils elutils;
	
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		elutils=new ElementsUtils(driver);
	}

	
	public String  getHeaderText() {
		
		String Text=elutils.getelementtext(HeaderText);
		
		System.out.println("Header Text is : "+HeaderText);
		return Text;
		
	}
	
	public int getProductcount() {
		
		int count=elutils.waitforElementsPresence(Searchproductlist, AppConstants.DEFAULT_SHORT_WAIT).size();
		
		System.out.println("Total count is : "+count);
		
		return count;
		
	}
	
	public ProductInfoPage doclicksearchProduct(String productName) {
		
		System.out.println("Productname is : "+productName);
		
		elutils.doClick(By.linkText(productName));//why we are locating because we have to search product in or call at run or testclass
		
		return new ProductInfoPage(driver);
		
	}
	
}
