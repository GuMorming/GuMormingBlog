package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.entity.Tag;
import cn.edu.whut.gumorming.mapper.TagMapper;
import cn.edu.whut.gumorming.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}