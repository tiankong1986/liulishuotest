package com.liulishuo.coins.dao.mapper.daomapper;

import java.sql.SQLException;

import com.liulishuo.coins.dao.dataobject.CoinsDO;
import com.liulishuo.coins.dao.mapper.BaseSqlMapper;

/**
 * <dl>
 * <dt><b>功能概要：</b></dt>
 * <dd>金币DAO接口定义</dd>
 * </dl>
 * 
 */
public interface CoinsDOMapper extends BaseSqlMapper<CoinsDO> {

    /** 插入金币表 */
    public int insert(CoinsDO coins) throws SQLException;
    
    /** 查询金币表 */
    public Integer qryCoinsByUserId(Long userId) throws SQLException;
    
    /** 更新某个用户的金币 */
    public Integer updateCoinsByUserId(CoinsDO coinsDO) throws SQLException;
    
    /** 用户是否有记录 */
    public Integer isExistUser(Long userId) throws SQLException;
    
}
