package cn.edu.whut.gumorming.model.vo.friend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 友链VO
 **/
@Data
@Schema(description = "友链VO")
public class FriendVO {
    /**
     * 友链id
     */
    @Schema(title = "友链id")
    private Long id;
    
    /**
     * 友链颜色
     */
    @Schema(title = "友链颜色")
    private String color;
    
    /**
     * 友链名称
     */
    @Schema(title = "友链名称")
    private String name;
    
    /**
     * 友链头像
     */
    @Schema(title = "友链头像")
    private String avatar;
    
    /**
     * 友链地址
     */
    @Schema(title = "友链地址")
    private String url;
    
    /**
     * 友链介绍
     */
    @Schema(title = "友链介绍")
    private String introduction;
    
}