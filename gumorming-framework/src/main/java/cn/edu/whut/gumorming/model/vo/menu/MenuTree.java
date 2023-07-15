package cn.edu.whut.gumorming.model.vo.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 菜单下拉树
 **/
@Data
@Schema(description = "菜单下拉树")
public class MenuTree {
    
    /**
     * 菜单id
     */
    @Schema(title = "菜单id")
    private Integer id;
    
    /**
     * 父菜单id
     */
    @JsonIgnore
    @Schema(title = "父菜单id")
    private Integer parentId;
    
    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String label;
    
    /**
     * 子菜单树
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "子菜单树")
    private List<MenuTree> children;
}