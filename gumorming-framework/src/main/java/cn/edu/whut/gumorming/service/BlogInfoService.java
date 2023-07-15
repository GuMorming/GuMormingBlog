package cn.edu.whut.gumorming.service;


import cn.edu.whut.gumorming.model.vo.blog.BlogBackInfoVO;
import cn.edu.whut.gumorming.model.vo.blog.BlogInfoVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;

/**
 * 博客业务接口
 *
 * @author ican
 **/
public interface BlogInfoService {
    /**
     * 上传访客信息
     */
    void report();
    
    /**
     * 查看博客信息
     *
     * @return 博客信息
     */
    ResponseResult<BlogInfoVO> getBlogInfo();
    
    /**
     * 查看博客后台信息
     *
     * @return 博客后台信息
     */
    BlogBackInfoVO getBlogBackInfo();
    
    /**
     * 查看关于我信息
     *
     * @return 关于我信息
     */
    String getAbout();
}