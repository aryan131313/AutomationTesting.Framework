package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

public class checkoutTest extends BaseTest {

	@BeforeClass
	// “Run this method ONLY ONCE before all @Test methods of that class
	public void checkoutsetup() throws InterruptedException {

		AccPage = lp.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod

//“Run this method BEFORE every @Test method.
	public void getHeadertexttest() {
		searchresultpage = AccPage.dosearch("macbook");
		productinfopage = searchresultpage.doclicksearchProduct("MacBook Pro");
		productinfopage.AddCart();
		checkoutpage = productinfopage.shoppingcart();
		String headertextvalue = checkoutpage.geTHeadervalue();
		System.out.println("Header text is : " + headertextvalue);
		Assert.assertTrue(headertextvalue.contains("Shopping Cart"));
	}

	@Test
	public void PageTextTest() {
		String pagetextvalue = checkoutpage.Getpagetext();
		System.out.println("Page Text is : " + pagetextvalue);
		Assert.assertTrue(pagetextvalue.contains("What would you like"));

	}
}
