package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.dto.TagListDto;
import cn.edu.whut.gumorming.domain.entity.Tag;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.mapper.TagMapper;
import cn.edu.whut.gumorming.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 标签(Tag)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    
    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 1. 查询条件
        // 有 标签名 则添加查询条件
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        // 有 备注名 则添加查询条件
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        // 2.分页
        Page<Tag> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装
        PageVo vo = new PageVo(page.getRecords(), page.getTotal());
        
        return ResponseResult.okResult(vo);
    }
    
    @Override
    public ResponseResult addTag(Tag tag) {
        // 标签名不能为空
        if (!StringUtils.hasText(tag.getName())) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        // 存入数据库中
        save(tag);
        
        return ResponseResult.okResult();
    }
}