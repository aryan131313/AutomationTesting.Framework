package com.qa.opencart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void ProductInfosetup() throws InterruptedException {

		AccPage=lp.dologin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductData() {

		Object obj[][] = new Object[3][2];

		obj[0][0] = "macbook";
		obj[0][1] = "MacBook Air";

		obj[1][0] = "samsung";
		obj[1][1] = "Samsung Galaxy Tab 10.1";

		obj[2][0] = "canon";
		obj[2][1] = "Canon EOS 5D";

		return obj;

	}

	@DataProvider
	public Object[][] getProductsImagescount() {
		Object obj[][] = new Object[3][3];

		obj[0][0] = "macbook";
		obj[0][1] = "MacBook Air";
		obj[0][2] = 4;

		obj[1][0] = "samsung";
		obj[1][1] = "Samsung Galaxy Tab 10.1";
		obj[1][2] = 7;

		obj[2][0] = "canon";
		obj[2][1] = "Canon EOS 5D";
		obj[2][2] = 3;

		return obj;

	}

	@Test(dataProvider = "getProductData")
	public void GetHeaderTest(String searchproduct, String ProductName) {

		searchresultpage = AccPage.dosearch(searchproduct);
		productinfopage = searchresultpage.doclicksearchProduct(ProductName);
		String ActValue = productinfopage.getHeaderProduct();
		Assert.assertEquals(ActValue, ProductName);

	}

	@Test(dataProvider = "getProductsImagescount")
	public void GetImagescountTest(String searchproduct, String ProductName, int count) {

		searchresultpage = AccPage.dosearch(searchproduct);
		productinfopage = searchresultpage.doclicksearchProduct(ProductName);

		int imagescount = productinfopage.getProductImages();
		System.out.println("Total images : " + imagescount);

		Assert.assertEquals(imagescount, count);
	}
	
	@Test
	public void getCompleteInfoTest() {
		
		searchresultpage=AccPage.dosearch("macbook");
		productinfopage=searchresultpage.doclicksearchProduct("MacBook Pro");
		Map<String, String> completeProductInformation = productinfopage.completeProductDetails();
		Assert.assertEquals(completeProductInformation.get("Brand"), "Apple");
		Assert.assertEquals(completeProductInformation.get("Availability"), "Out Of Stock");
		Assert.assertEquals(completeProductInformation.get("ExTaxprice"), "$2,000.00");
		Assert.assertEquals(completeProductInformation.get("Product Code"), "Product 18");
		Assert.assertEquals(completeProductInformation.get("Product price"), "$2,000.00");
		Assert.assertEquals(completeProductInformation.get("Reward Points"), "800");
	}

}
