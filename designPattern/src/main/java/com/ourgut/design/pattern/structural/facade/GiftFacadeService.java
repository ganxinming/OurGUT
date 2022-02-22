package com.ourgut.design.pattern.structural.facade;

/**
 * @author ganxinming
 * @createDate 2022/2/11
 * @description 门面模式
 * 提供一个统一的接口去访问多个子系统的多个不同的接口，它为子系统中的一组接口提供一个统一的高层接口。使用子系统更容易使用。
 * 本质：就是化零为整；引入一个中介类，把各个分散的功能组合成一个整体，只对外暴露一个统一的接口；
 * 这两年流行微服务，即化整为零，把一个大服务拆分成一个个零部件；
 * 而门面模式则是反其道，是化零为整；
 *
 * 目的：
 * 为了用户使用方便，把过度拆分的分散功能，组合成一个整体，对外提供一个统一的接口
 *
 * 相当于中介，感觉都不用写了，就是一个中介类，维持子系统引用就好了
 * 完全可以是作为一个工具类
 *
 * 应用：1.Spring JDBC模块下的JdbcUtils类
 * 		2.Tomcat的源码中也有体现，也非常有意思。以RequestFacade类为例
 */
public class GiftFacadeService {
}
