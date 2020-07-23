package com.test.project.db.dal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.test.project.db.mapper.SysUserPOMapper;
import com.test.project.db.po.SysUserPO;
import com.test.project.db.po.SysUserPOExample;
import com.wen.commons.mybatis.orderbyhelper.OrderByHelper;
import com.wen.commons.utils.StringUtils;
import com.wen.commons.web.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author denis.huang
 * @since 2017/4/14
 */
@Component
public class SysUserDal {
    @Autowired
    private SysUserPOMapper sysUserMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public List<SysUserPO> selectByUsername(String username) {
        SysUserPOExample example = new SysUserPOExample();
        example.createCriteria().andUsernameEqualTo(username).andDeletedEqualTo(false);

        return sysUserMapper.selectByExample(example);
    }

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    public SysUserPO getById(Integer userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 分页查询用户信息
     *
     * @param username
     * @param page
     * @return
     */
    public Page<SysUserPO> selectUserList(String username, PageParams page) {
        SysUserPOExample example = new SysUserPOExample();
        SysUserPOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);

        if (StringUtils.isNotEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }

        if (StringUtils.isNotEmpty(page.getSortName())) {
            OrderByHelper.orderBy(StringUtils.camelToUnderline(page.getSortName()), page.getSortType());
        }
        PageHelper.startPage(page.getPageNum(), page.getPageSize());

        return (Page<SysUserPO>) sysUserMapper.selectByExample(example);
    }
}
