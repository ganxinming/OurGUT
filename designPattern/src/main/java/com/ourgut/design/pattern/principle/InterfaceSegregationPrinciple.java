package com.ourgut.design.pattern.principle;

/**
 * @author ganxinming
 * @createDate 2022/1/8
 * @description
 * 接口隔离原则：是指尽量用多个专门的接口实现，而不使用单一的总接口(可能有很多没有的方法)，实现类不应该依赖它不需要的接口。
 * (比如：A接口有三个方法，但是实现者总是只需要实现其中1到2个方法，总有一个方法实现了空着。实现类，不应该去去实现他不用的接口)
 * 总结：
 * 1.尽量将接口方法细化，抽成不同接口，确保实现了该接口，就实现接口所有方法
 * 2.这样实现类，可以根据自身需要去实现不同的接口
 */
public class InterfaceSegregationPrinciple {
}
