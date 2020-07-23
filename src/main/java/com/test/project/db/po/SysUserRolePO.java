package com.test.project.db.po;

import com.wen.mapper.orm.annotation.Column;
import com.wen.mapper.orm.annotation.Entity;
import java.util.Date;

@Entity(tableName="sys_user_role")
public class SysUserRolePO {
    @Column(name="id", jdbcType="INTEGER", pk=true)
    private Integer id;

    @Column(name="user_id", jdbcType="INTEGER", remark="用户id")
    private Integer userId;

    @Column(name="role_id", jdbcType="INTEGER", remark="角色id")
    private Integer roleId;

    @Column(name="create_time", jdbcType="TIMESTAMP", remark="创建时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}