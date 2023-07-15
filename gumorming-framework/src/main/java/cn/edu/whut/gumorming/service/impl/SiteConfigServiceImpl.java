package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.constants.RedisConstants;
import cn.edu.whut.gumorming.entity.SiteConfig;
import cn.edu.whut.gumorming.mapper.SiteConfigMapper;
import cn.edu.whut.gumorming.service.SiteConfigService;
import cn.edu.whut.gumorming.utils.RedisCache;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (SiteConfig)表服务实现类
 *
 * @author gumorming
 * @since 2023-07-11 22:57:38
 */
@Service("siteConfigService")
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements SiteConfigService {
    @Autowired
    private RedisCache redisCache;
    
    /**
     * @return 网站相关信息
     */
    @Override
    public SiteConfig getSiteConfig() {
        SiteConfig siteConfig = redisCache.getCacheObject(RedisConstants.SITE_SETTING);
        if (Objects.isNull(siteConfig)) {
            // 从数据库中加载
            siteConfig = getById(1L);
            redisCache.setCacheObject(RedisConstants.SITE_SETTING, siteConfig);
        }
        return siteConfig;
    }
}