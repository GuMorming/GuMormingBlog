package cn.edu.whut.gumorming;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming
 * @createTime : 2023/7/8 12:55
 * @Email : gumorming@163.com
 * @Description :
 */
@SpringBootTest
@ConfigurationProperties(prefix = "oss")
public class OSSTest {
    String accessKey;
    String secretKey;
    String bucket;
    
    @Test
    public void test() {
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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String key = year + "/" + month + "/" + day + "/" + new Date().getTime() + ".png";
        
        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            String filePath = "C:\\Users\\GuMorming\\Desktop\\SGBlog\\笔记\\img\\image-20220202111056036.png";
//            InputStream inputStream = new FileInputStream("C:\\Users\\GuMorming\\Desktop\\SGBlog\\笔记\\img\\image-20220202111056036.png");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            
            try {
                Response response = uploadManager.put(filePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
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
        
    }
    
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}