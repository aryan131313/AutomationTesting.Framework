package com.qa.opencart.Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementsUtils;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPage {

	private final By headerText = By.tagName("h1");

	private final By AmountValue = By.xpath("//ul[@class='list-unstyled']//h2");

	private final By images = By.xpath("//ul[@class='thumbnails']//a");

	private final By AddtoCart = By.xpath("//button[@id='button-cart']");

	private final By shopcart = By.linkText("shopping cart");

	private final By Productdata = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[1]/li");

	private final By productprice = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li");

	private WebDriver driver;

	private ElementsUtils elutils;

	public Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elutils = new ElementsUtils(driver);
	}

	public String getHeaderProduct() {
		String headerTextvalue = elutils.getelementtext(headerText);
		System.out.println("Header Text is : " + headerTextvalue);
		return headerTextvalue;
	}

	public String getProductAmount() {
		String productAmountText = elutils.getelementtext(AmountValue);
		System.out.println("ProductAmountText is  : " + productAmountText);
		return productAmountText;
	}

	public int getProductImages() {

		int ImagesCount = elutils.waitforElementsVisibbility(images, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println("Total images are : " + ImagesCount);
		return ImagesCount;

	}

	private void getProductdata() { //because i dont to give liverage to other classes use this method (private)

		List<WebElement> productlist = elutils.waitforElementsVisibbility(Productdata, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total size of product data is : " + productlist.size());

		for (WebElement e : productlist) {
			String text = e.getText();
			String prductmeta[] = text.split(":");

			String key = prductmeta[0];
			String value = prductmeta[1].trim();

		//	productMap = new HashMap<String, String>(); dont want to hardcore or initialize map here

			productMap.put(key, value);

		}

	}

	private void getProductpricedata() {
		List<WebElement> productPricelist = elutils.waitforElementsVisibbility(productprice,
				AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total size of product data is : " + productPricelist.size());

		String producttextlist = productPricelist.get(0).getText();
		String exTaxprice = productPricelist.get(1).getText().split(":")[1].trim();

		productMap.put("Product price", producttextlist);
		productMap.put("ExTaxprice", exTaxprice);

	}
	
	public Map<String, String> completeProductDetails() {
		
		productMap=new HashMap<String,String>();
		 getProductdata();   //concept of encapsulation this public method is encapsulate these two private method
		 getProductpricedata();
		 
		 System.out.println("Complete Product details are : \n "+productMap);
		 
		 return productMap;
	}

	public void AddCart() {
		elutils.doClick(AddtoCart);
	}

	public CheckOutpage shoppingcart() {
		elutils.WaitforElementClickable(shopcart, AppConstants.DEFAULT_SHORT_WAIT);

		return new CheckOutpage(driver);
	}
}
