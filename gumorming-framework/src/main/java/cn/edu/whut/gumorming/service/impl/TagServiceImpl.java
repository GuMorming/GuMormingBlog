package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.ArticleConstants;
import cn.edu.whut.gumorming.entity.Article;
import cn.edu.whut.gumorming.entity.ArticleTag;
import cn.edu.whut.gumorming.entity.Tag;
import cn.edu.whut.gumorming.enums.HttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.mapper.TagMapper;
import cn.edu.whut.gumorming.model.dto.params.GetParamsDTO;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.article.ArticleCardVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.model.vo.tag.AdminTagVO;
import cn.edu.whut.gumorming.model.vo.tag.TagVO;
import cn.edu.whut.gumorming.service.ArticleService;
import cn.edu.whut.gumorming.service.ArticleTagService;
import cn.edu.whut.gumorming.service.TagService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-09 15:55:49
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    
    @Override
    public ResponseResult<PageVo> pageTagList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 1. 查询条件
        // 有 标签名 则添加查询条件
        queryWrapper
                .orderByDesc(Tag::getCreateTime)
                .like(StringUtils.hasText(getParamsDTO.getTagName()), Tag::getTagName, getParamsDTO.getTagName());
        // 2.分页
        Page<Tag> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        page(page, queryWrapper);
        // 3.封装vo
        List<AdminTagVO> adminTagVOS = BeanCopyUtils.copyBeanList(page.getRecords(), AdminTagVO.class);
        
        return ResponseResult.okResult(new PageVo<>(adminTagVOS, page.getTotal()));
    }
    
    @Override
    public ResponseResult addTag(Tag tag) {
        // 标签名不能为空
        if (!StringUtils.hasText(tag.getTagName())) {
            throw new SystemException(HttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        // 存入数据库中
        save(tag);
        
        return ResponseResult.okResult();
    }
    
    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ResponseResult deleteTag(List<Long> id) {
        for (Long tagId : id) {
            removeById(tagId);
            //删除与标签关联的文章 articleTag
            articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getTagId, tagId));
        }
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getTagInfoById(Long id) {
        // 根据Id获取对应Tag
        Tag tag = getById(id);
        // 封装VO
        AdminTagVO vo = BeanCopyUtils.copyBean(tag, AdminTagVO.class);
        
        return ResponseResult.okResult(vo);
    }
    
    @Override
    public ResponseResult updateTagInfoById(Tag tag) {
        if (!StringUtils.hasText(tag.getTagName())) {
            throw new SystemException(HttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        updateById(tag);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getTagList() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 获取所有Tag
        queryWrapper.eq(Tag::getIsDelete, Boolean.FALSE);
        List<Tag> tags = list(queryWrapper);
        // 封装VO
        List<TagVO> vos = BeanCopyUtils.copyBeanList(tags, TagVO.class);
        // 获取对应标签下的文章数量
        for (TagVO tagVO : vos) {
            tagVO.setArticleCount((int) count(new LambdaQueryWrapper<Tag>().eq(Tag::getId, tagVO.getId())));
        }
        
        return ResponseResult.okResult(vos);
    }
    
    @Override
    public ResponseResult<PageVo<ArticleCardVO>> getTagArticleList(GetParamsDTO getParamsDTO) {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        
        // 获取传入 tagId 下 关联的文章id
        List<Long> articleIds = articleTagService.list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getTagId, getParamsDTO.getTagId()))
                .stream()
                .map(ArticleTag::getArticleId)
                .toList();
        // 1. 查询条件
        articleQueryWrapper
                // 关联文章
                .in(Article::getId, articleIds)
                // 公开状态
                .eq(Article::getStatus, ArticleConstants.STATUS_PUBLIC)
                // 置顶文章在前
                .orderByDesc(Article::getIsTop);
        // 2.分页
        Page<Article> page = new Page<>(getParamsDTO.getCurrent(), getParamsDTO.getSize());
        articleService.page(page, articleQueryWrapper);
        // 3.封装首页文章VO
        List<ArticleCardVO> articleCardVOS = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleCardVO.class);
        articleCardVOS = articleService.setCategoryAndTags(articleCardVOS);
        
        return ResponseResult.okResult(new PageVo<>(articleCardVOS, page.getTotal()));
    }
}