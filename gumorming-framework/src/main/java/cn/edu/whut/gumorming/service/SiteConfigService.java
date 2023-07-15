package cn.edu.whut.gumorming.service;

import cn.edu.whut.gumorming.entity.SiteConfig;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * (SiteConfig)表服务接口
 *
 * @author gumorming
 * @since 2023-07-11 22:57:38
 */
public interface SiteConfigService extends IService<SiteConfig> {
    /**
     * 获取网站配置
     *
     * @return 网站配置
     */
    SiteConfig getSiteConfig();
}