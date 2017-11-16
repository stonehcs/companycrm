package com.lichi.increaselimit.common.enums;

import lombok.Getter;

/**
 * 返回状态和参数的枚举
 * @author majie
 *
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    ARTICLE_NO_EMPTY(2, "帖子数量不为空，不能删除该圈子"),

    CIRCLE_NO_EXIST(3, "圈子不存在"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(26, "登出成功"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
