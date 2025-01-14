package cn.edu.whut.gumorming.utils;

/**
 * @author : GuMorming
 * @Project : GuMormingBlog
 * @Package : cn.edu.whut.gumorming.utils
 * @createTime : 2023/7/8 15:40
 * @Email : gumorming@163.com
 * @Description :
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成文件路径工具类
 */
public class PathUtils {
    
    public static String generateFilePath(String fileName) {
        //根据日期生成路径   2099/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return datePath + uuid + fileType;
    }
}