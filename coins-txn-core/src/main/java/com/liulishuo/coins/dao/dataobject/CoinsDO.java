package com.liulishuo.coins.dao.dataobject;

import java.io.Serializable;

/**
 * <dl>
 * <dt><b>类功能概要：</b></dt>
 * <dd>金币表映射的实体对象</dd>
 * </dl>
 */
public class CoinsDO extends AbstractDO implements Serializable {
    private static final long serialVersionUID = 4489140399754011706L;
    private Integer id;
    private Long userId;
    private Integer coinNum;
    public CoinsDO(Long userId,Integer coinNum){
    	this.coinNum = coinNum;
    	this.userId = userId;
    }
    public CoinsDO(){
    	
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getCoinNum() {
		return coinNum;
	}
	public void setCoinNum(Integer coinNum) {
		this.coinNum = coinNum;
	}
}
