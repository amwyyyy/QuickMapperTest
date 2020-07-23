package com.test.project.mapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author denis.huang
 * @since 2017/6/21
 */
public class UserInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column
    private String username;

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
        this.username = username;
    }
}
