package com.test.project.db.po;

import com.wen.mapper.orm.annotation.Column;
import com.wen.mapper.orm.annotation.Entity;
import java.util.Date;

@Entity(tableName="sys_module")
public class SysModulePO {
    @Column(name="id", jdbcType="INTEGER", pk=true)
    private Integer id;

    @Column(name="name", jdbcType="VARCHAR", length=32, remark="权限名称")
    private String name;

    @Column(name="type", jdbcType="INTEGER", remark="权限类型")
    private Integer type;

    @Column(name="code", jdbcType="VARCHAR", length=32, remark="权限编码")
    private String code;

    @Column(name="url", jdbcType="VARCHAR", length=200, remark="操作链接")
    private String url;

    @Column(name="remark", jdbcType="VARCHAR", length=128, remark="备注")
    private String remark;

    @Column(name="parent_id", jdbcType="INTEGER", remark="父级编号，顶级编号为0")
    private Integer parentId;

    @Column(name="order_num", jdbcType="INTEGER", remark="模块排序号")
    private Integer orderNum;

    @Column(name="icon_cls", jdbcType="VARCHAR", length=32, remark="菜单图标样式")
    private String iconCls;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
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