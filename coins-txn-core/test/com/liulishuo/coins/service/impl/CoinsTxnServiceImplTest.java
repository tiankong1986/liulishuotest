package com.liulishuo.coins.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.liulishuo.coins.test.base.BaseTestCase;
import com.vf.fsn.txn.service.CoinsTxnService;

public class CoinsTxnServiceImplTest extends BaseTestCase{
	 @Resource(name = "coinsTxnService")
	 private CoinsTxnService coinsTxnService;
	 
	 @Test
	 public void addCoinsForUserTestCase001() throws Exception {
		 //正常case
		 coinsTxnService.addCoinsForUser(1000L, 10);
		int coins = coinsTxnService.getCoinsByUserId(1000L);
		Assert.assertEquals(10, coins);
	 }
	 
	 @Test
	 public void addCoinsForUserTestCase002() throws Exception {
		 //coins = null
		 coinsTxnService.addCoinsForUser(11L, null);
		int coins = coinsTxnService.getCoinsByUserId(11L);
		Assert.assertEquals(10, coins);
	 }
	 @Test
	 public void getCoinsByUserIdTestCase001() throws Exception {
		//此用户不存在
		 coinsTxnService.addCoinsForUser(10L, 10);
		 Integer coins = coinsTxnService.getCoinsByUserId(15L);
		Assert.assertEquals(null, coins);
	 }
	 @Test
	 public void getCoinsByUserIdTestCase002() throws Exception {
		//正常得到100
		 Integer coins = coinsTxnService.getCoinsByUserId(1000L);
		Assert.assertEquals(10, coins.intValue());
	 }
	 
	 @Test
	 public void transferTestCase001() throws Exception {
		//正常
		 coinsTxnService.transfer(1000L, 10L, Integer.valueOf(5));
		 Integer coin1 = coinsTxnService.getCoinsByUserId(1000L);
		 Integer coin2 = coinsTxnService.getCoinsByUserId(10L);
		Assert.assertEquals(5, coin1.intValue());
		Assert.assertEquals(15, coin2.intValue());
	 }
	 
	 @Test
	 public void transferTestCase002() throws Exception {
		//转出或者转入用户不存在
		 coinsTxnService.transfer(1005L, 10L, Integer.valueOf(5));
		 Integer coin1 = coinsTxnService.getCoinsByUserId(1000L);
		 Integer coin2 = coinsTxnService.getCoinsByUserId(10L);
		Assert.assertEquals(5, coin1.intValue());
		Assert.assertEquals(15, coin2.intValue());
	 }
	 
	 @Test
	 public void transferTestCase003() throws Exception {
		//转出或者转入用户金额不存在
		 coinsTxnService.transfer(1000L, 11L, Integer.valueOf(5));
		 Integer coin1 = coinsTxnService.getCoinsByUserId(1000L);
		 Integer coin2 = coinsTxnService.getCoinsByUserId(10L);
		Assert.assertEquals(5, coin1.intValue());
		Assert.assertEquals(15, coin2.intValue());
	 }
}
