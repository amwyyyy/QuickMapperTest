package com.test.project.db.po;

import com.wen.mapper.orm.annotation.Column;
import com.wen.mapper.orm.annotation.Entity;
import java.util.Date;

@Entity(tableName="sys_role")
public class SysRolePO {
    @Column(name="id", jdbcType="INTEGER", pk=true)
    private Integer id;

    @Column(name="name", jdbcType="VARCHAR", length=32, remark="角色名称")
    private String name;

    @Column(name="remark", jdbcType="VARCHAR", length=128, remark="备注")
    private String remark;

    @Column(name="create_time", jdbcType="TIMESTAMP", remark="创建时间")
    private Date createTime;

    @Column(name="update_time", jdbcType="TIMESTAMP", remark="更新时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}