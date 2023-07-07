package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.entity.Comment;
import cn.edu.whut.gumorming.mapper.CommentMapper;
import cn.edu.whut.gumorming.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 19:44:02
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}