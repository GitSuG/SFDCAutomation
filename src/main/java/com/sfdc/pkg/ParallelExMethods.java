package com.sfdc.pkg;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParallelExMethods {

	
	@BeforeTest
	public void beforeTest() {
		String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		System.out.println("Before Test:"+today);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}
	
	@Test
	public void m1() {
		System.out.println("m1");
	}
	
	@Test
	public void m2() {
		System.out.println("m2");
	}
	
	@Test
	public void m3() {
		System.out.println("m3");
	}
	
	@Test
	public void m4() {
		System.out.println("m4");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}
}
