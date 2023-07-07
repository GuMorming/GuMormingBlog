package cn.edu.whut.gumorming.mapper;

import cn.edu.whut.gumorming.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author gumorming
 * @since 2023-07-07 19:44:02
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}