package com.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.project.db.mapper.SysUserPOMapper;
import com.test.project.db.po.SysUserPO;
import com.test.project.db.po.SysUserPOExample;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "/spring-beans.xml" }) // 加载配置文件
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest {

	@Autowired
	private SysUserPOMapper userMapper;

	// @Test
	public void test001() {
		SysUserPO po = new SysUserPO();
		po.setId(9999);
		po.setCreateTime(new Date());
		po.setDeleted(false);
		po.setMobile("15999554794");
		po.setPassword("123");
		po.setUpdateTime(new Date());
		po.setUsername("wwww");

		userMapper.insert(po);
	}

	// @Test
	public void test002() {
		SysUserPO po = userMapper.selectByPrimaryKey(9999);
		System.err.println(po.getId() + "-" + po.getUsername() + "-" + po.getMobile() + "-" + po.getPassword());
	}

	// @Test
	public void test003() {
		SysUserPO po = new SysUserPO();
		po.setId(9999);
		po.setUsername("eeee");
		po.setPassword("aaa");
		po.setDeleted(true);
		po.setCreateTime(new Date());
		userMapper.updateByPrimaryKey(po);
	}

	// @Test
	public void test004() {
		SysUserPO po = userMapper.selectByPrimaryKey(9999);
		System.err.println(po.getId() + "-" + po.getUsername() + "-" + po.getMobile() + "-" + po.getPassword());
	}

	// @Test
	public void test005() {
		SysUserPO po = new SysUserPO();
		po.setId(9999);
		po.setUsername("dddd");
		userMapper.updateByPrimaryKeySelective(po);
	}

	// @Test
	public void test006() {
		SysUserPO po = userMapper.selectByPrimaryKey(9999);
		System.err.println(po.getId() + "-" + po.getUsername() + "-" + po.getMobile() + "-" + po.getPassword());
	}

	// @Test
	public void test007() {
		userMapper.deleteByPrimaryKey(9999);
	}

	@Test
	public void test008() {
		SysUserPOExample example = new SysUserPOExample();
		example.createCriteria().andUsernameEqualTo("wen");
		List<SysUserPO> pos = userMapper.selectByExample(example);

		Assert.assertTrue(pos.size() == 1);
		Assert.assertTrue(pos.get(0).getUsername().equals("wen"));
	}

	@Test
	public void test009() {
		SysUserPOExample example = new SysUserPOExample();
		example.createCriteria().andUsernameEqualTo("yun");

		SysUserPO po = new SysUserPO();
		po.setUsername("yun");
		po.setDeleted(false);
		po.setCreateTime(new Date());
		po.setMobile("11111");
		po.setPassword("123");

		userMapper.updateByExample(po, example);

		List<SysUserPO> pos = userMapper.selectByExample(example);
		Assert.assertTrue(pos.get(0).getMobile().equals("11111"));
	}

	@Test
	public void test010() {
		SysUserPOExample example = new SysUserPOExample();
		example.createCriteria().andUsernameEqualTo("wen");
		Assert.assertTrue(userMapper.countByExample(example) == 1);
	}

	@Test
	public void test011() {
		SysUserPOExample example = new SysUserPOExample();
		example.createCriteria().andUsernameEqualTo("234");

		userMapper.deleteByExample(example);

		Assert.assertTrue(userMapper.selectByExample(example).size() == 0);
	}

	@Test
	public void test012() {
		SysUserPOExample example = new SysUserPOExample();
		example.createCriteria().andUsernameEqualTo("yun");

		SysUserPO po = new SysUserPO();
		po.setUsername("yun");
		po.setMobile("111444");

		userMapper.updateByExampleSelective(po, example);

		Assert.assertTrue(userMapper.selectByExample(example).get(0).getMobile().equals(po.getMobile()));
	}

	@Test
	public void test013() {
		SysUserPO po = new SysUserPO();
		po.setId(2);
		po.setUsername("yun");
		po.setMobile("222555");
		userMapper.updateByPrimaryKeySelective(po);

		Assert.assertTrue(userMapper.selectByPrimaryKey(2).getMobile().equals(po.getMobile()));
	}
}
