package com.test.unit;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * 单元测试基类
 * 
 * @author denis.huang
 * @since 2017/6/27
 */
@ContextConfiguration(locations = {"classpath*:spring-beans-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
public abstract class UnitTestBase {
    public static final String TEST_DATA_HOME = "classpath:/data/"; // 测试数据文件
}
