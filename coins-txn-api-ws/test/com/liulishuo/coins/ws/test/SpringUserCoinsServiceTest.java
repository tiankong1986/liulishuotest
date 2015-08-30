package com.liulishuo.coins.ws.test;

import org.junit.Assert;
import org.junit.Test;

public class SpringUserCoinsServiceTest {
	@Test
	 public void addCoinsForUserTestCase() throws Exception {
		 //正常case
	  String sr=HttpRequest.sendPost("http://localhost:8080/user/add", "user_id=100&coins=500");
	  System.out.println(sr);
	  Assert.assertEquals(500, Integer.valueOf(sr).intValue());
	 }
	
	@Test
	 public void transferForUserTestCase() throws Exception {
		 //正常case
	  String sr=HttpRequest.sendPost("http://localhost:8080/transaction/transfer", "from_user_id=1&to_user_id=2&coins=500");
	  System.out.println(sr);
	  Assert.assertEquals(500, Integer.valueOf(sr).intValue());
	 }
	
	@Test
	 public void getCoinsForUserTestCase() throws Exception {
		 //正常case
	  String sr=HttpRequest.sendGet("http://localhost:8080/coins/user/1", "");
	  System.out.println(sr);
	  Assert.assertEquals(500, Integer.valueOf(sr).intValue());
	 }

}
