package com.liulishuo.coins.dao.mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * <dl>
 * <dt><b>功能概要：</b></dt>
 * <dd>BaseSqlMapper对SqlMapper接口封装，提供常用的增、删、改、查</dd>
 * <dd>可扩展此接口,增添所需功能</dd>
 * </dl>
 */
public interface BaseSqlMapper<T> extends SqlMapper {

    public int insert(T entity) throws SQLException;

    public int update(T entity) throws SQLException;

    public int delete(T entity) throws SQLException;

    public T get(T entity) throws SQLException;

    public List<T> getList(T entity) throws SQLException;

}
