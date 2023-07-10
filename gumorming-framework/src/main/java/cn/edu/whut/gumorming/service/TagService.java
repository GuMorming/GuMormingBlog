package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.dto.TagListDto;
import cn.edu.whut.gumorming.domain.entity.Tag;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 标签(Tag)表服务接口
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
public interface TagService extends IService<Tag> {
    
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
    
    ResponseResult addTag(Tag tag);
}