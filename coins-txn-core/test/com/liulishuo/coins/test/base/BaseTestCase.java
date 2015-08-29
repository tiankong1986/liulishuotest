package com.liulishuo.coins.test.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * 测试用例基类
 * </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/coins-txn-service.xml","/META-INF/spring/coins-txn-dao-persistence.xml","/META-INF/spring/coins-txn-dao.xml"})
public class BaseTestCase extends AbstractJUnit4SpringContextTests {
//    /** 各子类都可以使用的Logger，输出到控制台 */
//    protected Logger log = LoggerFactory.getLogger(getClass());
}
