package cn.edu.whut.gumorming.handler.mybatisplus;

import cn.edu.whut.gumorming.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Mybatis PLus 自动填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时填充: 创建时间,创建人,更新时间,更新人
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//表示是注册
        }
//        this.setFieldValByName("createTime", new Date(), metaObject);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());

//        this.setFieldValByName("createBy", userId, metaObject);
        this.strictInsertFill(metaObject, "createBy", Long.class, userId);

//        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

//        this.setFieldValByName("updateBy", userId, metaObject);
        this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
    }
    
    /**
     * 更新时填充: 更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
    }
}