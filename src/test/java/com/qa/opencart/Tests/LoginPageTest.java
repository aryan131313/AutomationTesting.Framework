package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
public class LoginPageTest extends BaseTest {
	
	// All methods will run in Alphabetic order
	
	@Test
public  void getPageTitleTest() {
		//as this is child class of basetest we can directly call loginpage refrence from base Basetestclass.Concept of inheritence 
	    String title = lp.getpageTitle();

	    //System.out.println(title);

	    Assert.assertEquals(title, "Account Login");//we can do assertions fromAssert class coming from TestNg
		
	}
	
	@Test
	public  void GetpageurlTest() {
			
		    String Url = lp.Getpageurl();

		   // System.out.println(Url);

		    Assert.assertTrue(Url.contains("account/login")); //one more method is coming from assertclass Assert.assertTrue(actUrl.contains(al));

}
	@Test
	public  void checkforggotlinkexistTest() {
			
		    boolean forgotpsw = lp.checkforggotlinkexist();

		    System.out.println(forgotpsw);

		    Assert.assertTrue(forgotpsw);
	
}
	@Test
	public  void isheaderexistTest() {
			
		    boolean headeris = lp.isheaderexist();

		    System.out.println(headeris);

		    Assert.assertTrue(headeris);
	
}
	@Test
	public  void loginPageTest() throws InterruptedException {
			
		    AccPage=lp.dologin("aryankapila99@gmail.com", "Selenium@1234");
              
		  

		  //  Assert.assertEquals(ActualTitle,"My Account");
	
	Assert.assertTrue(AccPage.checklogoutlinkexist());
}
}