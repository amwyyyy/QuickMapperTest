package com.test.project.common;

import com.wen.commons.exception.ErrorCode;

/**
 * 业务错误码定义
 * 
 * @author denis.huang
 * @since 2017年2月15日
 */
public class BizErrorCodeConst {
    // 用户模块异常 （1000-1100）
    public static final ErrorCode USER_NOT_EXIST = new ErrorCode(1000, "用户不存在");
}
