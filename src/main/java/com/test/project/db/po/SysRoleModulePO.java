package com.test.project.db.po;

import com.wen.mapper.orm.annotation.Column;
import com.wen.mapper.orm.annotation.Entity;
import java.util.Date;

@Entity(tableName="sys_role_module")
public class SysRoleModulePO {
    @Column(name="id", jdbcType="INTEGER", pk=true)
    private Integer id;

    @Column(name="role_id", jdbcType="INTEGER", remark="角色id")
    private Integer roleId;

    @Column(name="module_id", jdbcType="INTEGER", remark="权限id")
    private Integer moduleId;

    @Column(name="create_time", jdbcType="TIMESTAMP", remark="创建时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}