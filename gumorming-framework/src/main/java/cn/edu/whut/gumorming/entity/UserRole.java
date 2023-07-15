package cn.edu.whut.gumorming.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author gumorming
 * @since 2023-07-11 11:19:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user_role")
public class UserRole {
    //用户ID@TableId
    private Long userId;
    //角色ID@TableId
    private Long roleId;
    
    
}