package cn.edu.whut.gumorming.model.vo.category;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.domain.vo
 * @createTime : 2023/7/10 15:23
 * @Email : gumorming@163.com
 * @Description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVO {
    @ExcelProperty("分类名")
    private String categoryName;
    @ExcelProperty("状态: 0正常;1停用")
    private Integer isDisable;
    
    @ExcelProperty("创建时间")
    private Date createTime;
}