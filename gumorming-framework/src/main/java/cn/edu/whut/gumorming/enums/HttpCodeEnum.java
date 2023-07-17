package cn.edu.whut.gumorming.enums;

/**
 * 返回码及响应信息
 */
public enum HttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    /**
     * 客户端
     */
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    /**
     * 服务器端
     */
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONE_NUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    COMMENT_CONTENT_NOT_NULL(506, "评论内容不能为空"),
    UPDATE_TYPE_ERROR(507, "上传图片类型须为png或jpg"),
    
    REGISTER_USERNAME_NULL(508, "用户名不能为空"),
    REGISTER_PASSWORD_NULL(509, "密码不能为空"),
    REGISTER_NICKNAME_NULL(510, "昵称不能为空"),
    REGISTER_EMAIL_NULL(511, "邮箱不能为空"),
    TAG_NAME_NOT_NULL(512, "标签名不能为空"),
    MENU_PARENT_SELF(513, "修改菜单失败,上级菜单不能选择自己"),
    MENU_CHILDREN_EXIST(514, "子菜单存在,不允许删除"),
    DELETE_ADMIN_DENY(515, "不允许删除管理员"),
    REGISTER_EMAIL_VALID(516, "邮箱格式不正确"),
    EMAIL_CODE_INVALID(517, "验证码无效"),
    REQUIRE_CATEGORY(518, "文章需要分类"),
    
    FRIEND_NAME_NULL(519, "友链名称为空"),
    FRIEND_URL_NULL(520, "友链链接为空"),
    CATEGORY_NAME_NULL(521, "分类名称为空"),
    USER_IS_DISABLE(522, "您的账户已被禁用");
    
    int code;
    String msg;
    
    HttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
}