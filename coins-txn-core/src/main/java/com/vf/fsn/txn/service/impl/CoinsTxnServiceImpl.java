package com.vf.fsn.txn.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.liulishuo.coins.basic.ReturnConst;
import com.liulishuo.coins.dao.dataobject.CoinsDO;
import com.liulishuo.coins.dao.mapper.daomapper.CoinsDOMapper;
import com.vf.fsn.txn.service.CoinsTxnService;

/**
 * <dl>
 * <dt><b>类功能概要：</b></dt>
 * <dd>金币交易接口实现。</dd>
 * </dl>
 */
public class CoinsTxnServiceImpl implements CoinsTxnService{
	@Resource(name = "coinsMapper")
	private CoinsDOMapper coinsMapper;
	
	@Transactional(rollbackFor={Exception.class})//应该自定义异常
	@Override
	public String addCoinsForUser(Long userId, Integer coins) throws Exception {
		//参数检验
		isNullThrowException(userId,"用户ID不能为空");
		isNullThrowException(coins,"金币数量不能为空");
		isFalseThrowException(coins >= 0,"金币数量不能小于0");
		
		try {
				CoinsDO coinsDO = new CoinsDO(userId,coins);
				if(isExistThisUser(userId)){
					coinsMapper.updateCoinsByUserId(coinsDO);
				}else{
					coinsMapper.insert(coinsDO);
				}
			} catch (SQLException e) {
				//添加日志
				e.printStackTrace();
			}
		return ReturnConst.SUCCESS_CODE;
	}

	@Transactional(readOnly=true,rollbackFor={Exception.class})//应该自定义异常
	@Override
	public Integer getCoinsByUserId(Long userId) throws Exception{
		isNullThrowException(userId,"用户ID不能为空");
		Integer coins = 0;
		coins = coinsMapper.qryCoinsByUserId(userId);
		if(null == coins){
			throw new Exception("用户不存在或者金币为null!");//应该自定义异常
		}
		return coins;
	}
	
	@Transactional(rollbackFor={Exception.class})//应该自定义异常
	@Override
	public String transfer(Long outUserId, Long inUserId, Integer coins) throws Exception {
		//参数检验
		isNullThrowException(outUserId,"转出用户ID不能为空");
		isNullThrowException(inUserId,"转入用户ID不能为空");
		isNullThrowException(coins,"金币数量不能为空");
		isFalseThrowException(coins >= 0,"金币数量不能小于0");
		
		CoinsDO coinsDO = new CoinsDO();
		//判断用户是否存在
		if(!(isExistThisUser(outUserId) && isExistThisUser(inUserId))){
			//记录日志，用户不存在
			throw new Exception("转出用户不存在或者转入用户不存在");//应该自定义异常
		}
		
		Integer outUserCoins = coinsMapper.qryCoinsByUserId(outUserId);
		Integer inUserCoins = coinsMapper.qryCoinsByUserId(inUserId);
		//判断用户的余额非空
		if(inUserCoins == null || outUserCoins == null){
			//记录日志，用户不存在
			throw new Exception("转出用户金额为空或者转入用户金额为空");//应该自定义异常
		}
		//判断用户的余额是否够用
		if(outUserCoins < coins){
			//记日志，余额不足
			throw new Exception("转出用户余额不足");//应该自定义异常
		}
		coinsDO.setUserId(outUserId);
		coinsDO.setCoinNum(-coins);
		//需要事务
		coinsMapper.updateCoinsByUserId(coinsDO);
		
		coinsDO.setUserId(inUserId);
		coinsDO.setCoinNum(coins);
		coinsMapper.updateCoinsByUserId(coinsDO);
		
		return ReturnConst.SUCCESS_CODE;
	}
	
	private boolean isExistThisUser(Long userId) throws SQLException{
		
		int recNum = coinsMapper.isExistUser(userId);
		if(recNum > 0){
			return true;
		}
		return false;
	}
	
	public void isNullThrowException(Object object,String message) throws Exception {
        if (object == null) throw new Exception(message);//应该自定义异常
        if ("".equals(object)) throw new Exception(message);//应该自定义异常
    }
	
	public void isFalseThrowException(boolean flag,String message) throws Exception {
        if (!flag) throw new Exception(message);//应该自定义异常
    }
}
