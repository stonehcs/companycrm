package com.lichi.increaselimit.common.enums;

import lombok.Getter;

/**
 * 返回状态和参数的枚举
 * @author majie
 *
 */
@Getter
public enum LimuEnum {

	/**
	 * 学历
	 */
    EDUCATION(1, "api.education.get"),
    
    /**
     * 央行征信
     */
    CREDIT(2, "api.credit.get"),
    
    /**
     * 公积金
     */
    HOUSEFUND(3, "api.housefund.getareas"),

    CIRCLE_NO_EXIST(3, "圈子不存在"),
    
    CIRCLE_HAS_EXIST(4, "圈子已存在"),
    
    COURSE_NOT_EMPTY(4, "该讲师有对应课程，不能删除"),
    
    FILE_UPLOAD_ERROR(4, "文件上传异常"),
    
    NOT_SUPPORT_ERROR(5, "不支持该银行卡类型"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(26, "登出成功"),
    ;

    private Integer code;

    private String message;

    LimuEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
