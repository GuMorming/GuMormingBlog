package cn.edu.whut.gumorming.service.impl;

import cn.edu.whut.gumorming.domain.ResponseResult;
import cn.edu.whut.gumorming.domain.enums.AppHttpCodeEnum;
import cn.edu.whut.gumorming.exception.SystemException;
import cn.edu.whut.gumorming.service.UploadService;
import cn.edu.whut.gumorming.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.service.impl
 * @createTime : 2023/7/8 13:31
 * @Email : gumorming@163.com
 * @Description :
 */
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {
    String accessKey;
    String secretKey;
    String bucket;
    String cdnPath;
    
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        // 判断文件类型或文件大小
        // 获取文件名
        String originalFileName = img.getOriginalFilename();
        // 判断类型
        if (!originalFileName.endsWith(".png")) {
            throw new SystemException(AppHttpCodeEnum.UPDATE_TYPE_ERROR);
        }
        // 若判断通过, 则上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFileName);
        String url = uploadImgToOSS(img, filePath);
        
        return ResponseResult.okResult(url);
    }
    
    /**
     * 上传文件至七牛云
     *
     * @param img
     * @return 文件外链
     */
    public String uploadImgToOSS(MultipartFile img, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
//        String accessKey = oss;
//        String secretKey = ;
//        String bucket = ;
        
        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = year + "/" + month + "/" + day + "/" + new Date().getTime() + ".png";
        String key = filePath;
        try {
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            
            try {
                Response response = uploadManager.put(inputStream.readAllBytes(), key, upToken);
                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
                return cdnPath + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "out of try";
    }
}