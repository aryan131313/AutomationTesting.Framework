package com.qa.opencart.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Utils.StringUtils;

public class RegisterpageTest extends BaseTest{
	
	@BeforeClass
	public void naviagteuserRegistration() {
		
		rp=lp.navigatetoRegisterPage();
	}
	
	@Test
	public void userregistrationTest() {
		rp.userRegistration("Aryan", "Kapila", StringUtils.GenerateEmail(), "8580797695", "Selenium@1234", "yes");
		
		String successmsgtext=rp.successmsg();
		System.out.println("Actual Success Message is : " + successmsgtext);
		Assert.assertTrue(successmsgtext.equals("Your Account Has Been Created!"));
	}

	

}
