package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.entity.Link;
import cn.edu.whut.gumorming.mapper.LinkMapper;
import cn.edu.whut.gumorming.model.dto.QueryLinkDto;
import cn.edu.whut.gumorming.model.vo.LinkVo;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.LinkService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-07 13:23:36
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }
    
    @Override
    public ResponseResult addLink(Link link) {
        save(link);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult getLinkById(Long id) {
        Link link = getById(id);
        // todo 封装Vo
        return ResponseResult.okResult(link);
    }
    
    @Override
    public ResponseResult updateLink(Link link) {
        updateById(link);
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult deleteLinks(List<Long> id) {
        for (Long linkId : id) {
            removeById(linkId);
        }
        
        return ResponseResult.okResult();
    }
    
    @Override
    public ResponseResult<PageVo> pageLinkList(Integer pageNum, Integer pageSize, QueryLinkDto queryLinkDto) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        // 1.查询所有审核通过的友链
        queryWrapper.eq(StringUtils.hasText(queryLinkDto.getStatus()), Link::getStatus, queryLinkDto.getStatus())
                .like(StringUtils.hasText(queryLinkDto.getName()), Link::getName, queryLinkDto.getName());
        // 2.分页
        Page<Link> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        // 封装VO
        // todo 返回字段过多,设计LinkVo
        PageVo vo = new PageVo(page.getRecords(), page.getTotal());
        
        return ResponseResult.okResult(vo);
    }
}