package cn.edu.whut.gumorming.constants;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.constants
 * @createTime : 2023/7/12 11:21
 * @Email : gumorming@163.com
 * @Description :
 */

public class CommentConstants {
    /**
     * 根评论状态位
     */
    public static final long ROOT = -1L;
    /**
     * 根评论用户状态位
     */
    public static final long ROOT_USER = -1L;
    
    /**
     * 评论类型 (1文章 2友链 3说说)
     */
    public static final Integer TYPE_ARTICLE = 1;
    public static final Integer TYPE_LINK = 2;
    public static final Integer TYPE_TALK = 3;
    /**
     * 是否检查
     */
    public static final Integer IS_CHECK_TRUE = 1;
    public static final Integer IS_CHECK_FALSE = 0;
}