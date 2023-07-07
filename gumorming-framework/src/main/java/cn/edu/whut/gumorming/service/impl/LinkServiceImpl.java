package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.SystemConstants;
import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.entity.Link;
import cn.edu.whut.gumorming.domain.vo.LinkVo;
import cn.edu.whut.gumorming.mapper.LinkMapper;
import cn.edu.whut.gumorming.service.LinkService;
import cn.edu.whut.gumorming.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        // 查询所有审核通过的友链
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        // 封装VO
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        
        return ResponseResult.okResult(linkVos);
    }
}