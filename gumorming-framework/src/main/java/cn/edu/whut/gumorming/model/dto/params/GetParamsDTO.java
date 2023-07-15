package cn.edu.whut.gumorming.model.dto.params;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog-Back
 * @Package : cn.edu.whut.gumorming.model.dto
 * @createTime : 2023/7/12 14:05
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@Schema(name = "查询条件")
public class GetParamsDTO {
    
    /**
     * 页码
     */
    @Schema(title = "页码")
    private Integer current;
    
    /**
     * 条数
     */
    @Schema(title = "条数")
    private Integer size;
    
    /**
     * 搜索内容
     */
    @Schema(title = "搜索内容")
    private String keyword;
    
    /**
     * 是否禁用 (0否 1是)
     */
    @Schema(title = "是否禁用 (0否 1是)")
    private Integer isDisable;
    
    /**
     * 分类id
     */
    @Schema(title = "分类id")
    private Long categoryId;
    /**
     * 分类名称
     */
    @Schema(title = "分类名称")
    private String categoryName;
    @Schema(title = "标签名称")
    private String tagName;
    
    @Schema(title = "菜单名称")
    private String menuName;
    @Schema(title = "网站名称")
    private String friendName;
    @Schema(title = "网站URL")
    private String url;
    /**
     * 标签id
     */
    @Schema(title = "标签id")
    private Long tagId;
    
    /**
     * 相册id
     */
    @Schema(title = "相册id")
    private Long albumId;
    
    /**
     * 类型id
     */
    @Schema(title = "类型id")
    private Long typeId;
    
    /**
     * 评论主题类型
     */
    @Schema(title = "评论主题类型")
    private Integer commentType;
    
    /**
     * 文章类型 (1原创 2转载 3翻译)
     */
    @Schema(title = "文章类型 (1原创 2转载 3翻译)")
    private Integer articleType;
    
    /**
     * 登录类型
     */
    @Schema(title = "登录类型")
    private Integer loginType;
    
    /**
     * 用户类型
     */
    @Schema(title = "用户类型")
    private Integer userType;
    @Schema(title = "用户名")
    private String username;
    
    @Schema(title = "手机号")
    private String phoneNumber;
    
    /**
     * 操作模块
     */
    @Schema(title = "操作模块")
    private String optModule;
    
    /**
     * 是否删除 (0否 1是)
     */
    @Schema(title = "是否删除 (0否 1是)")
    private Integer isDelete;
    
    /**
     * 是否通过 (0否 1是)
     */
    @Schema(title = "是否通过 (0否 1是)")
    private Integer isCheck;
    
    /**
     * 文章状态 (1公开 2私密 3草稿)
     * 任务状态 (0运行 1暂停)
     */
    @Schema(title = "状态")
    private Integer status;
    
    /**
     * 任务组名
     */
    @Schema(title = "任务组名")
    private String taskGroup;
    
    /**
     * 调用目标
     */
    @Schema(title = "调用目标")
    private String invokeTarget;
    
    /**
     * 文件路径
     */
    @Schema(title = "文件路径")
    private String filePath;
}