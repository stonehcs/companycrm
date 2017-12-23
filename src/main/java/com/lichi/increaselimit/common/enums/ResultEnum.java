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
    
    CIRCLE_HAS_EXIST(4, "圈子已存在"),
    
    COURSE_NOT_EMPTY(4, "该讲师有对应课程，不能删除"),
    
    FILE_UPLOAD_ERROR(4, "文件上传异常"),
    
    NOT_SUPPORT_ERROR(5, "不支持该银行卡类型"),
    
    MOBILE_NUM_EMPTY(5, "该手机号用户不存在"),
    
    MOBILE_ERROR(5, "请输入正确的手机号码"),
    
    MOBILE_EXIST(5, "该手机号已存在"),
    
    CODE_EXIST(5, "请一分钟后再尝试"),
    
    CODE_NOT_EXIST(5, "验证码不存在"),
    
    VALIDATECODE_ERROR(5, "请填写正确的验证码"),
    
    VALIDATECODE_TIMEOUT(5, "验证码已失效"),

    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    
    REGISTER_ERROR(25, "注册用户失败"),
    
    CONTENT_ERRO(25, "帖子内容异常"),
    
//    PASSWORD_NOT_CHANGE(25, "注册用户失败"),

    LOGOUT_SUCCESS(26, "登出成功"),
    
    TOKEN_EXPIRED(26, "token已失效"),
    
    ENDTIME_BIGGER_THEN_STARTTIME(25, "结束时间应该大于开课时间"),
    
    DEPT_EXIST(25, "部门已存在"), 
    
    ROLE_EXIST(25, "角色已存在"), 
    
    MUNE_BOTTUN_BOTH_NULL(25, "菜单id和按钮id不能同时为空"), 
    
    MENU_EXIST(25, "菜单已存在"),
    
    MENU_NOT_EXIST(25, "菜单不存在"),
    
    HAVE_CHILD_MENU(25, "该菜单下有子菜单,不能删除"),
    
    HAVE_BUTTON(25, "该菜单下有按钮,不能删除"), 
    
    NO_USER_ID(25, "请输入用户id"), 
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
