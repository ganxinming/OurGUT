package com.ourgut.ourspring.mybatisplus.Handler;

import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/5/27
 * @description  在实体类中放入@TableField(fill = FieldFill.INSERT) 并没有效果，
 * 最后具体更新填充策略还是需要自己实现
 */
@Component  // 一定放入IOC中!!
@Slf4j   // 日志
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		// 插入时的填充策略
		log.info("start insert fill....");
		this.setFieldValByName("gmtCreate",new Date(),metaObject);  // 在插入时，自动将时间插入进去
		this.setFieldValByName("gmtModified",new Date(),metaObject); // 在插入时，自动将更新时间填充
	}
	@Override
	public void updateFill(MetaObject metaObject) {
		// 更新时的填充策略
		log.info("start update fill....");
		this.setFieldValByName("gmtModified",new Date(),metaObject);  // 在更新数据时，自动将更新时间填充
	}
}
