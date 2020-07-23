package com.test.unit;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.test.project.model.SysUser;
import com.test.project.service.SysUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.unitils.inject.annotation.TestedObject;

/**
 * @author denis.huang
 * @since 2017/6/27
 */
public class SysUserTest extends UnitTestBase {
    @Autowired
    @TestedObject
    private SysUserService userService;

    @Test
    @DatabaseSetup(TEST_DATA_HOME +"sys_user.xml")
    public void test() {
        SysUser user = userService.selectById(1);
        Assert.assertNotNull(user);
    }
}
