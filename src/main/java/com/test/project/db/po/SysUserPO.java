package com.test.project.db.po;

import com.wen.mapper.orm.annotation.Column;
import com.wen.mapper.orm.annotation.Entity;
import java.util.Date;

@Entity(tableName="sys_user")
public class SysUserPO {
    @Column(name="id", jdbcType="INTEGER", pk=true)
    private Integer id;

    @Column(name="username", jdbcType="VARCHAR", length=32, remark="用户名")
    private String username;

    @Column(name="password", jdbcType="VARCHAR", length=128, remark="密码")
    private String password;

    @Column(name="mobile", jdbcType="VARCHAR", length=32, remark="手机")
    private String mobile;

    @Column(name="deleted", jdbcType="BIT", remark="删除状态")
    private Boolean deleted;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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