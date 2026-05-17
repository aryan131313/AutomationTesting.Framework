package com.qa.opencart.Tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	
	public void Accsetup() throws InterruptedException {
		
		// before going to My Account page first we have to  to Login
		
	//	AccPage=new AccountsPage(driver);
		
		AccPage=lp.dologin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	

	@Test
	
	public void checklogoutlinkexistTest() {
		
		boolean flag=AccPage.checklogoutlinkexist();
		
		Assert.assertTrue(flag);
	}
	
	@Test
	public void dosearchTest() {
		
	AccPage.dosearch("IMac");
	}
	
	@Test
	public void accPageHeadersTest() {
		
		List<String> headerstext = AccPage.getHeaders();
		
		Assert.assertEquals(headerstext.size(), 4);
	}

}
