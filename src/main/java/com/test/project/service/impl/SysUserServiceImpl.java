package com.test.project.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.test.project.common.BizErrorCodeConst;
import com.test.project.db.dal.SysUserDal;
import com.test.project.db.mapper.SysUserPOMapper;
import com.test.project.db.po.SysUserPO;
import com.test.project.model.SysUser;
import com.test.project.service.SysUserService;
import com.wen.commons.BaseService;
import com.wen.commons.web.PageParams;

@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {
	@Autowired
	private SysUserPOMapper sysUserMapper;
	@Autowired
	private SysUserDal userDal;

	@Override
	public SysUser selectByUsername(String username) {
		checkNotEmpty(username);

		List<SysUserPO> poList = userDal.selectByUsername(username);

		if (CollectionUtils.isEmpty(poList)) {
			return null;
		} else {
			return SysUser.convertTo(poList.get(0));
		}
	}

	@Override
	public SysUser selectById(Integer userId) {
		checkNotNull(userId);

		SysUserPO userPO = userDal.getById(userId);
		if (userPO == null) {
			throwBizException(BizErrorCodeConst.USER_NOT_EXIST);
		}

		return SysUser.convertTo(userPO);
	}

	@Override
	public Page<SysUser> selectUserList(String username, PageParams page) {

		Page<SysUserPO> poList = userDal.selectUserList(username, page);

		return pageConvert(poList, SysUser::convertTo);
	}

	@Override
	public int insert(SysUser user) {
		SysUserPO po = user.convert();
		po.setDeleted(false);
		po.setUpdateTime(new Date());
		po.setCreateTime(new Date());
		sysUserMapper.insert(po);

		logger.info("添加用户成功,{}", po.getId());

		return po.getId();
	}

	@Override
	public void updateById(SysUser user) {
		checkNotNull(user.getId());

		// 检查是否存在
		selectById(user.getId());

		SysUserPO po = user.convert();
		po.setUpdateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(po);

		logger.info("添加用户成功,{}", user.getId());
	}

	@Override
	public void deleteById(Integer userId) {
		checkNotNull(userId);

		// 检查是否存在
		selectById(userId);

		sysUserMapper.deleteByPrimaryKey(userId);

		logger.info("delete user success userId={}", userId);
	}
}