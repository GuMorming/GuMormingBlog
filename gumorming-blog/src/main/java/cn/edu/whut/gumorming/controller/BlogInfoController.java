package cn.edu.whut.gumorming.controller;


import cn.edu.whut.gumorming.model.vo.blog.BlogBackInfoVO;
import cn.edu.whut.gumorming.model.vo.blog.BlogInfoVO;
import cn.edu.whut.gumorming.model.vo.response.ResponseResult;
import cn.edu.whut.gumorming.service.BlogInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客控制器
 **/
@Tag(name = "博客模块")
@RestController
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;
    
    /**
     * 上传访客信息
     *
     * @return {@link ResponseResult<>}
     */
    @Operation(summary = "上传访客信息")
    @PostMapping("/report")
    public ResponseResult<?> report() {
        blogInfoService.report();
        return ResponseResult.okResult();
    }
    
    /**
     * 查看博客信息
     *
     * @return {@link ResponseResult<BlogInfoVO>} 博客信息
     */
    @Operation(summary = "查看博客信息")
    @GetMapping("/")
    public ResponseResult<BlogInfoVO> getBlogInfo() {
        return blogInfoService.getBlogInfo();
    }
    
    /**
     * 查看后台信息
     *
     * @return {@link ResponseResult< BlogBackInfoVO >} 后台信息
     */
    @Operation(summary = "查看后台信息")
    @GetMapping("/admin")
    public ResponseResult<BlogBackInfoVO> getBlogBackInfo() {
//        return blogInfoService.getBlogBackInfo();
        return null;
    }
    
    /**
     * 查看关于我信息
     *
     * @return {@link ResponseResult<String>} 关于我信息
     */
// todo   @VisitLogger(value = "关于")
    @Operation(summary = "查看关于我信息")
    @GetMapping("/about")
    public ResponseResult<String> getAbout() {
//        return blogInfoService.getAbout();
        return null;
    }
}