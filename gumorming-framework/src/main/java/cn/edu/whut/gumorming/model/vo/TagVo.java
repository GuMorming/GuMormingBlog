package cn.edu.whut.gumorming.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/10 13:51
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {
    @TableId
    private Long id;
    //标签名
    private String name;
    //备注
    private String remark;
}