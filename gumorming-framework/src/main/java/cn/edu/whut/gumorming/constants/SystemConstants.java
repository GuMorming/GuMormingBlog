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
     * 根评论状态位
     */
    public static final long COMMENT_ROOT = -1L;
    /**
     * 根评论用户状态位
     */
    public static final long COMMENT_ROOT_USER = -1L;
    /**
     * 评论类型
     */
    public static final String COMMENT_TYPE_ARTICLE = "0";
    public static final String COMMENT_TYPE_LINK = "1";
    public static final String REDIS_ARTICLE_VIEWCOUNT = "article:viewCount";
    /**
     * Redis KEY 前缀
     */
    public static final String REDIS_KEY_PREFIX_ADMIN_LOGIN = "adminlogin:";
    public static final String REDIS_KEY_PREFIX_BLOG_LOGIN = "bloglogin:";
    /**
     * 菜单类型
     */
    // 目录
    public static final String MENU_TYPE_MULU = "M";
    // 菜单
    public static final String MENU_TYPE_CAIDAN = "C";
    // 按钮
    public static final String MENU_TYPE_BUTTON = "F";
}