package com.liulishuo.coins.dao.dataobject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * <dl>
 * <dt><b>类功能概要：</b></dt>
 * <dd>关系表映射的实体抽象对象</dd>
 * </dl>
 */
public abstract class AbstractDO {
	
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
