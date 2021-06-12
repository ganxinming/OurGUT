package com.ourspring.data.mybatisPlus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ourspring.data.mybatisPlus.entity.User;

import org.springframework.stereotype.Component;

/**
 * @author ganxinming
 * @createDate 2021/6/11
 * @description
 * Mybatis-plus提供了2个接口1个类：
 *
 * BaseMapper 针对dao层的方法封装 CRUD
 * IService<M,T> 针对业务逻辑层的封装 需要指定Dao层类和对应的实体类 是在BaseMapper基础上的加强
 * ServiceImpl 针对业务逻辑层的实现
 */
@Component("userMapper")
public interface UserMapper extends BaseMapper<User> {
}
