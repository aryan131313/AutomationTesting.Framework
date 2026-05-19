package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;



public class SearchResultTest extends BaseTest {
	
	
@BeforeClass	
	public void SearchResultsetup() throws InterruptedException {
		
	AccPage=lp.dologin(prop.getProperty("username"), prop.getProperty("password"));
	
	}
   
    @Test
    public void SearchProductTest(){
    	
    	 searchresultpage=AccPage.dosearch("macbook");
    	 productinfopage= searchresultpage.doclicksearchProduct("MacBook Pro");
    	String productheader= productinfopage.getHeaderProduct();
    	Assert.assertEquals(productheader, "MacBook Pro");
    	 
    }
    
    @Test
    public void getproductAmountTest() {
    	
    	String productAmountis=productinfopage.getProductAmount();
    	Assert.assertEquals(productAmountis, "2,000.00");
    }

}
