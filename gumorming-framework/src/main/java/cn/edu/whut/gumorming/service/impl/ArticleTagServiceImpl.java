package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.entity.ArticleTag;
import cn.edu.whut.gumorming.mapper.ArticleTagMapper;
import cn.edu.whut.gumorming.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-10 14:51:51
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}