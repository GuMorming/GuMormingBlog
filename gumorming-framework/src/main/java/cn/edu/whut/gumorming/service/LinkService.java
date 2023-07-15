package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.Link;
import cn.edu.whut.gumorming.model.dto.QueryLinkDto;
import cn.edu.whut.gumorming.model.vo.PageVo;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 友链(Link)表服务接口
 *
 * @author gumorming
 * @since 2023-07-07 13:23:35
 */

public interface LinkService extends IService<Link> {
    
    
    ResponseResult<PageVo> pageLinkList(Integer pageNum, Integer pageSize, QueryLinkDto queryLinkDto);
    
    ResponseResult getAllLink();
    
    ResponseResult addLink(Link link);
    
    ResponseResult getLinkById(Long id);
    
    ResponseResult updateLink(Link link);
    
    ResponseResult deleteLinks(List<Long> id);
}