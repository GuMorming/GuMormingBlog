package cn.edu.whut.gumorming.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response    渲染对象
     * @param jsonMessage 待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String jsonMessage) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void setDownLoadHeader(String filename, ServletContext context, HttpServletResponse response) throws UnsupportedEncodingException {
        String mimeType = context.getMimeType(filename);//获取文件的mime类型
        response.setHeader("content-type", mimeType);
        String fname = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment; filename=" + fname);
    }
    
    public static void setDownLoadHeader(String filename, HttpServletResponse response) {
        // 响应内容类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fname = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        // 响应头
        response.setHeader("Content-disposition", "attachment; filename=" + fname);
    }
}