package com.vf.fsn.txn.service;


/**
 * <dl>
 * <dt><b>类功能概要：</b></dt>
 * <dd>金币交易查询接口。</dd>
 * </dl>
 */
public interface CoinsTxnService {
	public String addCoinsForUser(Long userId,Integer coins) throws Exception ;
	public Integer getCoinsByUserId(Long userId) throws Exception;
	public String transfer(Long outUserId,Long inUserId,Integer coins) throws Exception;
}
