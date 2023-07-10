package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Link;
import cn.edu.whut.gumorming.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author gumorming
 * @since 2023-07-07 13:23:35
 */

public interface LinkService extends IService<Link> {
    
    
    ResponseResult<PageVo> pageLinkList(Integer pageNum, Integer pageSize);
    
    ResponseResult getAllLink();
}