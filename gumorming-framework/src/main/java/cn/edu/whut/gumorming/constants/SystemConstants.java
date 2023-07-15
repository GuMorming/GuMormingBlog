package cn.edu.whut.gumorming.constants;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.constants
 * @createTime : 2023/7/6 21:02
 * @Email : gumorming@163.com
 * @Description :
 */

public class SystemConstants {
    public static final Integer FALSE = 0;
    public static final Integer TRUE = 1;
    
    /**
     * 是否为博客登录
     */
    public static boolean IS_BLOG_LOGIN = false;
    /**
     * JWT
     */
    //有效期为
    public static final Long JWT_TTL = 1 * 60 * 60 * 1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文 TODO 不能为9字节?
    public static final String JWT_KEY = "gumorming1";
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 分类,友链,菜单 是正常状态
     */
    public static final String STATUS_NORMAL = "0";
    
    /**
     * 菜单类型
     */
    // 目录
    public static final String MENU_TYPE_MULU = "M";
    // 菜单
    public static final String MENU_TYPE_CAIDAN = "C";
    // 按钮
    public static final String MENU_TYPE_BUTTON = "F";
    
    public static final String FLAG_ADMIN = "1";
}